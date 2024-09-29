/*Φτιαξτε προγραμμα το οποιο να διαβαζει τις τιμες τριων προιοντων και να εμφανιζει την μεγαλυτερη απο αυτες*/
#include <stdio.h>
int main()
{
    float i, j, k, max;
    scanf("%f%f%f", &i, &k, &j);
    if(i>j)
    {
        max = i;
    }
    else
    {
        max = j;
    }
    if(k>max)
    {
        printf("%f",k);
    }
    else
    {
        printf("%f", max);
    }
    return 0;
}