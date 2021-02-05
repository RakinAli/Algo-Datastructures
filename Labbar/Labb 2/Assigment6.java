import java.util.Scanner;

public class Assigment6 
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

    //Insertion-sort 
    public static void insertionSort(int [] fixArray)
    {
        long swaps = 0;
        int arrayLength = fixArray.length;
        for(int i = 1; i<arrayLength; i++)
        {   
            for(int j = i; j>0 && fixArray[j]<fixArray[j-1]; j--)
            {
                int jLag = j-1;
                swap(fixArray,j,j-1);
                swaps++;
            }
        }
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
        write("2. A user created array");
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
        boolean on = true;
        Scanner in = new Scanner(System.in);
        int extraChoice = 0; 
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
                    printArray(array);
                    break;
                case 4:
                    on = false;
                    break;

                // En case som låter mig välja antalet element 
                default:
                    write("A number between 1 to 4. Not anything below or above");
            }
        }
        System.out.println("The program has stopped running...");
    }    
}
