package stuntClimb;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClimbingTests {
    private RockClimber rockClimber;

    @Before
    public void setUp() {
        this.rockClimber = new RockClimber("test", 120);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddRockClimberWithZeroCapacityShouldThrow() {
        Climbing climbing = new Climbing("test", 0);
        climbing.addRockClimber(this.rockClimber);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddRockClimberWithExistingClimberShouldThrow() {
        Climbing climbing = new Climbing("test", 1);
        climbing.addRockClimber(this.rockClimber);
        climbing.addRockClimber(this.rockClimber);
    }
    @Test
    public void testAddRockClimberShouldCorrect() {
        Climbing climbing = new Climbing("test", 1);
        climbing.addRockClimber(this.rockClimber);
        Assert.assertEquals(1, climbing.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCapacityWithNegativeValuesShouldThrow() {
        Climbing climbing = new Climbing("Test", -1);
        climbing.addRockClimber(this.rockClimber);
    }
    @Test(expected = NullPointerException.class)
    public void testSetNameNullShouldThrow() {
        Climbing climbing = new Climbing(null, 1);
    }
    @Test
    public void testRemoveShouldTrue() {
        Climbing climbing = new Climbing("test", 1);
        climbing.addRockClimber(this.rockClimber);
        boolean removed = climbing.removeRockClimber("test");
        Assert.assertTrue(removed);
    }


}
