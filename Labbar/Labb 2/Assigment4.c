/*
* - Ali Rakin Syed  001207-xxxx
* - Lab assigment 4 - 
The code will take an array and "sort" it so that it moves all the negative elements 
behind all the positive ones. It does not sort, just moves datatypes. 
*/
#include <stdio.h>

void swap(int array[] , int i , int j)
{
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}

void printArray(int array[], int array_length) // Fel med att ta fram array length
{
	int i = 0;
	int arrayLength = array_length;
	printf("The size of the array is ");
	printf("%d \n",arrayLength);
	for(i; i<arrayLength; i++) 
    {
        printf("[");
        printf("%d ",array[i]);
        printf("]");
    }
}

// A function that places all the negative integers behind all the postives 
void polarISATION(int array[], int size) // Fixa om
{
    int temp;
    int i = 0;
    int j = 0;
    for(i; i<size-1; i++) // Välj något  - Kalla den för etta
    {
        for(j=i+1; j<=size-1; j++) // Jämför med alla 
        {
            if(array[i]>0 && array[j] <0) // Om etta är större än noll och andra är mindre än noll - kalas
            {
                swap(array,i,j);
            }
        }
    }
}

int main(void)
{
    // Testar en enkel fall
    int array[] = {1,2,3,-1,-2,-3};
    int arraylength = sizeof(array)/sizeof(array[0]);
    printArray(array,arraylength);
    polarISATION(array,arraylength);
    printArray(array,arraylength); 

    // Testar ifall alla är negativa utom en
    int array1[] = {1,-10,-9,-8,-6,-3};
    int arraylength1 = sizeof(array1)/sizeof(array1[0]);
    printArray(array1,arraylength1);
    polarISATION(array1,arraylength1);
    printArray(array1,arraylength1);

    // Teestar ifall alla är postiva
    int array2[] = {1,2,3,4,5,6,7};
    int arraylength2= sizeof(array2)/sizeof(array2[0]);
    printArray(array2,arraylength2);
    polarISATION(array2,arraylength2);
    printArray(array2,arraylength2);


}
