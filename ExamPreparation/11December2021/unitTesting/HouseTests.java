package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {
    private static final int CAPACITY = 10;
    private static final String NAME = "BigHouse";

    private House house;
    private Cat cat;

    @Before
    public void setUp() {
        this.house = new House(NAME, CAPACITY);
        this.cat = new Cat("testCat");
    }
    @Test(expected = NullPointerException.class)
    public void testCreateHouseWithNullNameShouldThrow() {
        new House(null, CAPACITY);
    }
    @Test(expected = NullPointerException.class)
    public void testCreateHouseWithEmptyName() {
        new House("   ", CAPACITY);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCreateHouseWithNegativeCapacityShouldThrow() {
        new House(NAME, - 1);
    }
    @Test
    public void testCreateValidArgumentHouse() {
        Assert.assertEquals(NAME, house.getName());
        Assert.assertEquals(CAPACITY, house.getCapacity());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddCatShouldFailWhenCapacityReached() {
        House house1 = new House(NAME, 0);
        house1.addCat(cat);
    }
    @Test
    public void testAddCatCorrectSize() {
        house.addCat(cat);
        Assert.assertEquals(1, house.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCatShouldThrowMissing() {
        house.removeCat(cat.getName());
    }
    @Test
    public void testRemoveCatShouldDecrease() {
        house.addCat(cat);
        house.removeCat(cat.getName());
        Assert.assertEquals(0, house.getCount());
    }
    @Test
    public void testCatForSaleShouldSetCat() {
        house.addCat(cat);
        house.catForSale(cat.getName());
        Assert.assertFalse(cat.isHungry());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleShouldThrowMissing() {
        house.catForSale(cat.getName());
    }

}
