import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

/**
 * The test class Lab_2Test.
 *
 * @author  David Gvadzabia
 * @version 5.2.1
 */
public class arrayListTest
{

    @Test
    public void lastItemAdder(){
        ArrayList<Integer> arr= new ArrayList();
        //Test 1 general case of adding number
        arr.add(2);
        assertEquals(2, arr.get(0));
        //Test 2 case of adding 0;
        arr.add(0);
        assertEquals(0, arr.get(1));
        //Test 3 addition of Strings
        ArrayList<String> array=new ArrayList<>();
        array.add("hi");
        assertEquals("hi", array.get(0));
        array.add("yo");
        assertEquals("yo", array.get(1));
    }


    @Test 
    public void resizeTest(){
        ArrayList<Integer> arr=new ArrayList();
        //Test 1 when adding more than 10 elements using add
        for(int i=0; i<11; i++){
            arr.add(2);
        }
        assertEquals(20, arr.array_size());
        //Test 2 adding 10th element seperately
        arr.clear();
        for(int i=0; i<10; i++){
            arr.add(2);
        }
        arr.add(15);
        assertEquals(20, arr.array_size());  
        //Test 4 adding 20th element
        arr.clear();
        for(int i=0; i<20; i++){
            arr.add(2);
        }
        arr.add(24);
        assertEquals(30, arr.array_size());  
        //Test 5 adding more than 10 element with Strings
        ArrayList<String> array=new ArrayList<>();  
        for(int i=0; i<11; i++){
            array.add("hi");
        }
        assertEquals(20, array.array_size());
        array.clear();
        //Test 6 adding 10th element as a string
        for(int i=0; i<10; i++){
            array.add("hi");
        }
        array.add("hello");
        assertEquals(20, array.array_size()); 
        array.clear();
        //Test 7 adding 20th element as a string
        for(int i=0; i<20; i++){
            array.add("hi");
        }
        array.add("whats up");
        assertEquals(30, array.array_size());   
    }

    @Test
    public void clearTest(){
        //Test 1 general clearing of array with one element
        ArrayList<Integer> arr=new ArrayList();
        arr.add(2);
        arr.clear();
        assertTrue(arr.size()==0 && arr.array_size()==10);
        //Test 2 general clearing of array with 6 elements
        for(int i=0; i<6; i++){
            arr.add(2);       
        }
        arr.clear();
        assertTrue(arr.size()==0 && arr.array_size()==10);
        //Test 3 edge case of clearing array with 0 elements
        arr.clear();
        assertTrue(arr.size()==0 && arr.array_size()==10);
        //Test4 clear with one string element
        ArrayList<String> array=new ArrayList<>();
        array.add("hi");
        array.clear();
        assertTrue(array.size()==0 && array.array_size()==10);
        //Test5 clearing more than one string elements
        for(int i=0; i<6; i++){
            array.add("hello");       
        }
        array.clear();
        assertTrue(array.size()==0 && array.array_size()==10);
        //Test6 edge case of clearing array of strings with zero elements
        array.clear();
        assertTrue(array.size()==0 && array.array_size()==10);  
    }

    @Test
    public void getTest(){
        ArrayList arr=new ArrayList();
        for(int i=0; i<6; i++){
            arr.add(i);       
        }
        //Test 1 getting the first element
        assertEquals(0, arr.get(0));
        //Test 2 getting the last element
        assertEquals(5, arr.get(5));
        //Test 3 getting element in between the array
        assertEquals(3,arr.get(3));
        //Test of exception cases
        assertThrows(IndexOutOfBoundsException.class, () -> {arr.get(-1);});
        assertThrows(IndexOutOfBoundsException.class, () -> {arr.get(15);});
        //Test 4 getting first string element
        ArrayList<String> array=new ArrayList<>();
        array.add("hi");
        assertEquals("hi", array.get(0));
        //Test 5 getting another element from the array of string
        array.add("hello");
        array.add("whats up");
        array.add("nothing");
        assertEquals("whats up", array.get(2));
        //Test 6 getting last element 
        assertEquals("nothing", array.get(3));
        //Test of exception cases
        assertThrows(IndexOutOfBoundsException.class, () -> {array.get(-1);});
        assertThrows(IndexOutOfBoundsException.class, () -> {array.get(15);});
    }

    @Test
    public void removeTest(){
        ArrayList<Integer> arr=new ArrayList();
        arr.add(7);
        arr.add(4);
        arr.add(0);
        //Test 1 general case of removing 4 from array
        boolean removedItem=arr.remove(4);
        assertTrue(removedItem==true && arr.size()==2);
        //Test 2 general case of removing 0
        removedItem=arr.remove(0);
        assertTrue(removedItem==true && arr.size()==1);
        //Test 3 removing element that is not in the array
        assertThrows(NoSuchElementException.class, () -> {arr.remove(9);});
        //Test 4 testing removal of strings
        ArrayList<String> array=new ArrayList();
        array.add("hello");
        array.add("none");
        array.add("nothing");   
        boolean removed=array.remove("none");
        assertTrue(removed==true && array.size()==2);  
        removed=array.remove("hello");
        assertTrue(removed==true && array.size()==1);  
        //Test 5 testing removal of the element that is not in the array
        assertThrows(NoSuchElementException.class, () -> {array.remove("yo");});
    }



    @Test 
    public void sizeTest(){
        //Test 1 size test when 1 element is added
        ArrayList<Integer> arr=new ArrayList();
        arr.add(1);
        assertEquals(1,arr.size());
        //Test 2 size test when 3 element is added
        arr.clear();
        arr.add(1);
        arr.add(3);
        arr.add(24);
        assertEquals(3,arr.size());
        //Test 3 edge test when array size is 0
        arr.clear();
        assertEquals(0,arr.size());
        //Test 4 size test with one element string
        ArrayList<String> array=new ArrayList();
        array.add("hello");
        assertEquals(1,array.size());
        //Test 5 testing size with more elements
        array.add("whats up");
        array.add("yooo");
        assertEquals(3,array.size());
        //Test 6 edge case of testing array with size 0
        array.clear();
        assertEquals(0,array.size());
    }


    @Test 
    public void toStringTest(){
        ArrayList<Integer> arr=new ArrayList();
        //Test 1 when array has some elements
        for(int i=0; i<6; i++){
            arr.add(2);       
        }
        String output="2 2 2 2 2 2";
        assertEquals(output,arr.toString());
        //Test 2 edge case when array is empty
        arr.clear();
        output="";
        assertEquals(output,arr.toString());
        //Test 3 when array has some strign elements 
        ArrayList<String> array=new ArrayList<>();
        array.add("hello");
        array.add("whats, up");
        array.add("yo");
        output="hello whats, up yo";
        assertEquals(output,array.toString());
        //Test 4 edge case of having no elements
        array.clear();
        output="";
        assertEquals(output,array.toString());
    }
}

