
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task1 
{

    public static void main(String[] args) throws FileNotFoundException 
    {

        Scanner in = new Scanner(System.in);
        
        boolean on = true;


        System.out.println("This program will find paths to your destination");
        System.out.println("1- Run the program");
        System.out.println("2- End the progrma");

        SymbolGraph s_graph = new SymbolGraph("theData.txt");

        while(on)
        {
            System.out.println("This program will find paths to your destination");
            System.out.println("1- Run the program");
            System.out.println("2- End the progrma");    

            int command = in.nextInt();
            
            switch(command)
            {
                case 1:
                    System.out.println("Which state would you like to start from?");
                    
                    // Gör allt till stor bokstav
                    String startPoint = in.next().toUpperCase();
                    
                    //
                    int start_int = s_graph.index(startPoint);

                    System.out.println("Which state would you like to go to?");

                    String endPoint = in.next().toUpperCase();
                    int end_int = s_graph.index(endPoint);

                    Graph States2Int_graph = s_graph.G();

                    DepthFirstPaths search = new DepthFirstPaths(States2Int_graph, start_int);
                    if (!search.hasPathTo(end_int))
                        System.out.println("There is no paths!");
                    else {
                        for (int state : search.pathTo(end_int)) {
                            if (state == end_int)
                                System.out.print(s_graph.name(state));

                            else
                                System.out.print(s_graph.name(state) + "----->");
                        }
                        System.out.println(" ");
                    }
                    break;

                case 2:
                    on = false;
                    break;
                default:
                    System.out.println("A number between 1 and 2 ");
                    break;
            }
        }
        System.out.println("The program has stopped running...");
    }
    
    
}
