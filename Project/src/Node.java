/**
 * Node class that functions as nodes for queue data structure.
 * 
 * @author David Gvadzabia
 * @version 5.2.1
 */

public class Node<E> {
    E data; // Stores the value of the node
    Node<E> next; // Node itself

    /**
     * Constructor for the Node class.
     * @param data the value we path in for our node.
     */
    Node(E data) {
        this.data = data;
        this.next = null;
    }
}