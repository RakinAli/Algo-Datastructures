public class DepthFirstPaths
{
    private ST<String,Boolean> marked;
    private ST<String,Integer> distance;
    private ST<String, String> edgeTo; // the number of edges
    private final String destination;
    private int count; 

    public DepthFirstPaths(Graph g, String goal )
    {
        this.marked = new ST<>(); // A new Symbol table for marked 
        this.distance = new ST<>();
        this.edgeTo = new ST<>();
        this.destination = goal;
        this.count = 0;
        this.dfs(g, goal);
    }

    private void dfs(Graph G, String goal)
    {

        this.count ++;
        this.marked.put(goal, true);

        for(String check : Graph.adj(goal))
        {
            // If it does not contain mark then mark it as unvisited
            if(!this.marked.contains(check))
                marked.put(check, false);
            

        }

      
    }

    public boolean hasPathTo(int v)
    { 
        return marked[v]; 
    }
    
    
    public Iterable<Integer> pathTo(int v)
    {
        
    }
}