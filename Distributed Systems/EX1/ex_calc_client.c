/*

    This program takes input from the user for two arrays X and Y,
    and then prompts the user to select one of three calculations to
    perform on the arrays: inner product, average values, or vector product.
    The program then sends the input and choice to a remote procedure
    call server using the RPC library, and prints the result returned by
    the server.
*/
/*
    Askhsh 1
*/
#include <stdio.h>
#include <stdlib.h>
#include <rpc/rpc.h>
#include "ex_calc.h"

int main() {
    // Declare variables and allocate memory for input arrays
    InputData input;
    int i, choice;
    printf("#####################################\n");
    printf("# Enter the number of elements: ");
    scanf("%d", &input.n);

    input.X = (float*) malloc(input.n * sizeof(float)); //allocate memory with malloc based on the input.n
    input.Y = (float*) malloc(input.n * sizeof(float));

    // Prompt user for input arrays X and Y
    printf("# Enter the values of X: ");
    for (i = 0; i < input.n; i++) {
        scanf("%f", &input.X[i]);
    }

    printf("# Enter the values of Y: ");
    for (i = 0; i < input.n; i++) {
        scanf("%f", &input.Y[i]);
    }

    // Prompt user to select a calculation to perform
    printf("#####################################\n");
    printf("# Select the calculation to perform:\n");
    printf("# 1. Inner product\n");
    printf("# 2. Average values\n");
    printf("# 3. Vector product\n");
    printf("# Enter your choice: ");
    scanf("%d", &choice);

    // Create RPC client handle
    CLIENT *cl;
    cl = clnt_create("localhost", EX_CALC_PROG, EX_CALC_VERS, "udp");
    if (cl == NULL) {
        clnt_pcreateerror("localhost");
        exit(1);
    }

    // Perform the selected calculation using RPC calls to server
    switch (choice) {
        // calculate inner product
        case 1: {
            int *result;
            result = inner_product_1(&input, cl); // call rpc fun and pass input, client handle
            if (result == NULL) {
                clnt_perror(cl, "inner_product_1 call failed");
            } else {
                printf("# The inner product is: %d\n", *result);
            }
            free(result); //free memory allocated.
            break;
        }
        case 2: {
            float *result;
            result = avg_values_1(&input, cl);
            if (result == NULL) {
                clnt_perror(cl, "avg_values_1 call failed");
            } else {
                printf("# The average values are: %f, %f\n", result[0], result[1]);
            }
            free(result);
            break;
        }
        case 3: {
            float *result;
            result = vec_product_1(&input, cl);
            if (result == NULL) {
                clnt_perror(cl, "vec_product_1 call failed");
            } else {
                printf("# The vector product is: ");
                //print every result
                for (i = 0; i < input.n; i++) {
                    printf("%f ", result[i]);
                }
                printf("\n");
            }
            free(result);
            break;
        }
        default:
            printf("Invalid choice\n");
            break;
    }
    printf("#####################################\n");

    // Destroy RPC client handle and free memory for input arrays
    clnt_destroy(cl);
    free(input.X);
    free(input.Y);

    return 0;
}
