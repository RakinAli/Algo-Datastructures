import java.util.Iterator;
import java.util.NoSuchElementException;

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> 
{
    private BST<Key, Value> st;

    
    public ST() 
    {
        st = new BST<Key, Value>();
    }

    public Value get(Key key) 
    {
        if (key == null)
            throw new IllegalArgumentException("calls get() with null key");
        return st.get(key);
    }

    public void put(Key key, Value val) 
    {
        if (key == null) 
            throw new IllegalArgumentException("calls put() with null key");
        else             
            st.put(key, val);
    }

    public void delete(Key key) 
    {
        if (key == null)
            throw new IllegalArgumentException("calls delete() with null key");
        st.delete(key);
    }

    public void remove(Key key) 
    {
        if (key == null)
            throw new IllegalArgumentException("calls remove() with null key");
        st.delete(key);
    }

    public boolean contains(Key key) 
    {
        if (key == null)
            throw new IllegalArgumentException("calls contains() with null key");
        return st.contains(key);
    }

    public int size() 
    {
        return st.size();
    }

    public boolean isEmpty() 
    {
        return size() == 0;
    }

    public Iterable<Key> keys() {
        return st.keys();
    }


    public Iterator<Key> iterator() 
    {
        return st.keys().iterator();
    }

    /**
     * Returns the smallest key in this symbol table.
     *
     * @return the smallest key in this symbol table
     * @throws NoSuchElementException if this symbol table is empty
     */
    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("calls min() with empty symbol table");
        return st.min();
    }

    /**
     * Returns the largest key in this symbol table.
     *
     * @return the largest key in this symbol table
     * @throws NoSuchElementException if this symbol table is empty
     */
    public Key max() 
    {
        if (isEmpty())
            throw new NoSuchElementException("calls max() with empty symbol table");
        return st.max();
    }
}
