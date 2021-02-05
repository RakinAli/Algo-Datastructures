import java.util.Scanner;


public class Graph
{   
    private int V; // number of vertices
    private int E; // number of edges
    private ST<String,Bag<String>> adj; // adjacency lists
    
    
    //Constructor if the parameter contains a Scanner
    public Graph(Scanner read)
    {
        this.V = 0;
        this.E = 0;

        // A new adjacency list
        adj = new ST<>();

        while(read.hasNext())
        {

            String state1 = read.next();
            String state2 = read.next();
            addEdge(state1, state2);
        }
    }

    public int V() 
    { 
        return V; 
    }

    public int E() 
    { 
        return E; 
    }
    
    public void addEdge(String v, String w)
    {

        // If the symbol table doesn't contain the state - just add it
        if(adj.contains(v))
        {
            Bag<String> edges = new Bag<>();
            this.adj.put(v, edges);
            V++;
        }

        if(!this.adj.contains(w))
        {
            Bag<String> edges = new Bag<>();
            this.adj.put(w, edges);
            V++;
        }

        this.adj.get(v).add(w);
        this.adj.get(w).add(v);
        this.E ++;

    }

    public Iterable<String> vertices()
    {
        return adj.keys();
    }

    public  Iterable<String> adj(String v)
    { 
        return adj.get(v);
    }
}
