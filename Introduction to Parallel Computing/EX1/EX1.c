/*****************************************************************************************
    DESCRIPTION:   MPI programma to calculate and find the average, variety and delta array.
    AUTHOR:        coccalis
    CLASS:         Ε5 (Τετάρτη 4-6μμ)
    DATE:          20/12/2021
*****************************************************************************************/

#include <stdio.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "mpi.h"
#include <limits.h>


int main(int argc, char **argv){

    int num_p, rank;
    int source,target;
    int num;
    int k;
    int tag1=1, tag2=2, tag3=3, tag4 = 4, tag5 = 5, tag6 =6, tag7=7, tag8=8, tag9=9, tag10=10;
    float sum=0, avg=0, finAvg=0, finVar;
    // Δηλώνουμε με INT_MIN τις max μεταβλητές ώστε να πάρουν την ελάχιστη δυνατή τιμή και με INT_MAX για τις min μεταβλήτες ώστε να πάρουν την μέγιστη δυνατή τιμή.
    int fin_max = INT_MIN , fin_min = INT_MAX ,loc_max = INT_MIN, loc_min = INT_MAX;

    int sizeOfArr;
    float dataArr[200], data_loc[200];
    float d_arr_fin[200], d_arr_loc[200];

    // int *dataArr;
    // int *data_loc;
    // float *d_arr_loc, *d_arr_fin;

    MPI_Status status;
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &num_p);

    while(1){
        if(rank == 0){

            printf("+---------------------------------+\n");
            printf("| If you want to exit press 0. \n");
            printf("| Enter the size of the array: \n");
            printf("| ");
            scanf("%d", &sizeOfArr); //Διάβαζει το μέγεθος του πίνακα.
			
			//Αν ο χρήστης επιλέξει την τιμή 0 τότε θα τερματιστεί το πρόγραμμα.
            if(sizeOfArr == 0){
                printf("| Exiting....\n");
                printf("+---------------------------------+\n");
                MPI_Abort(MPI_COMM_WORLD,1);

            }

            // dataArr = (int *) malloc (sizeOfArr *sizeof(int));

            // if(!dataArr){
            //     printf("error with array\n");
            //     MPI_Abort(MPI_COMM_WORLD,1);
            // }

            //Εκχωρεί ο χρήστης τους αριθμούς και τους αποθηκεύει στον πίνακα dataArr.
            printf("+---------------------------------+\n");
            printf("| Please input the numbers you want: \n");
            for (k = 0; k < sizeOfArr; k++){
                printf("| ");
                scanf("%f", &dataArr[k]);
            }

            //Στέλνει το μέγεθος του πίνακα στα process.
            for (target = 1; target < num_p; target++){
                MPI_Send(&sizeOfArr, 1, MPI_INT, target, tag1, MPI_COMM_WORLD);
            }

            num = sizeOfArr / num_p;
            k = num;

            // Στέλνει σε ίσα μέρη τους αριθμούς σε όλα τα process.
            for (target = 1; target < num_p; target++){
                MPI_Send(&dataArr[k], num, MPI_FLOAT, target, tag2, MPI_COMM_WORLD);
                k += num;
            }

            //Αποθηκεύει τα στοιχεία του πίνακα σε ένα local array.
            for ( k = 0; k < num; k++)
            {
                data_loc[k] = dataArr[k];
            }
        }
        else{
            //Λαμβάνουν τα παιδία τα στοιχεία απο τον κεντρικό επεξεργαστή.
            MPI_Recv(&sizeOfArr, 1 , MPI_INT, 0, tag1, MPI_COMM_WORLD, &status);
            num = sizeOfArr/num_p;
            MPI_Recv(&data_loc[0], num, MPI_FLOAT, 0,tag2,MPI_COMM_WORLD,&status);
        }

    //########## Α ερώτημα #################
        sum = 0;

        for (k=0; k < num; k++){

                sum += data_loc[k]; //υπολογίζουμε το άθροισμα

                // Βρίσκουμε το μέγιστο και ελάχιστο.
                if(data_loc[k] > loc_max){
                    loc_max = data_loc[k];
                }

                if(data_loc[k] < loc_min){
                    loc_min = data_loc[k];
                }
        }

        avg =  (float) sum /  num; //υπολογίζουμε το άθροισμα


        if (rank != 0)  {
            //Τα παιδία στέλνουν πίσω στον κεντρικό επεξεργαστή τα αποτελέσματα
            MPI_Send(&avg, 1, MPI_FLOAT, 0, tag3, MPI_COMM_WORLD);
            MPI_Send(&loc_max, 1 ,MPI_INT, 0, tag4, MPI_COMM_WORLD);
            MPI_Send(&loc_min, 1, MPI_INT, 0, tag5, MPI_COMM_WORLD);

        }else{

            finAvg= avg;

            //Υπολογίζει το τελικό max και min.
            if (loc_max > fin_max){
                fin_max = loc_max;
            }

            if(fin_min > loc_min){
                fin_min = loc_min;
            }


            for (source = 1; source < num_p; source++)  {
                //Λαμβάνει τα στοιχεία απο τα παιδία.
                MPI_Recv(&avg, 1, MPI_FLOAT, source, tag3, MPI_COMM_WORLD, &status);
                MPI_Recv(&loc_max, 1, MPI_INT, source, tag4, MPI_COMM_WORLD, &status);
                MPI_Recv(&loc_min, 1, MPI_INT, source, tag5, MPI_COMM_WORLD, &status);
			
                
				// Υπολογίζει το τελικό max και min.

                if (loc_max > fin_max){
                    fin_max = loc_max;
                }

                if(fin_min > loc_min){
                    fin_min = loc_min;
                }

                finAvg += avg;
            }

            finAvg /= (float) num_p;
            printf("+---------------------------------+\n");
            printf("| Average = %.2f\n", finAvg);
            printf("+---------------------------------+\n");

            //Στέλνει το τελικό μέσο όρο και max, min στα παιδιά, ο κεντρικός επεξεργαστής.
            for(target = 1; target < num_p; target++){
                MPI_Send(&finAvg, 1,MPI_FLOAT, target, tag6, MPI_COMM_WORLD );
                MPI_Send(&fin_max, 1,MPI_INT, target, tag7, MPI_COMM_WORLD);
                MPI_Send(&fin_min, 1,MPI_INT, target, tag8, MPI_COMM_WORLD);
            }
        }

    //########## B ερώτημα ###################

        if(rank != 0){
			// Τα παιδία λαμβάνουν τα στοιχεία.
            MPI_Recv(&finAvg,  1, MPI_FLOAT, 0, tag6, MPI_COMM_WORLD, &status);
            MPI_Recv(&fin_max, 1, MPI_INT, 0, tag7, MPI_COMM_WORLD, &status);
            MPI_Recv(&fin_min, 1, MPI_INT, 0, tag8, MPI_COMM_WORLD, &status);

        }


        sum = 0;

        //Υπολογίζει την πράξη.
        for(k=0; k < num; k++){
            sum = (float) sum + ( (data_loc[k] - finAvg) * (data_loc[k] - finAvg) );
        }

        if(rank!=0){
            //Στέλνουν τα παιδιά το sum στον κεντρικό επεξέργαστη.
            MPI_Send(&sum, 1, MPI_FLOAT, 0 , tag9, MPI_COMM_WORLD);
        }else{
            finVar = sum;

            for(target = 1; target < num_p; target++ ){
                //Λαμβάνει ο κεντρικος επεξεργαστής το sum του κάθε παιδιού και υπολογίζει τη συνολική διασπορά.
                MPI_Recv(&sum, 1, MPI_FLOAT, target , tag9, MPI_COMM_WORLD, &status);

                finVar += sum;
            }

            finVar = finVar / sizeOfArr;
            printf("| Var = %.2f \n", finVar);
            printf("+---------------------------------+\n");


        }

    //################ Γ ερώτημα ################


        for(k=0; k < num; k++){

            if((fin_max - fin_min) != 0){ //ελέγχει αν ο παρανομαστής είναι μηδέν.
                d_arr_loc[k] = (float) ( (data_loc[k] - fin_min) / (fin_max - fin_min) ) * 100; //αποθηκεύει το αποτέλεσμα σε ένα local array.
            }

        }

        if(rank !=0){
            //Στέλνον το local delta array στον κεντρικό επεξεργαστή.
            MPI_Send(&d_arr_loc[0], num, MPI_FLOAT, 0, tag10, MPI_COMM_WORLD);
        }else{

            for(k=0; k< num_p; k++){
                d_arr_fin[k] = d_arr_loc[k];
            }

            for (target = 1; target < num_p; target++)
            {
                //Λαμβάνει τα στοιχεία από τα παιδία και τα αποθηκεύει σε ένα τελικό array
                MPI_Recv(&d_arr_loc[0], num,MPI_FLOAT, target, tag10,MPI_COMM_WORLD, &status);

                for(int i = 0; i < num; i++){
                    d_arr_fin[k] = d_arr_loc[i];
                    k++;
                }
                k += num;

            }

            printf("| Max = %d \n", fin_max);
            printf("| Min = %d\n", fin_min);
            printf("+---------------------------------+\n");
            printf("| The Contents of Delta Arrat: \n");

            //Εκτύπωση του πίνακα delta.
            for (k = 0; k < sizeOfArr; k++){
                printf("| --------------\n");
                printf("| Position = %d \n", k);
                printf("| Data = %.2f \n", d_arr_fin[k]);
            }

            printf("+---------------------------------+\n");


        }


    }

    MPI_Finalize();
    return 0;
}