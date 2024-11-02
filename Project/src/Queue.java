/**
 * Queue data structure, which can handle any type of object.
 * 
 * @author David Gvadzabia
 * @version 5.2.1
 */
public class Queue<E> {
    private Node<E> head;  // Head pointer for the queue 
    private Node<E> tail;  // Tail pointer for the queue 

    /**
     * Constructor for the queue data structure which sets head and tail both to null initially.
     */
    public Queue() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Adds the node to the end of the queue.
     * @param data the value that the added node holds.
     */
    public void add(E data) {
        Node<E> newNode = new Node<>(data);
        if (tail == null) {
            head = tail = newNode; // If the queue is empty head and tail are the same
        } else {
            // Appending new node
            tail.next = newNode;
            tail = newNode;
        }
    }

    /**
     * Gets and removes the first element in the queue.
     * @return E returns the object it removed.
     */
    public E poll() {
        if (head == null) {
            return null; // Return null if the queue is empty
        }
        E data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;  // If the queue becomes empty, reset tail to null
        }
        return data;
    }

    /**
     * Checks whether the queue is empty.
     * @return returns true if it is empty, false if it is not.
     */
    public boolean isEmpty() {
        return head == null;
    }
}