package p05_CustomLinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomLinkedListTest {
       CustomLinkedList<String> customLinkedList;

       @Before
    public void setup() {
           customLinkedList = new CustomLinkedList<>();
       }
       @Test
    public void testAddSuccess() {
           customLinkedList.add("Silviya");
           Assert.assertEquals("Silviya", customLinkedList.get(0));
       }
       @Test
    public void testAddToNonEmptyListSuccess() {
           customLinkedList.add("Silviya");
           customLinkedList.add("Kaya");
           Assert.assertEquals("Kaya", customLinkedList.get(1));
       }
       @Test(expected = IllegalArgumentException.class)
    public void testGetShouldThrowForBigIndex() {
          customLinkedList.get(11);
       }
       @Test(expected = IllegalArgumentException.class)
    public void testGetShouldThrowNegativeIndex() {
           customLinkedList.get(-2);
       }
     @Test(expected = IllegalArgumentException.class)
    public void emptyListInvalidIndexThrowException() {
           customLinkedList = new CustomLinkedList<>();
           customLinkedList.set(3, "Valentin");
     }
     @Test
    public void testValidSetElementOnIndex() {
           customLinkedList.add("Silviya");
           customLinkedList.set(0, "Mariya");
           Assert.assertEquals(customLinkedList.get(0), "Mariya");
     }
     @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtOnEmptyList() {
           customLinkedList.removeAt(0);
     }
     @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtInvalidIndexThrowException() {
           customLinkedList.add("Silviya");
           customLinkedList.add("Kaya");
           customLinkedList.removeAt(3);
     }

}