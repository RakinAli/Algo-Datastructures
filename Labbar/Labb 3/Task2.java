/*
* - Rakin Ali Cinte 2 
* - Runs frequency counter, inserts every word then later looks for the 
* - The most frequent word 
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/*
* - Heavily inspired by Princeton code for Frequency counter and 
* - the main in their BinarysearchST and Binary Tree. 
*/
public class Task2 
{
    // A frequency counter for objects of BinarySearchST
    public static void frequencyCounter(BinarySearchST <String, Integer> BinarySearchST, Scanner text, int wordMax)
    {
        int uniquewords = 0;
        int words =0;
        int minLength = 1;

        //If the text has strings and less than the word max then do 
        while(text.hasNext() && words < wordMax)
        {
            String key = text.next().toLowerCase();
            
            // If there's only one letter then take the next letter
            if(key.length() < minLength)
            {
                continue;
            }
            
            // Increase the word counter
            words++;
            
            // If the Symbol table already contains the key then just increase the word - frequency, frequency 
            if(BinarySearchST.contains(key))
                BinarySearchST.put(key, BinarySearchST.get(key) + 1);

            else 
            {
                BinarySearchST.put(key, 1);
                uniquewords++;
            }
        }
        String max = ""; // Just create a word
        BinarySearchST.put(max,0); // The value of the string is zero 

        for(String word: BinarySearchST.keys()) // Iteratates through every word
        {
            //Searches for the key - lock with highest value 
            if(BinarySearchST.get(word)>BinarySearchST.get(max)) // If the word - freq > get(max) then max = the new word
                max = word;
        }

        System.out.println("Number of unique words: " + uniquewords);
        System.out.println("Number of words: " + words);
        System.out.println("The most frequent word is:" + "'" + max + "'" +"and it's used " + BinarySearchST.get(max) + " times");
        System.out.println("");

    }

    // Frequency counter for objects of BST 
    public static void frequencyCounter(BST<String, Integer> BST, Scanner text, int wordMax)
    {
        // Attributes
        int uniquewords = 0;
        int words =0;
         int minLength = 1;

        //If the text has strings and less than the word max then do the while loop
        while(text.hasNext() && words < wordMax)
        {
            // Key stores the word
            String key = text.next().toLowerCase();

            // If there's only one letter then take the next letter
            if(key.length() < minLength)
            {
                continue;
            }
            words++;

            // If it already contains the key
            if(BST.contains(key))
            {
                BST.put(key, BST.get(key)+1); // Increases the frequency of the word
            }

            // If not, it's unique 
            else
            {
                BST.put(key,1); // Puts it on the Binary tree 
                uniquewords++;
            }
        }

        // Just create a word 
        String max = "";
        BST.put(max, 0); // It will become the root 
        for( String word: BST.keys())
        {
            if(BST.get(word)> BST.get(max))
                max= word;
        }

        System.out.println("Number of unique words: " + uniquewords);
        System.out.println("Number of words: " + words);
        System.out.println("The most frequent word is:" + "'" + max + "'" +"and it's used " + BST.get(max) + " times");
        System.out.println("");
    }

    public static void main( String[] args) throws FileNotFoundException
    {
        System.out.println("-------------------------");
        System.out.println(" Task 2 in lab 3 - Searching");
        System.out.println(" Compare Binary search and Binary serach tree");

        // Loading in the file
        File theText = new File("outPut.txt");
        
        // Scanner classes that are used to read the file and user input
        Scanner read1 = new Scanner(theText); // Used for 
        Scanner read2 = new Scanner(theText);
        Scanner in = new Scanner(System.in);

        // Asks for user input
        System.out.println("How many words do you want to scan in the text. ");
        int Total_words= in.nextInt(); 

        // Initiating the binary search tree and search
        BinarySearchST<String, Integer> binarySearchST = new BinarySearchST<String, Integer>();
        BST<String,Integer> BST = new BST<String, Integer>();

        long timer1 = System.nanoTime();
        frequencyCounter(binarySearchST, read1,Total_words);
        long endTimer1 = System.nanoTime();
        long timeElapsed1 = (endTimer1-timer1)/1000000;

        long timer2 = System.nanoTime();
        frequencyCounter(BST, read2, Total_words);
        long endtimer2 = System.nanoTime();
        long timeElapsed2 = (endtimer2 - timer2)/1000000;

        System.out.println("________________________-");
        System.out.println("Symbol table: " + timeElapsed1 + " ms");
        System.out.println("Binary Search Tree: " + timeElapsed2 + " ms");
        in.close();

    }    
}
