import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> 
{
    private Node root; // Root of the Binary search tree

    // A nested class into BST 
    private class Node
    {
        private Key key; // Sorted by key
        private Value value; // The "lock" which the Key opens 
        private Node left, right; // Left and right subtrees
        private int size; // The number of nodes in a subtree

        //Constructor for the private class
        public Node(Key key, Value value, int size)
        {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public BST()
    {

    }

    // Checks if the Binary search tree is empty
    public boolean isEmpty()
    {
        return size() == 0;
    }

    //  Returns the size from the root
    public int size()
    {
        return size(root);
    }

    // Reutrns the size from a specific node
    private int size(Node x)
    {
        if(x==null)
        {
            return 0;
        }
        else
        {
            return x.size;
        }
    }

    //Checks the value of a certain key
    public boolean contains(Key key)
    {
        if(key==null)
            throw new IllegalArgumentException("Arguemnts to contains() is null");
        return get(key) != null;
    }

    //Returns the value associated with a given key
    public Value get(Key key)
    {
        return get(root,key);
    }

    /*Check the Node. Smaller than? Go left. Larder than ? Go right 
        If nothing then just return the value 
    */
    private Value get(Node x, Key key)
    {
        if(key==null)
            throw new IllegalArgumentException("Calls get() with a null key");
        if(x==null)
            return null;
        
        int cmp = key.compareTo(x.key);
        
        if(cmp<0)
            return get(x.left,key);
        
        else if(cmp>0)
            return get(x.right,key);
        
        else
            return x.value;
    }

    /* Inserts a specified key-value pair in to the symbol table, Writes
    over old values with the new value if the symbol table already contains the
    specified key. Deletes the specified key from the symbol tble
    if the specified value is null.

    */
    public void put(Key key, Value value)
    {
        if(key ==null)
            throw new IllegalAccessError("Call put() with a Null key");
        
        if(value == null)
        {
            delete(key);
            return;
        }
        root = put(root,key,value);
        assert check();
    }

    //Compares with the root-subtr and places the node depending on the size 
    private Node put(Node x, Key key, Value value)
    {
        if(x == null) 
            return new Node(key, value, 1); // If there's no root or sub-branches then it places it there 
        
        int cmp = key.compareTo(x.key); //Compare to Root, 
        if (cmp < 0) // If smaller than root place on the left side 
            x.left  = put(x.left,  key, value);
        
        // If larger than root, place on the right side. remember now that the X.right becomes the root. Recursive funntion
        else if (cmp > 0) 
            x.right = put(x.right, key, value);
    
        // Exactly same key - Change the value of the key then
        else             
             x.value   = value;
        
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    //Deletes the smallest key and the associated value from the symbol tre
    public void deleteMin()
    {
        if(isEmpty())
            throw new NoSuchElementException("Symbol Table underflow");
        root = deleteMin(root);
        assert check();
    }

    // Deletes the least used word 
    private Node deleteMin(Node x) 
    {
        if (x.left == null) 
            return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    // Deletes the maximun used word
    public void deleteMax() 
    {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
        assert check();
    }

    //Goes to the right - 
    private Node deleteMax(Node x) 
    {
        if (x.right == null) 
            return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) 
    {
        if (key == null) 
            throw new IllegalArgumentException("calls delete() with a null key");
        root = delete(root, key);
        assert check();
    }

    private Node delete(Node x, Key key) 
    {
        if (x == null) 
        return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) 
            x.left  = delete(x.left,  key);
        else if (cmp > 0) 
            x.right = delete(x.right, key);
        else 
        { 
            if (x.right == null) 
                return x.left;
            if (x.left  == null)
                return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        } 
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    } 

    public Key min() 
    {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    } 

    private Node min(Node x) 
    { 
        if (x.left == null) 
            return x; 
        else              
            return min(x.left); 
    }

    public Key max() 
    {
        if (isEmpty()) 
            throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }
    
    private Node max(Node x) 
    {
        if (x.right == null) 
            return x; 
        else    
            return max(x.right); 
    } 

    public Key floor(Key key) 
    {
        if (key == null) 
            throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) 
            throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) 
            throw new NoSuchElementException("argument to floor() is too small");
        else 
            return x.key;
    } 
   
    private Node floor(Node x, Key key) 
    {
        if (x == null) 
            return null;
        
        int cmp = key.compareTo(x.key);
        
        if (cmp == 0) 
            return x;
        if (cmp <  0) 
            return floor(x.left, key);
        
        Node t = floor(x.right, key); 
        if (t != null) 
            return t;
        else 
            return x; 
    }

    public Key floor2(Key key) 
    {
        Key x = floor2(root, key, null);
        if (x == null) 
            throw new NoSuchElementException("argument to floor() is too small");
        else 
            return x;
    }

    private Key floor2(Node x, Key key, Key best) 
    {
        if (x == null)
            return best;
        int cmp = key.compareTo(x.key);
        
        if (cmp  < 0) 
            return floor2(x.left, key, best);
        else if (cmp  > 0) 
            return floor2(x.right, key, x.key);
        else              
            return x.key;
    } 

    public Key ceiling(Key key) 
    {
        if (key == null) 
            throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) 
            throw new NoSuchElementException("calls ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) 
            throw new NoSuchElementException("argument to floor() is too large");
        else 
            return x.key;
    }

    private Node ceiling(Node x, Key key)
    {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) 
            return x;
        if (cmp < 0) 
        { 
            Node t = ceiling(x.left, key); 
            if (t != null) 
                return t;
            else
                 return x; 
        } 
        return ceiling(x.right, key); 
    }
    
    public Key select(int rank) 
    {
        if (rank < 0 || rank >= size()) 
        {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(root, rank);
    }

    private Key select(Node x, int rank) 
    {
        if (x == null) 
            return null;
        int leftSize = size(x.left);
        if  (leftSize > rank) 
            return select(x.left,  rank);
        else if (leftSize < rank) 
            return select(x.right, rank - leftSize - 1); 
        else         
             return x.key;
    }

    public int rank(Key key) 
    {
        if (key == null)
            throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    } 

    // Number of keys in the subtree less than key.
    private int rank(Key key, Node x) 
    {
        if (x == null)
            return 0; 
        int cmp = key.compareTo(x.key); 
        if (cmp < 0) 
            return rank(key, x.left); 
        else if (cmp > 0)
            return 1 + size(x.left) + rank(key, x.right); 
        else              
            return size(x.left); 
    } 

    public Iterable<Key> keys() 
    {
        if (isEmpty()) 
            return new Queue<Key>();
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) 
    {
        if (lo == null) 
            throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) 
            throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    } 

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) 
    { 
        if (x == null) 
            return; 
       
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key); 
        
        if (cmplo < 0) 
            keys(x.left, queue, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) 
            queue.enqueue(x.key); 
        if (cmphi > 0) 
            keys(x.right, queue, lo, hi); 
    } 

    public int size(Key lo, Key hi) 
    {
        if (lo == null) 
            throw new IllegalArgumentException("first argument to size() is null");
       
        if (hi == null) 
            throw new IllegalArgumentException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        
        if (contains(hi)) 
            return rank(hi) - rank(lo) + 1;
        else              
            return rank(hi) - rank(lo);
    }

    public int height() 
    {
        return height(root);
    }
    
    private int height(Node x) 
    {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public Iterable<Key> levelOrder() 
    {
        Queue<Key> keys = new Queue<Key>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) 
        {
            Node x = queue.dequeue();
            if (x == null) 
                continue;
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }

    private boolean check() 
    {
        if (!isBST())       
             System.out.println("Not in symmetric order");
        if (!isSizeConsistent()) 
            System.out.println("Subtree counts not consistent");
        if (!isRankConsistent()) 
            System.out.println("Ranks not consistent");
        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    private boolean isBST() 
    {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key min, Key max) 
    {
        if (x == null) 
            return true;
        if (min != null && x.key.compareTo(min) <= 0) 
            return false;
        if (max != null && x.key.compareTo(max) >= 0) 
            return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    } 

    private boolean isSizeConsistent() 
    { 
        return isSizeConsistent(root); 
    }

    private boolean isSizeConsistent(Node x) 
    {
        if (x == null) 
            return true;
        if (x.size != size(x.left) + size(x.right) + 1) 
            return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    } 

    private boolean isRankConsistent() 
    {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) 
                return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) 
                return false;
        return true;
    }

    public static void main(String[] args)
    {
        
    }

}

