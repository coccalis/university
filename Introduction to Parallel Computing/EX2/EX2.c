/*****************************************************************************************
    DESCRIPTION:   An mpi project that checks parallelly if a NxN input matrix is strictly diagonally dominant, it searches for the max element of the main diagonal of A and prints it
                    Based on max, a new B NxN matrix is being created, where: " B[i][j] = max–abs(A[i][j]), i<>j"  " B[i][j] = max, i=j "
    AUTHOR:        coccalis
    CLASS:         Ε5 (Τετάρτη 4-6μμ)
    DATE:          23/1/2022
    NOTE:          it doesn't work for uneven number of process and array size
*****************************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include "mpi.h"
#include <limits.h>

void create2DMatrix(int ***arr,int row, int col);
int *cov2DArrTo1DArr(int **arr, int row, int col);
int checkifMatrixSDD(int *arr, int num, int arrLen, int rank);
void freeArray(int **ptr, int arrLen);


int main(int argc, char* argv[]){

    int arrLen;
    int num_p, rank, num;
    int i,j;
    const int root = 0;
    int flag=0, finalFlag;
    int max = INT_MIN, finalMax;

    //declare array
    int **A2d, *A1d;
    int *recvArr, *recvBArr;
    int **B2d, *B1d;


    MPI_Init(&argc, &argv);                /* Initialize MPI */
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &num_p);   /* Get own identifier */





    if(rank == 0){
        printf("+-----------------------------------------------------------+\n");
        printf("| Enter the dimensions of the array:  ");
        scanf("%d", &arrLen); //Διάβαζει το μέγεθος του πίνακα.


        create2DMatrix(&A2d,arrLen,arrLen); // Καλούμε την συνάρτηση και δημιουργούμε τον δισδιάστατο πίνακα Α

        //Εκχωρεί ο χρήστης τους αριθμούς και τους αποθηκεύει στον πίνακα Α(NxN).
        printf("| Please input the data of the [ %d X %d ] array: \n", arrLen,arrLen);
        for(i =0; i< arrLen; i++){
            for(j=0; j<arrLen; j++){
                printf("| A[%d][%d]= ", i,j);
                scanf("%d", &A2d[i][j]); //enter data
            }

            A1d = cov2DArrTo1DArr(A2d, arrLen, arrLen); //καλούμε την συνάρτηση και μετατρέπουμε τον δισδιάστατο πίνακα σε μονοδιάστατο
        }

        printf("+-----------------------------------------------------------+\n");
        printf("| The initial 2D Matrix is: \n");
        for(i =0; i< arrLen; i++){
		printf("| ");
            for(j=0; j<arrLen; j++){
                printf("%4d", A2d[i][j]);
            }
            printf("\n");
        }

        printf("| The 1D Matrix after convertion is: \n");
        for(i=0;i<(arrLen*arrLen);i++){

            printf("| A1D[%d] = %d \n", i,  A1d[i]);
        }

        printf("+-----------------------------------------------------------+\n");

    }


    MPI_Bcast(&arrLen,1,MPI_INT,root,MPI_COMM_WORLD);
    num = arrLen/num_p;

    recvArr =(int*)calloc(num*arrLen, sizeof(int));
    MPI_Scatter(A1d, num*arrLen, MPI_INT, // μοίρασε τις σειρές του πίνακα για num*arrLen αριθμούς
                recvArr,num*arrLen, MPI_INT, // Λαμβάνει τις σειρες του πίνακα
                root,MPI_COMM_WORLD);

    //######### Ερώτημα Α #######################
    flag += checkifMatrixSDD(recvArr,num,arrLen,rank); //καλούμε την συνάρτηση και ελέγχουμε αν ο πίνακας είναι αυστηρά διαγώνιος.

    MPI_Reduce(&flag,&finalFlag,1,MPI_INT, MPI_SUM,root,MPI_COMM_WORLD); //μαζεύει τα flags, τα αθροίζει και τα αποθηκεύει στην μεταβλτητή finalFlag

    MPI_Barrier(MPI_COMM_WORLD); //sync prossess

    if(rank==0){

        // αν το τελικο flag είναι ίσο με τον αριθμό των διεργασιων τότε ο πίνακας είναι sdd.
        if(finalFlag==num_p){
            printf("| The Matrix is indeed Strickly Diagonally Dominant.\n");
            printf("| We can proceed with finding the maximum value diagonally.\n");
            printf("+-----------------------------------------------------------+\n");

        }
        else{
            printf("| ERROR! The Matrix isn't Strickly Diagonally Domonant.\n");
            printf("| We cant proceed with rest of the problem. Exiting..... .\n");
            printf("+-----------------------------------------------------------+\n");
            MPI_Abort(MPI_COMM_WORLD,1);
        }

    }

    //######### Ερώτημα Β #####################

    // βρίσκουμε το τοπικό με΄γιστο στην διαγώνιο
    for(i=0; i< num; i++){

        int rowNum =  abs(recvArr[i*arrLen+rank*num+i]);

        if(max < rowNum){
            max = rowNum;
        }
    }

    MPI_Reduce(&max,&finalMax, 1, MPI_INT, MPI_MAX,root, MPI_COMM_WORLD); //μαζεύει τα τοπικά max και τα συγκρινεί για να βρει το τελικό max
    MPI_Bcast(&finalMax,1,MPI_INT,root,MPI_COMM_WORLD);
    MPI_Barrier(MPI_COMM_WORLD);

    if(rank == 0){
        printf("| The Diagonal Maximum value of the Matrix is: %d \n", finalMax);
        printf("+-----------------------------------------------------------+\n");


    }

    //######### Ερώτημα Γ ##########################
    create2DMatrix(&B2d, num, arrLen); // Καλούμε την συνάρτηση και δημιουργούμε τον δισδιάστατο πίνακα Β

    for(i=0; i<num;i++){
        for(j=0;j<arrLen;j++){

                B2d[i][j]= finalMax - abs(recvArr[i*arrLen+j]);

            if((i+rank)==j){

                B2d[i][j]=finalMax;
            }
            //or you can do if(i==j){  B2d[i][j]=finalMax; }else{ B2d[i][j]= finalMax - abs(recvArr[i*arrLen+j]);}
        }
    }


    recvBArr =(int*)calloc(num*arrLen, sizeof(int));
    B1d = cov2DArrTo1DArr(B2d, num, arrLen); //μετατρέπουμε σε μονοδιάστατο

    // κάθε διεργασία στέλνει τα δεδομένα πίσω στον γονέα
    MPI_Gather(B1d, num*arrLen,MPI_INT,
                recvBArr, num*arrLen,MPI_INT,
                root,MPI_COMM_WORLD);
    MPI_Barrier(MPI_COMM_WORLD);

    if(rank ==0){
        printf("| The B 2D Matrix is: \n");
        for(i =0; i< arrLen; i++){
            printf("| ");
            for(j=0; j<arrLen; j++){
                printf(" %d ", recvBArr[i*arrLen+j]);
            }
            printf("\n");
        }
        printf("+-----------------------------------------------------------+\n");
    }

	MPI_Finalize();
}



//######## FUNCTIONS ########

void create2DMatrix(int ***arr, int row, int col){

    *arr = (int**)calloc(row,sizeof(int*));
    for (int i = 0; i < row; i++)
    {
        (*arr)[i]=(int*)calloc(col,sizeof(int));
    }
}

int *cov2DArrTo1DArr(int **arr, int row, int col){

    int *temp = (int*)calloc(row*col, sizeof(int));

    for (int i = 0; i < row; ++i) {
		for (int j = 0; j < col; ++j) {
			// mapping 1D array to 2D array
			temp[i * row + j] = arr[i][j];
		}
	}

    return temp;
}


int checkifMatrixSDD(int *arr, int num, int arrLen, int rank){

    int i,j,rowSum;

    for(i=0;i<num;i++){
        rowSum=0;

        for(j=0;j<arrLen;j++){

            rowSum += abs(arr[i*arrLen + j]);

        }

        rowSum -= abs(arr[i*arrLen+rank*num+i]);
        // αν το άθροι είναι μικρότερο απο το στοιχείο στην διαγώνιο τότε επέστρεψε στο flag 1. Αλλίως επιστρέφει 0.
        if(abs(arr[i*arrLen+rank*num+i]) > rowSum){
            return 1;
        }
    }
    return 0;
}


/*
Χρησιμοποιήσα την calloc για δέσμευση της μνήμης καθώς με την malloc είχα θέματα στην μνήμη και μου εμφάνιζε
σκουπίδια όταν τα πέρναγα από την εντολή scatter.

Επίσης δεν έχει απαντηθεί το Δ ερώτημα.



*/