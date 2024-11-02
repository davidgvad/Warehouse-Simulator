import java.util.NoSuchElementException;

/**
 * Creates a flexible java array that can resize, insert elements, get its size and access its elements by index.
 *
 * @author David Gvadzabia
 * @version 5.2.1
 */
public class ArrayList<E> {
    // Instance variables
    private Object[] array; // The array we are manipulating on
    private int arrayListLength; // The Length of array

    /**
     * Initiates an array with 10 empty elements.
     */
    public ArrayList() {
        array=new Object[10];
        arrayListLength=0;
    }

    /**
     * Adds an element to the end of the array.
     *
     * @param newItem represents the element needed to be added into the array.
     */
    public void add(E newItem) {
        if (arrayListLength==array.length) {
            resize();
        }
        array[arrayListLength] = newItem;
        arrayListLength++;
    }

    /**
     * Resizes the array in case of being filled by adding an additional 10 empty elements.
     */
    private void resize() {
        Object[] newArray=new Object[array.length + 10]; 
        for (int i=0; i < array.length; i++) {
            newArray[i]=array[i]; 
        }
        array=newArray;
    }

    /**
     * Gets the desired element from the array.
     *
     * @param index specifies the position of the desired element.
     * @return desired element.
     */
    public E get(int index) {
        if (index>=arrayListLength || index < 0) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        return (E) array[index];
    }

    /**
     * Clears the array and leaves only 10 empty elements.
     */
    public void clear() {
        array=new Object[10];
        arrayListLength=0;
    }

    /**
     * Specifies the length of the array containing elements.
     * @return the length of array of real elements.
     */
    public int size() {
        return arrayListLength;
    }

    /**
     * Specifies the actual length of the array including the empty elements.
     * @return the actual array size.
     */
    public int array_size() {
        return array.length;
    }

    /**
     * Returns a string that contains all elements separated by spaces.
     * @return string with all the elements.
     */
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for (int i=0; i < arrayListLength; i++) {
            sb.append(array[i]).append(" "); 
        }
        // Convert to string and trim the string
        return sb.toString().trim(); 
    }

    /**
     * Removes the specified element from the arraylist.
     * @param element the element we are trying to remove.
     * @return returns true if the removal was succesful.
     */
    public boolean remove(E element) {
        for (int i=0; i < arrayListLength; i++) {
            if (element.equals(array[i])) {
                // Shift elements to the left to fill the gap
                for (int j=i; j < arrayListLength-1; j++) {
                    array[j]=array[j + 1];
                }
                arrayListLength--; // Decrease the size of the array
                array[arrayListLength] = null; // Nullify the last element to help with garbage collection
                return true; // Element was found and removed
            }
        }
        throw new NoSuchElementException("Element not found in the array");
    }
}