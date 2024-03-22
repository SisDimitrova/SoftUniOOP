package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;
public class DatabaseTest {
  public Database database;
  private static final Integer[] NUMBERS = {3, 5, 8, 12};
  @Before
  public void prepare() throws OperationNotSupportedException {
    database = new Database(NUMBERS);
  }

  @Test
    public void testConstructorHasCreateValidObject() {
      Integer[] elements = database.getElements();
    Assert.assertArrayEquals(elements, NUMBERS);
  }
  @Test(expected = OperationNotSupportedException.class)
  public void testConstructorThrowWhenMore16Elements() throws OperationNotSupportedException {
    Integer[] numbers = new Integer[17];
    new Database(numbers);
  }
  @Test(expected = OperationNotSupportedException.class)
  public void testConstructorThrowNotElement() throws OperationNotSupportedException {
    Integer[] numbers = new Integer[0];
    new Database(numbers);
  }
  @Test
  public void testAddShouldAddElement() throws OperationNotSupportedException {
    database.add(26);
    Assert.assertEquals(database.getElements().length, NUMBERS.length + 1);
    Assert.assertEquals(database.getElements()[database.getElements().length - 1], Integer.valueOf(26));
  }
  @Test(expected = OperationNotSupportedException.class)
  public void testAddShouldThrowNullParam() throws OperationNotSupportedException {
    database.add(null);
  }
  @Test
  public void testRemoveLastElement() throws OperationNotSupportedException {
    database.remove();
    Integer[] element = database.getElements();
    Assert.assertEquals(element.length, NUMBERS.length - 1);
    Assert.assertEquals(element[element.length - 1], Integer.valueOf(8));
  }
  @Test(expected = OperationNotSupportedException.class)
  public void testRemoveThrowEmptyDb() throws OperationNotSupportedException {
    for (int i = 0; i < NUMBERS.length; i++) {
      database.remove();
    }
    database.remove();
  }
}