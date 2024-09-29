#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>

void * simple1(void *);
void * simple2(void *);
void * simple3(void *);

#define THREADS_NUM 3
pthread_t tid[THREADS_NUM];
sem_t semA,semB,semC;

int main(){
   int i;

   sem_init(&semA,0,1);
   sem_init(&semB,0,0);
   sem_init(&semC,0,0);
   
   pthread_create(&tid[0], NULL, simple1, NULL);
   pthread_create(&tid[1], NULL, simple2, NULL);
   pthread_create(&tid[2], NULL, simple3, NULL);

   for(i=0; i < THREADS_NUM; i++){
   
      pthread_join(tid[0], NULL);
      pthread_join(tid[1], NULL);
      pthread_join(tid[2], NULL);
      
   }
   //printf("\nfinished \n");

}

void * simple1(void * parm){
   
   while(1){
   sem_wait(&semA);
   printf("<one> ");
   sem_post(&semB);
   }
   
}    

void * simple2(void * parm){
   
   while(1){
   sem_wait(&semB);
   printf("<two> ");
   sem_post(&semC);
   }
   
} 

void * simple3(void * parm){
   
   while(1){
   sem_wait(&semC);
   printf("<three>");
   sem_post(&semA);
   }
   
}