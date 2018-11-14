import java.util.Iterator;
import java.util.ListIterator;

/**
 * This class contains the methods for the MyArrayList<E>. 
 * 
 */
public class MyArrayList<E>
{
    private int size;
    private Object[] values;  

    /**
     * Constructor for the MyArrayList<E> class 
     * 
     */
    public MyArrayList()
    {
        size = 0;
        values = new Object[1];
    }

    public String toString()
    {
        if (size == 0)
            return "[]";

        String s = "[";
        for (int i = 0; i < size - 1; i++)
            s += values[i] + ", ";
        return s + values[size - 1] + "]";
    }

    /**
     * Doubles the size of an array with the previous elements copied 
     * into it 
     * @return the array with doubled size 
     */
    private void doubleCapacity()
    {
        int length = values.length;
        Object [] valuesFinal = new Object[length * 2];
        for (int i = 0; i < size; i++)
        {
            valuesFinal[i] = values[i];
        }
        values = valuesFinal;
    }

    /**
     * Returns the length of the array 
     * @return the array length 
     */
    public int getCapacity()
    {
        return values.length;
    }

    /**
     * Returns the size of the array 
     * @return size 
     */
    public int size()
    {
        return size;
    }

    /**
     * Retrieves the value at the specified index 
     * @param index the index where we want to find the value 
     * @return the value at the index 
     */
    public E get(int index)
    {
        return (E)values[index];
    }

    /**
     * Replaces the element at position index with obj 
     * @param index the index where we want to find the value
     * @param obj the new value we want to replace the old value with 
     * @return the element formerly at the specified position 
     */
    public E set(int index, E obj)
    {
        E temp = this.get(index);
        values[index] = obj;
        return temp; 
    }

    /**
     * Appends obj to the end of the list 
     * @param obj the value we want to add to the list 
     * @return true 
     */
    public boolean add(E obj)
    {
        if (getCapacity() == size)
        {
            doubleCapacity();
        }
        values[size] = obj;
        size++;
        return true;
    }

    /**
     * Removes element from position index, moving elements at position
     * index + 1 and higher to the left and adjusts size 
     * @param index the index where we want to remove the element from 
     * @return the element formerly at the specified position 
     */
    public E remove(int index)
    {
        E temp = this.get(index);
        for (int i = index; i < size - 1; i++)
        {
            values[i] = values[i + 1];
        }
        size--;
        return temp; 
    }

    /**
     * The iterator for the MyArrayList<E> class 
     * @return the MyListInterator
     */
    public Iterator<E> iterator()
    {
        return new MyArrayListIterator();
    }

    /**
     * Inserts obj at position index, moving elements at position index
     * and higher to the right and adjusts size 
     * @precondition 0 <= index <= size 
     * @param index the index where we want to add the element to 
     * @param obj the value we want to add to the list 
     */
    public void add(int index, E obj)
    {
        if (getCapacity() == size)
            doubleCapacity();
        for (int i = size; i > index; i--)
        { 
            values[i+1] = values[i];
        }
        values[index] = (Object) obj;
        size++; 
    }

    /**
     * Private class that implements the interator class 
     * 
     */
    private class MyArrayListIterator implements Iterator<E>
    {
        private int nextIndex;

        /**
         * Constructor for the MyArrayListIterator 
         * 
         */
        public MyArrayListIterator()
        {
            nextIndex = 0;
        }

        /**
         * Tests whether or not there is an element in the next position
         * @return true if there is; otherwise, false
         * 
         */
        public boolean hasNext()
        {
            return nextIndex < size(); 
        }

        /**
         * Sets the pointer to the next element
         * @return the next element
         */
        public E next()
        {
            nextIndex++;
            return (E)MyArrayList.this.get(nextIndex); 
        }

        /**
         * Removes the last element that was returned by next 
         * 
         */
        public void remove()
        { 
            if (nextIndex > 0)
            { MyArrayList.this.remove(nextIndex-1);
            }
        }
    }
}