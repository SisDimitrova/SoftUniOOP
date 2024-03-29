package robots;

import org.junit.Assert;
import org.junit.Test;

public class ServiceTests {

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrow() {
        new Service(null, 5);
    }
    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowOnWhitespaces() {
        new Service("   ", 5);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCapacityShouldThrowOnNegative() {
        new Service("Main", -1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFullCapacityShouldThrow() {
        Service service = new Service("Main", 1);
        service.add(new Robot("testRobot"));
        service.add(new Robot("test"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveShouldThrowMissingRobot() {
        Service service = new Service("Main", 2);
        service.add(new Robot("testRobot"));
        service.add(new Robot("test"));
        service.remove("Pesho");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testForSaleShouldThrowMissingRobot() {
        Service service = new Service("Main", 2);
        service.add(new Robot("testRobot"));
        service.add(new Robot("test"));
        service.forSale("Pesho");
    }
    @Test
    public void testForSaleShouldSetRobot() {
        Service service = new Service("Main", 5);
        Robot robot = new Robot("testRobot");
        service.add(robot);
        service.forSale(robot.getName());
        Assert.assertFalse(robot.isReadyForSale());
    }
}
