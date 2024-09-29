
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(){

    int P1,P2,P3,P4,P5, fd[2],a, status;
    //δηλώνουμε το μηνύμα που θέλουμε να εμφανιστεί στην διεργασία P0 πριν την δημιουργία της P2
    char* msg = "hello from your first child\n", msgsize [100]; 
    //ώστε στο τέλος να αντικατασταθεί η P0 από την εντολή ps
    char* cmd[]={"ps", NULL};

    //σε περίπτωση που πετάξει κάποιο error η pipe.
    if(pipe(fd)==-1){
        printf("An error has ocurred with opening the pipe\n");
        
        return 1;

    }

    //δημιουργία της P1
    P1=fork();

    if(P1==0){ //Αμα η fork()==0 τοτε είναι παιδί.

        close(fd[0]);  //close reading 
        write(fd[1], msg, strlen(msg)+1); //εκχωρούμε το μηνύμα.
        close(fd[1]);  //close writing

        printf("I'm the child P1 and my parent is P0. P1 : %d, i have PID: %d and my PPID: %d \n",P1,getpid(),getppid());
        printf("\n");

    }else if(P1!=0){ //γονέας
	

        printf("I am the parent P0. I have PID:  %d and my PPID is: %d \n", getpid(), getppid());	
        printf("\n");

        close(fd[1]); //close writting
        a=read(fd[0],msgsize,sizeof(msgsize)); //διαβαζουμε το μηνυμα
        write(1,msgsize,a);
        close(fd[0]);//close reading
        printf("\n");

        P2=fork();//δημιουργία της P2
        waitpid(P2,&status,0);//να περιμένει η P2 να τελείωσουν οι διεργασίες των παιδιών της

        if(P2==0){//Θέλουμε να δημιουργήσουμε τα παιδία όποτε η fork==0

            P3=fork(); // δημιουργία της P3        
		
            if(P3==0){

                printf("I'm the child P3 and my parent is P2. P3 : %d, i have PID: %d and my PPID: %d \n",P3,getpid(),getppid());
                printf("\n");

                    P4=fork();//δημιουργία της P4
                    if(P4!=0){//αν η fork!=0 να εμφανίσει το μηνύμα

                        printf("I'm the child P4 and my parent is P2. P4 : %d, i have PID: %d and my PPID: %d \n",P4,getpid(),getppid());
                        printf("\n");
                        sleep(3);

                    }else{ wait(&status); }

                    P5=fork();//δημιουργία της P5
                    if(P5!=0 && P4!=0){// P5!=0 και P4!=0 για να αποφευχθεί  η διπλή printf

                        printf("I'm the child P5 and my parent is P2. P5 : %d, i have PID: %d and my PPID: %d \n",P5,getpid(),getppid());
                        printf("\n");
                        sleep(2);
                        
                    }else{ wait(&status); }
                
            }else{ //εκτύπωση μηνύματος P2 και ολοκλήρωση της P2

                    wait(&status); //περιμένει να ολοκληρώσουν τα παιδιά της η P2
                    printf("I'm the child P2 and my parent is P0. P2 : %d, i have PID: %d and my PPID: %d \n",P2,getpid(),getppid());	
                    printf("\n");
            }
            
        }
        if(P2!=0){

            wait(&status);
            execv("/bin/ps",cmd);// ώστε να αντικατασταθεί η P0 με την εντολή ps
            printf("Getpid: %d, getppid: %d , P2 : %d \n", getpid(), getppid(), P2);
        }
        
    }else{
        printf("Error with the process.\n");
        return 0;
    }


return 0;
}