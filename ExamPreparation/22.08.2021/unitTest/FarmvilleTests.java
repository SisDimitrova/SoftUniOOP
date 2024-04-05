package farmville;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FarmvilleTests {
    @Test
    public void testConstructor() {
        Farm farm = new Farm("Family", 10);
        assertEquals("Family", farm.getName());
        assertEquals(10, farm.getCapacity());
        assertEquals(0, farm.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameNullShouldThrow() {
        Farm farm = new Farm(null, 10);

    }

    @Test(expected = NullPointerException.class)
    public void testSetNameIsEmptyShouldThrow() {
        Farm farm = new Farm("    ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeCapacity() {
        Farm farm = new Farm("Family", -2);
    }

    @Test
    public void testAddSuccess() {
        Farm farm = new Farm("Family", 2);
        Animal animal = new Animal("Chicken", 20);
        farm.add(animal);
        assertEquals(1, farm.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFullCapacityShouldThrow() {
        Farm farm = new Farm("Family", 1);
        Animal animal = new Animal("Chicken", 20);
        Animal animal1 = new Animal("Caw", 50);
        farm.add(animal);
        farm.add(animal1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddFullTheSameAnimalsShouldThrow() {
        Farm farm = new Farm("Family", 3);
        Animal animal = new Animal("Chicken", 20);
        Animal animal1 = new Animal("Caw", 50);
        farm.add(animal);
        farm.add(animal1);
        farm.add(animal);
    }
    @Test
    public void removeSuccess() {
        Farm farm = new Farm("Family", 3);
        Animal animal = new Animal("Chicken", 20);
        Animal animal1 = new Animal("Caw", 50);
        farm.add(animal);
        farm.add(animal1);
        farm.remove("Caw");
        assertEquals(1, farm.getCount());
    }
    @Test()
    public void testRemoveNoAnimalInFarmShouldThrow() {
        Farm farm = new Farm("Family", 3);
        Animal animal = new Animal("Chicken", 20);
        Animal animal1 = new Animal("Caw", 50);
        farm.add(animal);
        farm.add(animal1);
        boolean lion = farm.remove("Lion");
        assertFalse(farm.remove("Lion"));

    }

}