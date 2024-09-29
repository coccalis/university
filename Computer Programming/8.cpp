#include <stdio.h>
#include <string.h>

struct student
{
	char name[10];
	int code;
	float grd;
};

int main()
{
	int i;
	float grd;
	
	struct student s[10];
	for(i=0;i<10;i++)
	{
		printf("Enter name:");
		gets(s[i].name);
		printf("enter code:");
		scanf("%d", &s[i].code);
		printf("enter grade:");
		scanf("%f", &s[i].grd);
		
		getchar();
	}
	scanf("%d", &grd);
	for(i=0;i<10;i++)
	{
		if(s[i].grd>grd)
		{
			printf("N:%s C:%d G:%f\n", s[i].name, s[i].code, s[i].grd);
		}
	}
	return 0;
}
