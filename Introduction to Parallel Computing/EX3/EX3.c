/*****************************************************************************************
    DESCRIPTION:   Factorial with MPI.
    AUTHOR:        coccalis
    CLASS:         Ε5 (Τετάρτη 4-6μμ)
    DATE:          26/01/2022
*****************************************************************************************/

#include <stdio.h>
#include "mpi.h"

int main(int argc, char** argv) {
    int my_rank;
    int p,k,res,finres,a1,b1,num;
    int source;
    int target;
    int tag1 = 50;
    int tag2 = 60;
    int N;
    MPI_Status status;


    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &my_rank);
    MPI_Comm_size(MPI_COMM_WORLD, &p);


    if (my_rank == 0)  {
        printf("Give the amount of numbers:\n");
        scanf("%d", &N);
        for (target = 1; target < p; target++)
            MPI_Send(&N, 1, MPI_INT, target, tag1, MPI_COMM_WORLD);
    }
    else
        MPI_Recv(&N, 1, MPI_INT, 0, tag1, MPI_COMM_WORLD, &status);


    res = 1; //Θέτουμε με το 1καθώς έτσι μας διευκρινίζει ο τύπος.
    num = N/p; //elements per process
    a1 = (my_rank * num) + 1;
    b1 = a1 + num - 1;
    for (k=a1; k<=b1; k++){
        res *= k; // Κάθε process υπολογίζει το τοπικό γινόμενο.
    }

    //Μαζεύει το τοπικό γίνομενο από ολες τις διεργασίες και το στέλνει στον αρχηγό
    MPI_Reduce(&res, &finres, 1, MPI_INT, MPI_PROD, 0, MPI_COMM_WORLD);
    if (my_rank == 0)  {
        printf("Final result from parent is:  %d\n", finres);
    }

    MPI_Finalize();
}