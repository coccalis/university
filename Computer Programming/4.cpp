/*Φτιαξτε προγραμμα το οποιο να διαβαζει τις τιμες 3 προιντων και να εμφανιζει την μεση τιμη τους*/
#include <stdio.h>
int main()
{
    float i, j, k, mo;
     printf("Enter numbers:");
     scanf("%f %f %f", &i, &j, &k);
     mo = (i+j+k)/3;
     printf("Mo = %f\n", mo);
     return 0;
}