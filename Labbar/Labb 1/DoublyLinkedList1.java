import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DoublyLinkedList1<Item> implements Iterable<Item> {
    // Attributes of the class
    private int length; // Number of elements on the list
    private Node top; // Before the first item
    private Node bottom; // After the last item

    // Konstruktör för classen - skapar en dubbel-länkad node
    public DoublyLinkedList1() {
        top = new Node();
        bottom = new Node();
        top.next = bottom; // Before the first item points to the last item
        bottom.prev = top; // THe last item points to the first node
    }

    private class Node {
        // Attributes of the Node class
        private Item item;
        private Node next;
        private Node prev;
    }

    // checks if the node is empty
    public boolean isEmpty() {
        return length == 0;
    }

    // Returns the length of the nodes
    public int size() {
        return length;
    }

    public void add(Item item) {
        Node last = bottom.prev; // Points to the second last Item
        Node x = new Node(); // Creates a new Item
        x.item = item; // Puts in the item in the node
        x.next = bottom; // Next of node X goes to bottom
        x.prev = last; // Previous of node X goes toe second last
        bottom.prev = x;
        last.next = x;
        length++;

        /*
         * För att den ska vara cirkulär måste last.prev peka på post och post.next peka
         * på last
         */
        bottom.next = top;
        top.prev = bottom;
    }

    public void addFirst(Item item) {
        Node firstNode = top.next; // Points to the second first Item
        Node x = new Node(); // Creates a new Item
        x.item = item; // Adds the item into x node
        x.next = firstNode; // Points to the first node
        x.prev = top; // Points to start

        firstNode.prev = x; // Points back to x insteadd of top
        top.next = x; // Top points to x instead of first node

        // To make the List circular
        bottom.next = top;
        top.prev = bottom;
    }

    public ListIterator<Item> iterator() {
        return new DoublyLinkedListIterator();
    }

    private class DoublyLinkedListIterator implements ListIterator<Item> {
        private Node current = top.next; // the node that is returned by next()
        private Node lastAccessed = null; // the last node to be returned by prev() or next()

        // Length of Linked list iterator
        private int index = 0;

        // Length has to be more than index
        public boolean hasNext() {
            return index < length;
        }

        // Index has to more than 0
        public boolean hasPrevious() {
            return index > 0;
        }

        // Moves behind
        public int previousIndex() {
            return index - 1;
        }

        // Returns which index
        public int nextIndex() {
            return index;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("Does not have next");
            lastAccessed = current;
            Item item = current.item;
            current = current.next;
            index++;
            return item;
        }

        public Item previous() {
            if (!hasPrevious())
                throw new NoSuchElementException("Does not have Previous");
            current = current.prev;
            index--;
            lastAccessed = current;
            return current.item;
        }

        // replace the item of the element that was last accessed by next() or
        // previous()
        // condition: no calls to remove() or add() after last call to next() or
        // previous()
        public void set(Item item) {
            if (lastAccessed == null)
                throw new IllegalStateException();
            lastAccessed.item = item;
        }

        // remove the element that was last accessed by next() or previous()
        // condition: no calls to remove() or add() after last call to next() or
        // previous()
        public void remove() {
            if (lastAccessed == null)
                throw new IllegalStateException("Doesn't point to anything - Illegal");
            Node x = lastAccessed.prev;
            Node y = lastAccessed.next;
            x.next = y;
            y.prev = x;
            length--;
            if (current == lastAccessed)
                current = y;
            else
                index--;
            lastAccessed = null;
        }

        // add element to list
        public void add(Item item) {
            Node x = current.prev;
            Node y = new Node();
            Node z = current;
            y.item = item;
            x.next = y;
            y.next = z;
            z.prev = y;
            y.prev = x;
            length++;
            index++;
            lastAccessed = null;
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append("[" + item + "] "); // Eget
        return s.toString();
    }

    // Creates a space for me
    public static void space() {
        System.out.println("");
    }

    // A method that printes out what to write- instedad of typing
    // system.out.println
    public static void write(String x) {
        System.out.println(x);
    }

    public static void main(String[] args) {
        // Interacts with the user
        write("This program will store a word you write into a linked list");
        space();
        write("The linked list will be a dubble linked list ");
        space();
        write("Write a word or a sentence - It will be stored in a String ");
        space();

        // Stores the user input in a String
        Scanner userInput = new Scanner(System.in);
        String userr = userInput.nextLine();

        // interacts with the user
        System.out.println("the word/sentence you typed was : ");
        System.out.print(userr);
        space();
        write("Your word/sentence will now be stored in a dubbel linked list");

        // Creates a dubbel linked list
        DoublyLinkedList1<String> dubbeltrubbel = new DoublyLinkedList1<String>();
        
        // Stores it in the dubbel linked list
        for(int i = 0; i<userr.length(); i++)
        {
            dubbeltrubbel.add(userr.substring(i, i+1));
        }

        // Getting the Iterator
        ListIterator<String> iterator = dubbeltrubbel.iterator();
       
        String firstItem = iterator.next();
        
        // Printing the List
        System.out.println(dubbeltrubbel);
        write("The length of the Dubbel linked list is " + dubbeltrubbel.size());

        //Testing the delete function
        write("We are going to delete the first element in the index to check if the remove function works");
        write("We are now going to test the remove function:");
        iterator.remove();
        System.out.println(dubbeltrubbel);
        
        //Testing the add function once again
        write("The length of the Double linked list is " + dubbeltrubbel.size());
        write("We will now add the deleted element back to the list");
        iterator.add(firstItem);
        System.out.println(dubbeltrubbel);
        write("The length of the list is " + dubbeltrubbel.size());
        space();
        
        space();
        write("We will now try to add elements at the front and back of the list");
        write("Add an element of type String at the end of list");
        write("It could be a word or a letter, just anything of type String");
        
        String userr2 = userInput.nextLine();

        // Adds the user input2 to the last of the list then adds the element.
        while(iterator.hasNext() )
        {
            iterator.next();
        }
        iterator.add(userr2);
        System.out.println(dubbeltrubbel);
    
        //Iterator pointers moves back until the first node. 
        write("We are now going to add element at the first ");
        write("Type a sentence or a word of type String");
        userr2=userInput.nextLine();
        while(iterator.hasPrevious())
        {
            iterator.previous();
        }
        iterator.add(userr2);
        System.out.println(dubbeltrubbel);
        write("The double linkedl list has elements" + dubbeltrubbel.size());

        space();
        write("Choose which element you want to delete");
    }
}



