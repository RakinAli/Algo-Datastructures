/*
* - Ali Rakin
* - Cinte 2
* - Assigment 1,2,3
*/

import java.util.Scanner;


public class Assigment1_2_3
{
    //Switch integer i with j and j with i.
    static void swap(int [] array, int i, int j)
    {
        int temp = array[i];
        array [i] = array[j];
        array[j] = temp;
    }

    // A method that prints out the array
    static void printArray(int[]array)
    {
        for(int i= 0; i<array.length; i++)
            System.out.print(array[i]+", ");
        System.out.println("");
    }

    public static void whatToInsert(int [] fixarray)
    {
        int arraysize = fixarray.length;
        for(int i = 0; i<arraysize; i++)
        {
            for(int j = i; j<arraysize-1; j++)
            {
                if(fixarray[i] > fixarray[j+1])
                {
                    System.out.println("[Index: " + i + ", Value: " + fixarray[i] + "], " + "[Index: " + (j+1) + ", Value: " + fixarray[j+1] + "]" );
                }
            }
        }
    }

    //Insertion-sort 
    public static void insertionSort(int [] fixArray)
    {
        int swaps = 0;
        int arrayLength = fixArray.length;
        for(int i = 1; i<arrayLength; i++) // Väljer en index som ska bytas
        {   
            for(int j = i; j>0 && fixArray[j]<fixArray[j-1]; j--) //Jämför med allt bakom. Lägger den bakom en tal som den e större än 
            {
                int jLag = j-1;
                System.out.println("[ " + j + ", "+ fixArray[j] +" ]" + "<===> " + "[ " + jLag + ", " + fixArray[jLag] + " ]"); 
                swap(fixArray,j,j-1);
                swaps++;
                System.out.println("");
            }
        }
        System.out.println("The number of swaps was " + swaps);
    }

    //MergeSort 
    public static int[] mergeSort(int[]array)
    {
        // Just return it 
        if(array.length <= 1)
        {
            return array;
        }
        
        int middle = array.length / 2;
        int[] leftSide = new int[middle];
        int[] rightSide;

        //Fixing the right side of the array so it takes the remainder element
        if(array.length % 2 ==0)
        {
            rightSide = new int[middle];
        }
        else // So that it takes in the remainder element
        {
            rightSide = new int[middle+1];
        }

        //Populate the leftside of the array
        for(int i = 0; i <middle; i++)
        {
            leftSide[i] = array[i];
        }
        
        //Fixing the right side
        for(int j=0; j <rightSide.length;j++)
        {
            rightSide[j] = array[middle+j];
        }

        //Stores it in here
        int [] result = new int [array.length];
        
        //Recursive part - Will dvide by 2 until it can't no longer
        leftSide = mergeSort(leftSide);
        rightSide = mergeSort(rightSide);

        result = mergeTheSort(leftSide,rightSide);
        
        return result;
    }

    //After the mergeSort is finished with dividing by 2
    private static int[] mergeTheSort( int[] left, int[]right)
    {
        int[] result = new int [left.length + right.length];   

        int leftPekare;
        int rightpekare; 
        int resultPekare;

        leftPekare = rightpekare = resultPekare = 0;
        
        //When there are items in both the arrays 
        while(leftPekare < left.length || rightpekare <right.length  )
        {
            // Condition if there are items in both arrays
            if(leftPekare <left.length && rightpekare < right.length)
            {
                //Code when the left item is less than the right item
                if(left[leftPekare] < right[rightpekare])
                {
                    result[resultPekare ++] = left[leftPekare++];
                }
                //Code when the right item is less than the left item
                else
                {
                    result[resultPekare ++] = right[rightpekare++];
                }
            }

            //Condition if there are only items in the left array
            else if(leftPekare<left.length)
            {
                result[resultPekare++] = left[leftPekare++];
            }
            
            //Condition if there are only items in the right array 
            else if (rightpekare<right.length)
            {
                result[resultPekare++] = right[rightpekare++];
            } 
        }
        return result;
    }

    // Just a text - too lazy to write the same things repetetively
    public static void introduction() 
    {
        write("--------------------");
        write("What do you want the program to do");
        write("1. Insertionsort the array");
        write("2. Mergesort the array");
        write("3. Print out the elements in the array");
        write("4. Exit the program");
        write("---------------------");
    }
   
    // A function so that i do not need to write System.out.println all the time
    public static void write(String x)
    {
        System.out.println(x);
    }

    // just a test - too lazy to write teh same things repetetively
    public static void arrayCreation()
    {        
        write("------------");
        write("What kind of array do you want to create");
        write("1. Randomly generated array");
        write("Any other number = User created");
    }
    
    // just a test - too lazy to write teh same things repetetively
    public static void howManyArrayElements()
    {
        write("-----------------------------");
        write("How many elements would you like to store?");
        write("--------------------------------");
    }

    // Creates an int array with randomly created numbers. 
    public static int[] randomlyCreatedArrayWithIntegers(int howManyElements)
    {
        int arrayy[] = new int[howManyElements];
        int min = 0;
        int max = howManyElements;
        
        for(int i =0; i<howManyElements; i++)
        {
            arrayy[i] = (int)(Math.random()*((max - min) + min));
        }

        return arrayy;
    }

    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        int array[];

        howManyArrayElements();
        int howManyArrayElements = in.nextInt();
        array = new int[howManyArrayElements];
        for(int i = 0; i<howManyArrayElements; i++)
        {
            System.out.println("At index " + i);
            array[i] = in.nextInt();
        }
        printArray(array);

        whatToInsert(array);
        insertionSort(array);
        printArray(array);
        
        
    }

}