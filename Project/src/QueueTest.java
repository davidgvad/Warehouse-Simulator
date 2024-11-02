import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class QueueTest.
 *
 * @author  David Gvadzabia
 * @version 5.2.1
 */
public class QueueTest
{
    @Test
    public void addTest() {
        //Test1 adding one element
        Queue<Integer> queue=new Queue();
        queue.add(10);
        assertEquals(10, queue.poll());
        //Test2 adding more elements
        queue.add(1);
        queue.add(2);
        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        //Test3 adding one string element 
        Queue<String> queueStr=new Queue();
        queueStr.add("hi");
        assertEquals("hi", queueStr.poll());
        //Test4 adding more string elements
        queueStr.add("hey");
        queueStr.add("hello");
        assertEquals("hey", queueStr.poll());
        assertEquals("hello", queueStr.poll());
    }
  @Test
  public void isEmptyTest(){
        //Test1 adding element and seeing if its empty
        Queue<Integer> queue=new Queue();
        queue.add(10);
        assertEquals(false, queue.isEmpty()); 
        //Test2 removing that element and seeing if the queue is empty
        queue.poll();
        assertEquals(true, queue.isEmpty());
    }
  @Test
  public void pollTest(){
        //Test1 testing poll with one element
        Queue<Integer> queue=new Queue();
        queue.add(0);
        assertEquals(0, queue.poll());
        //Test2 testing poll with multiple elements
        queue.add(1);
        queue.add(3);
        assertEquals(1, queue.poll());
        assertEquals(3, queue.poll());
        //Test3 edge case of using poll on empty queue
        assertEquals(null, queue.poll());
        //Test4 testing poll with one String element
        Queue<String> queueStr=new Queue();
        queueStr.add("hello");
        assertEquals("hello", queueStr.poll());
        //Test5 testing poll with multiple string elements
        queueStr.add("yay");
        queueStr.add("cs");
        assertEquals("yay", queueStr.poll());
        assertEquals("cs", queueStr.poll());
        //Test6 testing poll with empty queue of strings
        assertEquals(null, queueStr.poll());
    } 
}
