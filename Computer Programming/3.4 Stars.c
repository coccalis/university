 #include <stdio.h>
 #include <math.h>
 
 int main()
 {
 	int N, sum, P, nopos;
 	system ("chcp 1253");
	int i, j;
 	printf("Πόσες γραμμές θες; :");
 	scanf("%d", &N);
 	
 	for (i=0; i<N; i++)
 	{
 		for (j=0; j<=i; j++)
 		printf("*");
 		printf("\n");
	 }
	 printf("\n");
	 
	 /*δέυτερο σχήμα */
	 
	 for (i=0; i<N; i++)
	 {
	 	for(j=N; j>=i; j--)
	 	 printf(" ");
	 	for (j=0; j<=i; j++)
	 	 printf ("*");
	 	printf("\n");
	 }
	 printf("\n");
	 
	  /*τρίτο σχήμα */
	  
	 for(i=1; i<=N; i++)
	 {
	 	for(j=1; j<=N-i; ++j)
	 	printf(" ");
	 	for(j=1; j<=2*i-1; j++)
	 		printf("*");
		 
		 printf("\n");
	 }
	 printf("\n");
	 
	 /*τέταρτο σχήμα*/
	 
	 	for (i=1;i<=N;i++) 
		{
		for (j=1;j<=N;j++) 
		{
			if(j==N || j==1 || i==1 || i==N || j==N)
			printf("*"); 
			else if (j==i || j==N-(i-1))
			printf(".");
			else
			printf(" ");
		}
		printf("\n");
	    }
	 return 0;
 }
