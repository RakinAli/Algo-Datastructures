/*
* - Rakin Ali Cinte 2
* - Task 1 
* - READ ME 
* - Filters the text so that it's only alphabets. 
*/

#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>

int main(void)
{
    // Initiating a pointer to two files
    FILE *ifp;
    FILE *ofp;

    //Takes the address of the file 
    char input_filename []  = "theText.txt";
    char output_filename[] = "outPut.txt";

    // Opens the files
    ifp = fopen(input_filename, "r");
    ofp = fopen(output_filename, "w");

    if(ifp==NULL)
    {
        printf("Could not open file %s");
        return 1;
    }

    char String;

    while((String = fgetc(ifp)) != EOF)
    {
        if(isalpha(String))
        {
            fputc(String,ofp);
        }
        // If not alpabet, space between the letters. 
        else
		{
			fputc(' ',ofp);
		}
    }

	// Closes the files
    fclose(ifp);
    fclose(ofp);

}
