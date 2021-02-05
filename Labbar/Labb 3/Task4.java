import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task4 
{
    static int characters = 0;

    public static String filter(Scanner reader) 
    {
        StringBuilder sb = new StringBuilder();
        while (reader.hasNext()) 
        {
            String read = reader.next().toLowerCase();

            if (read.equals(" "))
             {
                characters++;
                break;
            }
            else
            {
                sb.append(read);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException 
    {
        // 
        System.out.println("----------");
        System.out.println(" Task 4- Lab 3 - Searching");
        System.out.println("The program allows user to find the word and how many times it occurs");

        // Loading in the file 
        File theText = new File("outPut.txt");        

        // Scanner classes that are used to read the file and user input
        Scanner reader = new Scanner(theText);
        Scanner in = new Scanner(System.in);

        // Asks for inputs
        System.out.println("How many words do you want to scan in the text from the textfile?");
        int total_words = in.nextInt();

        // Initiating the Binary Search Tree, 9950 unique words
        BinarySearchST<String, ArrayList<Integer>> BinarySearch = new BinarySearchST<>(9950);

        
        while(reader.hasNext())
        {
            reader.useDelimiter("");

            //Every char get's included
            String word = filter(reader);

            // If it doesn't contain the word
            if(!BinarySearch.contains(word))
            {
                // Create an arraylist inside the key, the array list will contain the index 
                BinarySearch.put(word, new ArrayList<Integer>());
                

                BinarySearch.get(word).add(characters);
                characters += word.length();
            }

            else
            {
                BinarySearch.get(word).add(characters);
                characters += word.length();
            }
        }

        System.out.println("What word do you want to look for ");
        
        String chosenWord = in.next().toLowerCase();
        if(BinarySearch.contains(chosenWord))
        {
            for(int i = 0; i<BinarySearch.get(chosenWord).size(); i++)
            {
                System.out.println(BinarySearch.get(chosenWord).get(i) + " ");
            }
        }
        else
        {
            System.out.println("The word is not in the text");
        }




    }
}
