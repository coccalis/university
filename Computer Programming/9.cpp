#include <stdio.h>
int main()
{
	FILE *fp;
	float prc;
	char str[100];
	
	
	printf("Enter file name:");
	gets(str);
	fp=fopen(str, "w");
	if(fp==NULL)
	{
		printf("Error\n");
		return 1;
	}
	
	while(1)
	{
		printf("Enter price:");
		scanf("%f", &prc);
		if(prc == -1)
		    break;
		if(prc>=10 && prc<=20)
		{
		fprintf(fp,"%f\n", prc);	
		}
	}
	fclose(fp);
  return 0;	
}
