import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) throws FileNotFoundException 
    {

        File theText = new File("theData.txt");
        Scanner in = new Scanner(System.in);
        Scanner reader = new Scanner(theText);        
        
        boolean on = true;


        System.out.println("This program will find paths to your destination");
        System.out.println("1- Run the program");
        System.out.println("2- Check if the city is in the list");
        System.out.println("3- End the progrma");

        while(on)
        {
            int command = in.nextInt();
            
            switch(command)
            {
                case 1:
                case 2:
                case 3:
                default:
            }
        }
    }
    
}
