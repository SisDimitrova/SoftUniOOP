package p02_ExtendedDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {
    public Database database;
    private static final Person[] PEOPLE = {new Person(1, "Silviya"), new Person(2, "Kaya"),
    new Person(3, "Denis")};
    @Before
    public void prepare() throws OperationNotSupportedException {
        database = new Database(PEOPLE);
    }

    @Test
    public void testConstructorHasCreateValidObject() {
        Person[] elements = database.getElements();
        Assert.assertArrayEquals(elements, PEOPLE);
    }
    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowWhenMore16Elements() throws OperationNotSupportedException {
        Person[] people = new Person[17];
        new Database(people);
    }
    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowNotElement() throws OperationNotSupportedException {
        Person[] people = new Person[0];
        new Database(people);
    }
    @Test
    public void testAddShouldAddElement() throws OperationNotSupportedException {
        database.add(new Person(4, "Valentin"));
        Person[] people = database.getElements();
        Assert.assertEquals(people.length, PEOPLE.length + 1);
        Assert.assertEquals(Integer.valueOf(people[people.length - 1].getId()), Integer.valueOf(4));
        Assert.assertEquals(people[people.length - 1].getUsername(), "Valentin");

    }
    @Test(expected = OperationNotSupportedException.class)
    public void testAddShouldThrowNullParam() throws OperationNotSupportedException {
        database.add(null);
    }
    @Test
    public void testRemoveLastElement() throws OperationNotSupportedException {
        database.remove();
        Person[] people = database.getElements();
        Assert.assertEquals(people.length, PEOPLE.length - 1);
        Assert.assertEquals(Integer.valueOf(people[people.length - 1].getId()), Integer.valueOf(2));
        Assert.assertEquals(people[people.length - 1].getUsername(), "Kaya");
    }
    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowEmptyDb() throws OperationNotSupportedException {
        database = new Database();
        database.remove();
    }
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByNameThrowNullParam() throws OperationNotSupportedException {
        database.findByUsername(null);
    }
    @Test(expected = OperationNotSupportedException.class)
    public void testAddFindByNameThrowEmptyDb() throws OperationNotSupportedException {
        database = new Database();
        database.findByUsername("Peter");
    }
    @Test
    public void testFindByUsername() throws OperationNotSupportedException {
        Person person = database.findByUsername("Silviya");
        Assert.assertEquals(1, person.getId());
        Assert.assertEquals("Silviya", person.getUsername());
    }
    @Test(expected = OperationNotSupportedException.class)
    public void testListElementMoreOne() throws OperationNotSupportedException {
        database.add(new Person(4, "Kaya"));
        database.findByUsername("Kaya");
    }
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameInvalidName() throws OperationNotSupportedException {
        database.findByUsername("Sisi");
    }
    @Test
    public void testGetId() throws OperationNotSupportedException {
        Person person = database.findById(1);
        Assert.assertEquals(person.getId(), 1);
        Assert.assertEquals(person.getUsername(), "Silviya");
    }
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdMoreThanOneId() throws OperationNotSupportedException {
        database.add(new Person(1, "Koko"));
        database.findById(1);
    }
    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdEmptyData() throws OperationNotSupportedException {
        database = new Database();
        database.findById(2);
    }
}