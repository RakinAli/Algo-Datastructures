/*
* - The point with this task is to show the flaws of using hashcode in Java
*/ 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Task3 
{

    public static void main(String[] args) throws FileNotFoundException
    {
        System.out.println("----------");
        System.out.println(" Task 3- Lab 3 - Searching");
        System.out.println("Write a program that shows how evenly the built-in hashcode() function for strings in Java distributes the hashcodes for the words found in the text.");

        // Loa1ding in the file 
        File theText = new File("outPut.txt");        

        // Scanner classes that are used to read the file and user input
        Scanner reader = new Scanner(theText);
        Scanner in = new Scanner(System.in);

        // Asks for inputs
        System.out.println("How many words do you want to scan in the text from the textfile?");
        int total_words = in.nextInt();

        // Initiating the Binary Search Tree, Queues for HAsh and word
        BST<Integer, String> BST = new BST<>();
        Queue<Integer> Hashqueue = new Queue<Integer>();
        Queue<String> wordQue = new Queue<String>();
        
        // Putting all the words in the Symbol Table and looking for hash_crashes
        int words = 0;
        int minlength = 1;
        int key;
        String word;
        int collisions = 0;

        while(reader.hasNext() && words <total_words)
        {
            word = reader.next().toLowerCase();

            // If the word only has word length 1
            if(word.length() < minlength)
                continue;

            words++;
            
            //Creating an index for the key. Has to be unique, No other word may occupy that array
            key = word.hashCode();

            // If the Hash value is unique, just place it 
            if(!BST.contains(key))
            {
                BST.put(key, word);
            }

            // If the index isn't unique but it isn't the same word 
            if(!BST.get(key).equals(word))
            {
                System.out.println("Collision detected!");
                System.out.println(word + " - " + word.hashCode());
                System.out.println("");
                collisions++;
                Hashqueue.enqueue(key);
                wordQue.enqueue(word);
            }
        }
        if(collisions==0)
            System.out.println("A unique Hashcode was distributed everywhere");
        else
        {
            System.out.println("There were " + collisions + " many collisions");
            System.out.println("The Hash values that were not unique are");
            String hash = Hashqueue.toString();
            System.out.println(hash);

            System.out.println("These are the words that were collided");
            String crashWord = wordQue.toString();
            System.out.println(crashWord);
        }
    }
}
