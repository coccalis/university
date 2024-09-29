
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <time.h>


int p,n; //Δηλώνουμε τις μεταβλητές p,n όπου p τα threads και n τα elements 
int part=0; 
int *local_sum, *arr; //pointers για τον πίνακα arr και την μεταβλητή local_sum που θα αποθηκεύει το τοπικό άθροισμα.


// Συνάρτηση void που θα υπολογήσει το άθροισμα των τετραγώνων των στοιχείων.
void* sum_array(void* arg)
{
    int thread_prt;
    thread_prt = part++;

    for (int i = thread_prt * ( n / p); i < (thread_prt + 1) * (n / p); i++){

        local_sum[thread_prt] += (arr[i] * arr[i]); //αποθηλεύουμε το άθροισμα στην local_sum
    }
}


int main(){
    
    int total_sum=0, a;
    

    printf("Please enter the number of elements: \n");
    scanf("%d", &n);

    printf("Please enter the number of threads you wish: \n");
    scanf("%d", &p);

    arr=(int*) calloc(n,sizeof(int)); //Χρησιμοποιούμε την calloc για τον πίνακα arr για να δεσμεύσουμε μνήμη άναλογα με το μέγεθος του πίνακα που θα εκχωρήσει ο χρήστης και να επιστρέψει ενα pointer. 
    if(arr==NULL){ //συνθήκη ελέγχου σε περίπτωση που επιστρέψει NULL. 

        printf("Memory not allocated for arr.\n");
        exit(0);

    }else{

        //Δίνεται η επιλογή στον χρήστη να εκχωρήση από το keyboard του τους αριθμόυς που επιθυμεί ή να εκχωρηθούν τυχαίοι αριθμόι.
        printf("\n");
        printf("Do you want to insert numbers by keyboard or generate random rumbers? \n");
        printf("Please type 1 for first option or type 2 for second option. \n");
        scanf("%d", &a);

        if(a==1){

            //Εκχωρούμε τις τιμές που θέλουμε στον πίνακα arr.
            printf("\n");
            printf("Please enter the elements you want to calculate: \n");
            for(int k=0; k<n; k++){
                scanf("%d", &arr[k]);
            }

        }else if(a==2){

            //srand(time(0));
            for(int k=0; k<n; k++){
                arr[k]=rand()%100; //να διαλέξει αριθμόυς απο 1-99
            }

        }else{
            printf("You entered wrong argument. Please re-execute the program and try again.\n");
            return 0;
        }

    }

    local_sum=(int*)calloc(p,sizeof(int)); //Χρησιμοποιούμε και για την local_sum την calloc
    if(local_sum==NULL){ // Συνθήκη ελεγχού
        printf("Memory not allocated for local_sum.\n");
        exit(0);
    }

    pthread_t t[p]; //ορίζουμε την μεταβλητή όπου θα αποθηκεύτουν οι πληροφοριές του thread

    // Δημιουργούμε τα threads. Χρησιμοποιο΄΄υμε την for επειδή ο χρήστης μπορεί να ζητήσει να δημιουργηθούν παραπάνω απο 2 threads.
    for (int i = 0; i < p; i++){

        pthread_create(&t[i], NULL, sum_array, (void*)NULL);

    }
    // Χρησιμοποιούμε την pthread_join ωστε να επιβληθεί η αναμονή για τον τερματισμό των threads
    for (int i = 0; i < p; i++){

        pthread_join(t[i], NULL);
    
    }
    
    //Χρησιμοποιούμε την for για να υπολογίσουμε το ολικό άθροισμα.
    for(int i=0; i < p; i++){

        total_sum=local_sum[i];
    
    }

    printf("Total_sum: %d \n", total_sum );
    
    //Χρησιμοποιούμε την free για να ελευθερώσουμε απο την μνήμη τον χώρο που δεσμεύσαμε.
    free(arr);
    free(local_sum);

return 0;
}


