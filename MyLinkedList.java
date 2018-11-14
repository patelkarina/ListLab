import java.util.Iterator;
import java.util.ListIterator;

/**
 * This class contains the methods for the MyLinkedList<E>. 
 * 
 */
public class MyLinkedList<E>
{
    private DoubleNode first;
    private DoubleNode last;
    private int size;

    /**
     * Constructor for the MyLinkedList<E> class 
     * 
     */
    public MyLinkedList()
    {
        first = null;
        last = null;
        size = 0;
    }

    public String toString()
    {
        DoubleNode node = first;
        if (node == null)
            return "[]";
        String s = "[";
        while (node.getNext() != null)
        {
            s += node.getValue() + ", ";
            node = node.getNext();
        }
        return s + node.getValue() + "]";
    }

    /**
     * Starting from first, returns the node with the given index 
     * @precondition 0 <= index <= size/2
     * @param index the index where we want to get the node 
     * @return the node at the given index 
     * 
     */
    private DoubleNode getNodeFromFirst(int index)
    {
        DoubleNode temp = first;
        while (index > 0)
        {
            index--;
            temp = temp.getNext();
        }
        return temp;
    }

    /**
     * Starting from last, returns the node with the given index
     * @precondition 0 <= index <= size/2
     * @param index the index where we want to get the node 
     * @return the node at the given index 
     * 
     */
    private DoubleNode getNodeFromLast(int index)
    {
        DoubleNode temp = last;
        int count = size - 1;
        while (count > index)
        {
            count--;
            temp = temp.getPrevious();
        }
        return temp;
    }

    /**
     * Starts from first or last (depending on which is closer), returns the node with the given index 
     * @param index the index where we want to get the node
     * @return the node at the given index 
     * 
     */
    private DoubleNode getNode(int index)
    {
        if (index < size/2)
        {
            return this.getNodeFromFirst(index); 
        }
        return this.getNodeFromLast(index);
    }

    /**
     * Returns the size of the list 
     * @return size 
     * 
     */
    public int size()
    {
        return size;
    }

    /**
     * Retrieves the value of the node at the specified index
     * @param index the index where we want to find the value of the node
     * @return the value of the node at index 
     * 
     */
    public E get(int index)
    {
        return (E)this.getNode(index).getValue();
    }

    /**
     * Replaces the element at position index with obj
     * @param index the index where we want to find the node
     * @param obj the new value we want to replace with the old value
     * @return the element formerly at the specified position
     * 
     */
    public E set(int index, E obj)
    {
        DoubleNode d = this.getNode(index);
        E prev = (E) d.getValue();
        d.setValue(obj);
        return prev;
    }

    /**
     * Appends obj to the end of the list
     * @param obj the node we want to add to the list 
     * @return true 
     * 
     */
    public boolean add(E obj)
    {
        DoubleNode d = new DoubleNode(obj);
        d.setNext(null);
        d.setPrevious(last);
        if (last != null)
        {
            last.setNext(d);
        }
        if (first == null)
        {
            first = d;
        }
        last = d;
        size++;
        return true;
    }

    /**
     * Removes element from position index, moving elements at position
     * index + 1 and higher to the left and adjusts size
     * @param index the index where we want to remove the node 
     * @return the element formerly at the specified position
     * 
     */
    public E remove(int index)
    {
        DoubleNode r = this.getNode(index);
        if (size == 1)
        {
            first = null;
            last = null;
            size--;
        }
        else if (index == 0)
        {
            this.removeFirst();
        }
        else if (index == size - 1)
        {
            this.removeLast();
        }
        else
        {
            DoubleNode d = this.getNode(index);
            d.getPrevious().setNext(d.getNext());
            d.getNext().setPrevious(d.getPrevious());
            size--; 
        }
        return (E)r.getValue();
    }

    /**
     * Inserts obj at position index, moving elements at position index
     * and higher to the right and adjusts size
     * @precondition 0 <= index <= size
     * @param index the index where we want to add the node to 
     * @param obj the node we want to add to the list 
     * 
     */
    public void add(int index, E obj)
    {
        DoubleNode r = this.getNode(index);
        if (size == 0)
        {
            DoubleNode d = new DoubleNode(obj);
            first = d;
            last = first;
            size++;
            return;
        }
        if (index == size - 1)
        {
            this.add(obj);
            return;
        }
        else
        {
            DoubleNode d = first;
            while (index > 1)
            {
                d = d.getNext();
                index--;
            }
            DoubleNode e = new DoubleNode(obj);
            d.setNext(e);
            e.setPrevious(d);
            e.setNext(e.getNext());
        }
        size++;
    }

    /**
     * Adds a node to the first position of the list
     * @param obj the node we want to add to the list 
     * 
     */
    public void addFirst(E obj)
    {
        this.add(0, obj);
    }

    /**
     * Adds a node to the last position of the list  
     * @param obj the node we want to add to the list 
     * 
     */
    public void addLast(E obj)
    {
        this.add(size - 1, obj);
    }

    /**
     * Gets the first node of the list 
     * @return first 
     * 
     */
    public E getFirst()
    {
        return (E)first;
    }

    /**
     * Gets the last node of the list 
     * @return last 
     * 
     */
    public E getLast()
    {
        return (E)last;
    }

    /**
     * Removes the first node of the list
     * @return the value of the node just removed 
     * 
     */
    public E removeFirst()
    {
        DoubleNode d = first;
        first = first.getNext();
        first.setPrevious(null);
        size--;
        return (E)d.getValue();
    }

    /**
     * Removes the last node of the list
     * @return the value of the node just removed 
     * 
     */
    public E removeLast()
    {
        DoubleNode d = last;
        last = last.getPrevious();
        last.setNext(null);
        size--;
        return (E)d.getValue();
    }

    /**
     * The iterator for the MyLinkedList<E> class
     * @return MyLinkedListIterator 
     * 
     */
    public Iterator<E> iterator()
    {
        return new MyLinkedListIterator();
    }

    private class MyLinkedListIterator implements Iterator<E>
    {
        private DoubleNode nextNode;

        public MyLinkedListIterator()
        {
            throw new RuntimeException("INSERT MISSING CODE HERE");
        }

        public boolean hasNext()
        {
            throw new RuntimeException("INSERT MISSING CODE HERE");
        }

        public E next()
        {
            throw new RuntimeException("INSERT MISSING CODE HERE");

            //(You will need to promise the return value is of type E.)
        }

        //postcondition: removes the last element that was returned by next
        public void remove()
        {
            throw new RuntimeException("INSERT MISSING CODE HERE");
        }
    }
}