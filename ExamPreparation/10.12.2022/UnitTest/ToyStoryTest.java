package toyStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ToyStoryTest {
    private ToyStore toyStore;
    @Before
    public void init() {
        toyStore = new ToyStore();
    }
    @Test
    public void testInitToyStory() {
      Map<String, Toy> toyShelf = new LinkedHashMap<>();
        toyShelf.put("A", null);
        toyShelf.put("B", null);
        toyShelf.put("C", null);
        toyShelf.put("D", null);
        toyShelf.put("E", null);
        toyShelf.put("F", null);
        toyShelf.put("G", null);
        Assert.assertEquals(toyShelf, toyStore.getToyShelf());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testWhenAddToyToNonExistingShouldThrow() throws OperationNotSupportedException {
        Toy toy = new Toy("Minion", "1");
        toyStore.addToy("W", toy);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddToyToTakenShelfShouldThrow() throws OperationNotSupportedException {
        Toy toy = new Toy("Minion", "1");
        Toy toy1 = new Toy("Maquin", "2");
        toyStore.addToy("A", toy);
        toyStore.addToy("A", toy1);
    }
    @Test(expected = OperationNotSupportedException.class)
    public void testAddDuplicateToyShouldThrow() throws OperationNotSupportedException {
        Toy toy = new Toy("Minion", "1");
        toyStore.addToy("A", toy);
        toyStore.addToy("B", toy);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveToyFromNonExistingShelfShouldThrow() throws OperationNotSupportedException {
        Toy toy = new Toy("Minion", "1");
        toyStore.addToy("A", toy);
        toyStore.removeToy("W", toy);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingToyShouldThrow() throws OperationNotSupportedException {
        Toy toy = new Toy("Minion", "1");
        Toy toy1 = new Toy("Lego", "2");
        toyStore.addToy("A", toy);

        toyStore.removeToy("A", toy1);
    }
    @Test
    public void testRemoveShouldSuccess() throws OperationNotSupportedException {
        Toy toy = new Toy("Lego", "1");
        toyStore.addToy("A", toy);

        String result = toyStore.removeToy("A", toy);

        Assert.assertNull(toyStore.getToyShelf().get("A"));
        Assert.assertEquals("Remove toy:1 successfully!", result);
    }
}