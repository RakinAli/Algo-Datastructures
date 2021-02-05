import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class SymbolGraph 
{
    private ST<String, Integer> st; // String -> index
    private String[] keys; // index -> String
    private Graph G; // the graph which has been built by t

    public SymbolGraph(String stream) throws FileNotFoundException 
    {
        st = new ST<String, Integer>();
        Scanner in = new Scanner(new FileReader(stream)); // First pass

        while (in.hasNextLine()) // builds the index
        {
            String[] theText = in.nextLine().split(" "); // by reading strings
            for (int i = 0; i < theText.length; i++) // to associate each
                if (!st.contains(theText[i])) // distinct string
                    st.put(theText[i], st.size()); // with an index. - The size of the BST 
        }

        // The String array called Keys - Adds all the states in the array
        // 
        keys = new String[st.size()]; // Inverted index
        for (String name : st.keys()) // to get string keys
            keys[st.get(name)] = name; // is an array.
        
        
        G = new Graph(st.size());
        in = new Scanner(new FileReader(stream)); // Skannar in filen 
    
        
        while (in.hasNextLine()) // builds the graph
        {
            // Kommer lägga hela texten inuti a arrayn sedan dra ut dess integer value from symbolgraphsen.
            // Kommer sedan lägga Staten som var brevid.
            /*
            tex AL - FL. Kommer göra a[0] = Al sedan a[1] = FL och konnecta.
            */

            String[] a = in.nextLine().split(" "); // Splittrar varje string med " ". Alltså om det finns mellanslag då är det en seprarat string 
            int v = st.get(a[0]); // Drar ut första Noden
            for (int i = 1; i < a.length; i++) // Drar ut andra noden
                G.addEdge(v, st.get(a[i])); // Kopplar de tillsammans 
        }
    }

    public boolean contains(String s) 
    {
        return st.contains(s);
    }

    public int index(String s) 
    {
        return st.get(s);
    }

    public String name(int v) 
    {
        return keys[v];
    }

    public Graph G() 
    {
        return G;
    }
}
