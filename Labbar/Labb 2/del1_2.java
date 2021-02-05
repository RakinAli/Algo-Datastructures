import java.util.Scanner;


public class del1_2
{

    // Creating a method that does swapping
    static void swap(int [] array, int i, int j)
    {
        int temp = array[i];
        array [i] = array[j];
        array[j] = temp;
    }

    // Puts it in Decending order
    public static void insertionSort(int [] fixArray)
    {
        long swaps = 0;
        int arrayLength = fixArray.length;
        for(int i = 1; i<arrayLength; i++)
        {   
            for(int j = i; j>0 && fixArray[j]<fixArray[j-1]; j--)
            {
                swap(fixArray,j,j-1);
                swaps++;
            }
        }
        System.out.println("The number of swaps was " + swaps);
    }

    public static void decendingInseritionsort(int [] fixArray)
    {
        // Multiply by minus 1 
        for(int i = 0; i<fixArray.length; i++)
        {
            fixArray[i] = fixArray[i]* -1;
        }

        insertionSort(fixArray);

        // Multiply by minus 1
        for(int j = 0; j<fixArray.length; j++)
        {
            fixArray[j] = fixArray[j] *-1;
        }
    }

    public static void Quicksort(int[] array, int leftside, int rightside)
    {
        //Chooses a random index
        int index = partition(array,leftside,rightside);

        if(leftside<index-1)
            Quicksort(array, leftside, index-1);
        if(index<rightside)
            Quicksort(array, index, rightside);
    }

    public static int partition(int [] array, int leftside, int rightside) //
    {
        /*
        Everything left of the choose index needs to be smaller than the chosen index value
        Everything right of the choosen index needs to larger than the choosen index value 
        Look for the the array in the left and the array in the right then switch them. THEN SWAP THEM

        - Special case - If they are equal to each other, just switch.
        */

        int chosen = array[(leftside+rightside)/2]; 
        
        while(leftside <= rightside) 
        {
            while(array[leftside] <chosen) // Looks for a number on the left side that is larger than choosen index
                leftside++;
            while(array[rightside]>chosen) // Looks for a number on the right side that is larger than choosen index
                rightside--;
            
            if(leftside<= rightside) // Puts the larger number on on the left of the pivot and smaller number on the right of the pivot.
            {
                swap(array, leftside, rightside);
                leftside ++;
                rightside--;
            }
        }
        return leftside; //Base case, just 1 number 
    }

    static void printArray(int[]array)
    {
        for(int i= 0; i<array.length; i++)
            System.out.print(array[i]+", ");
        System.out.println("");
    }

    // A function so that i do not need to write System.out.println all the time
    public static void write(String x)
      {
        System.out.println(x);
      }

    // A method that creates an array with random integers 
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

    // Just a text - too lazy to write the same things repetetively
    public static void introduction() 
    {
        write("--------------------");
        write("What do you want the program to do");
        write("1. Insertionsort the array");
        write("2. Mergesort the array");
        write("3. Quicksort the array");
        write("4. Quick and Merge   - sort and compare the results ");
        write("5. Normal and Special Quicksort. Compare the results");
        write("6. Special Quicksort only");
        write("7. Print the result");
        write("8. End the program");
    }

    //MergeSort 
    public static int[] mergeSort(int[]array, int CUTTOFF)
    {
        // Just return it 
        if(array.length <= 1) // Base case
        {
            return array;
        }
      
        //Nya base-case
        if(array.length <= CUTTOFF)
        {
            insertionSort(array);
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
        leftSide = mergeSort(leftSide,CUTTOFF);
        rightSide = mergeSort(rightSide,CUTTOFF);

        result = mergeTheSort(leftSide,rightSide);
        
        return result;
    }

    // Merges the results
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

    // Copies an array
    public static int[] copy(int array[])
    {
        int [] copycat = new int[array.length];
        for(int i= 0; i<array.length; i++)
        {
            copycat[i] = array[i];
        }   
        return copycat;
    }

    public static void main(String []args)
    {
        
        boolean on = true;
        Scanner in = new Scanner(System.in);
        int howMany = 0;       
        
        write("We need to create an array");
        write("How many elements would you like to store");
        howMany= in.nextInt();
        write("We will randomly generate integers in the array");
        int array[] = randomlyCreatedArrayWithIntegers(howMany);
       
        while(on==true)
        {       
            introduction();
            int command = in.nextInt();
            int cuttof = 0;
            
            switch(command)
            {
                case 1:
                    long timer11 = System.nanoTime();
                    insertionSort(array);
                    long timer12 = System.nanoTime();
                    long stoptimer1 = (timer12- timer11)/1000000; 
                    write("The array has been sorted through insertion sort");
                    System.out.println("The time it took was: " + stoptimer1 + " miliseconds");
                    break;
                case 2:
                    write("What cuff-off value would you like to have");
                    cuttof = in.nextInt();
                    long timer21 = System.nanoTime();
                    array = mergeSort(array,cuttof);
                    long timer22 = System.nanoTime();
                    long stoptimer2 = (timer22-timer21)/1000000;
                    write("The array has been sorted through mergesort");
                    System.out.println("The time it took was: " + stoptimer2 + " miliseconds");
                    break;
                case 3:
                    long timer31 = System.nanoTime(); 
                    Quicksort(array, 0, array.length-1);
                    long timer32 = System.nanoTime();
                    long stoptimer3 = (timer32-timer31)/1000000;
                    write("The array has been sorted through mergesort");
                    System.out.println("The time it took was: " + stoptimer3 + " milliseconds");
                    break;
                case 4:
                    int [] copycat = copy(array);
                    
                    long timer41 = System.nanoTime();
                    mergeSort(array,1); //Vanlig mergesort
                    long stoptimer41 = System.nanoTime();
                    long resultmerge = (stoptimer41-timer41)/1000000;

                    long timer42 = System.nanoTime();
                    Quicksort(copycat,0,array.length-1);
                    long stoptimer42 = System.nanoTime();
                    long resultquick = (stoptimer42-timer42)/1000000;
                    
                    System.out.println("Mergesort took " + resultmerge + " ms");
                    System.out.println("Quicksort took " + resultquick + " ms") ;
                    if(resultmerge<resultquick)
                    {
                        write("Merge was quicker by " + (resultquick - resultmerge) + " ms");
                    }
                    else
                    {
                        write("Quicksort was quicker by " + (resultmerge - resultquick) + " ms");
                    }
                    break;

                case 5:
                    int [] copycatt = copy(array);
                    break;
                case 6:
                    break;
                case 7: 
                    printArray(array);
                    break;
                case 8 :
                    on= false;
                    break;

                // En case som låter mig välja antalet element 
                default:
                    write("A number between 1 to 4. Not anything below or above");
            }
        }
        System.out.println("The program has stopped running...");
    }    
}
   
   
