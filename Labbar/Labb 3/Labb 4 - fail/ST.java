public class ST<Key extends Comparable<Key>, Value>  
{
    private BinarySearchST<Key, Value> st;

    public ST() 
    {
        st = new BinarySearchST<Key, Value>();
    }

    public Value get(Key key) 
    {
        if (key == null)
            throw new IllegalArgumentException("calls get() with null key");
        return st.get(key);
    }

    public void put(Key key, Value val) 
    {
        st.put(key, val);
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

    public Iterable<Key> keys() 
    {
        return st.keys();
    }

}
