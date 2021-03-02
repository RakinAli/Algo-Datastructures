#include <stdio.h>


int  iterativee()
{
	char word[50];
	int i = 0;
	int j = 0;
	int howManyIndexes = 0; 
    char temporal;

    // Stores in the array and how many characters were sent
    while(((word[i]=getchar()) != '\n'))
    {
        i++;
        howManyIndexes++;
    }

    // Reverses the array
    for(j=0; j<(howManyIndexes/2);j++)
    {
        temporal = word[j];
        word[j] = word[howManyIndexes-j-1];
        word[howManyIndexes-j-1] = temporal;
    }

    for(j=0; j<howManyIndexes; j++)
    {
        printf("%c", word[j]);
    }
}
/*
* - Scans in what user writes in until user hits enter. Recursive keeps scanning in
* - When user hits enter. Prints all the words backwords
*/
void recursive()
{
	char reverse;
    scanf("%c", &reverse); //Scannar och lagrar i reverse adrressen
    if(reverse != '\n')
    {
        recursive();
        printf("%c", reverse);
    }
}

int main(void)
{
    printf("Write your character - no more than 50:");

	//iterativee();  
    recursive();

    //printf("hello World");
}

//getchar -  
//putchar - 
