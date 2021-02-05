/*
 - Task 1 - Ali Rakin Cinte 2
*/

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class BinarySearchST<Key extends Comparable<Key>, Value> 
{
    // Class attritubtes
    private static final int INIT_CAPACITY = 2; // Default size if not initiaizlied
    private Key[] keys; // The keys 
    private Value[] values; // What the key unlocks
    private int N; // Amount of Key_locks

    public BinarySearchST() 
    {
        this(INIT_CAPACITY);
    }

    public BinarySearchST(int capacity) 
    {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    // Returns the number of key-value pairs in this symbol table
    public int size() 
    {
        return N;
    }

    /* Size of the Ordered Search Array. You need to know the 
    * - root and the worst case scenario key
    */
    public int size(Key low, Key high) 
    {
        if (low == null)
            throw new IllegalArgumentException("first argument to size() is null");
        if (high == null)
            throw new IllegalArgumentException("second argument to size() is null");

        // If low is higher than high then it isn't ordered
        if (low.compareTo(high) > 0)
            return 0;
        
        //If it has High. Compare with low 
        if (contains(high))
            return rank(high) - rank(low) + 1; // Rank low will be in index 0
        else
            return rank(high) - rank(low);
    }

    //
    public Value get(Key key) 
    {
        // If the ordered array is empty
        if (isEmpty())
            return null;
        
        int i = rank(key); //Finds the "correct" place in the array

        // Has to match the parameter key. If it has found the key 
        if (i < N && keys[i].compareTo(key) == 0)
            return values[i];
        else
            // Has not found the key
            return null;
    }

    // Returns the number of keys that are strictly less than the chosen key and the index 
    // Value will place it where the right side is larger than the 
    private int rank(Key key) 
    {
        int Low = 0;
        int High = N - 1;
        
        // Base case - While high is larger than low
        while (Low <= High)
        {
            int mid = Low + (High - Low) / 2; // Pivot. The middle of the array 
            int cmp = key.compareTo(keys[mid]);

            if (cmp < 0) // Belongs on the left side of the middle
                High = mid - 1;
            else if (cmp > 0) // Belongs on the right side of the blittle and keep looking
                Low = mid + 1;
            
            // If compare == 0 then it happens to be in the middle - Base case
            else
                return mid;
        }
        // If not found - Return low. 
        return Low;
    }

    // Checks if the sumbol table is empty
    private boolean isEmpty() 
    {
        return size() == 0;
    }

    // Search for key. Update value if found, grow table if new
    public void put(Key key, Value val) 
    {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null"); 

        // If the key value is zero then just delete the key 
        if (val == null) 
        {
            delete(key);
            return;
        }

        // Find the positionen in Y
        int i = rank(key);

        // if key is already in table, just update the value
        if (i < N && keys[i].compareTo(key) == 0) 
        {
            values[i] = val;  // Adds it in the index 
            return;
        }

        // If the array is full then double the array
        if (N == keys.length)
            resize(2 * keys.length);

        //Move everything to the right by one spot. Makes space for the key to be inserted
        // Everything in-front of the index will be moved. Everything behind untouched
        for (int j = N; j > i; j--) 
        {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        
        //Place them in 
        keys[i] = key;
        values[i] = val;
        N++;

        assert check();
    }

    // Returns the smallest key in the symbol talbe
    public Key min() 
    {
        if (isEmpty())
            throw new NoSuchElementException("Called min() with empty symbol table");
        return keys[0];
    }

    // Returns the largest key in this symbol table
    public Key max() 
    {
        if (isEmpty())
            throw new NoSuchElementException("Called Max with empty symbol table");
        return keys[N - 1];
    }

    // Returns a selected index in key []
    public Key select(int k) 
    {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("Called Select() with invalid arugment: " + k);
        }
        return keys[k];
    }

    // Returns the smallest key in the synbol table greater than or equal to key
    public Key ceiling(Key key) 
    {
        if (key == null)
            throw new IllegalArgumentException("argument to ceiling() is null");

        int i = rank(key);
        if (i == N)
            return null;
        else
            return keys[i];
    }

    // returns the largest key in the symbol less than or equal to key
    public Key floor(Key key) {
        if (key == null)
            throw new IllegalArgumentException("Arugment to floor() is null");

        int i = rank(key);

        if (i < N && key.compareTo(keys[i]) == 0)
            return keys[i];

        if (i == 0)
            return null;
        else
            return keys[i - 1];
    }

    public void delete(Key key) 
    {
        if (key == null)
            throw new IllegalArgumentException("Arguemnt to delte() is null");
    
        // If the array list is empty
       if (isEmpty())
            return;

        // Compute the position that needs to be deleted 
        int i = rank(key);

        // Key not in table
        if (i == N || keys[i].compareTo(key) != 0) 
        {
            return;
        }


        for (int j = i; i < N - 1; j++)
        {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }

        N--;

        keys[N] = null;
        values[N] = null;

        // Resize if 1/4 full
        if (N > 0 && N == keys.length / 4)
            resize(keys.length / 2);

        assert check();
    }

    // Removes the smallest key and associated value from the symbol table
    public void deleteMin() 
    {
        if (isEmpty())
            throw new NoSuchElementException("Symbol table Error");
        delete(min());
    }

    // Deletes the largest key and associated value from this Symbol table
    public void deleteMax() 
    {
        if (isEmpty())
            throw new NoSuchElementException("Symbol Table underflow Error");
        delete(max());
    }

    private void resize(int capacity) 
    {
        // Makes sure that the capacity is larger than current size
        assert capacity >= N; // checks if larger than the size of SymbolTable
        Key[] tempk = (Key[]) new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];

        // Fills upp the temporaly Arrays
        for (int i = 0; i < N; i++) 
        {
            tempk[i] = keys[i];
            tempv[i] = values[i];
        }

        values = tempv;
        keys = tempk;
    }

    public boolean contains(Key key) 
    {
        if (key == null) 
        {
            throw new IllegalArgumentException("Argument to contains() is null");
        }
        // If not null it exists
        return get(key) != null;
    }

    public Iterable<Key> keys() 
    {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key low, Key high) 
    {
        if (low == null)
            throw new IllegalArgumentException("first argument to keys() is null");
        if (high == null)
            throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        if (low.compareTo(high) > 0)
            return queue;
        for (int i = rank(low); i < rank(high); i++)
            queue.enqueue(keys[i]);
        if (contains(high))
            queue.enqueue(keys[rank(high)]);
        return queue;
    }

    // Checks if everything is in place. Borth s
    private boolean check() 
    {
        return isSorted() && rankCheck();
    }

    // Checks if the items in the array are in ascending order
    private boolean isSorted() 
    {
        for (int i = 1; i < size(); i++)
            if (keys[i].compareTo(keys[i - 1]) < 0)
                return false;
        return true;
    }

    // Checks that rank(Select(i)) = i
    private boolean rankCheck() 
    {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i)))
                return false;

        for (int i = 0; i < size(); i++)
            if (keys[i].compareTo(select(rank(keys[i]))) != 0)
                return false;
        return true;
    }
}