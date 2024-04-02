package scubaDive;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DivingTests {
    private DeepWaterDiver deepWaterDiver;

    @Before
    public void setUp() {
        this.deepWaterDiver = new DeepWaterDiver("test", 30);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDeepWaterDiverWithZeroCapacityShouldThrow() {
        Diving diving = new Diving("test", 0);
       diving.addDeepWaterDiver(this.deepWaterDiver);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddDeepWaterDiverWithExistingDiverShouldThrow() {
        Diving diving = new Diving("test", 1);
        diving.addDeepWaterDiver(this.deepWaterDiver);
        diving.addDeepWaterDiver(this.deepWaterDiver);
    }
    @Test
    public void testAddDeepWaterDiverShouldCorrect() {
        Diving diving = new Diving("test", 1);
        diving.addDeepWaterDiver(this.deepWaterDiver);
        Assert.assertEquals(1, diving.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCapacityShouldThrowNegativeValues() {
        Diving diving = new Diving("Test", -1);
        diving.addDeepWaterDiver(this.deepWaterDiver);
    }
    @Test(expected = NullPointerException.class)
    public void testSetNameNullShouldThrow() {
      Diving diving = new Diving(null, 1);
    }
    @Test
    public void testRemoveShouldTrue() {
        Diving diving = new Diving("test" , 1);
        diving.addDeepWaterDiver(this.deepWaterDiver);
        boolean isRemoved = diving.removeDeepWaterDiver("test");
        Assert.assertTrue(isRemoved);
    }

}
