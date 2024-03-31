package carShop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CarShopTests {
    private CarShop carShop;
    private Car car;

    @Before
    public void setUp() {
        carShop = new CarShop();
        car = new Car("Audi", 200, 40_000);
        carShop.add(car);
    }
    @Test(expected = UnsupportedOperationException.class)
    public void testGetCarsReturnUnmodifiableList() {
        carShop.getCars().set(0, car);
    }
    @Test
    public void testGetCountReturnCorrectCount() {
        Assert.assertEquals(1, carShop.getCount());
    }
    @Test
    public void testFindAllCarsWithMaxHorsePowerReturnCorrectList() {
        Car bmw = new Car("BMW", 400, 45_000);
        Car ford = new Car("Ford", 500, 40_000);
        Car opel = new Car("Opel", 700, 43_000);
        carShop.add(bmw);
        carShop.add(ford);
        carShop.add(opel);

        List<Car> expected = List.of(ford, opel);
        List<Car> actual = carShop.findAllCarsWithMaxHorsePower(400);
        Assert.assertEquals(expected, actual);
    }
    @Test(expected = NullPointerException.class)
    public void testAddNullShouldThrow() {
        Car car1 = null;
        carShop.add(car1);
    }
    @Test
    public void testRemove() {
        Car bmw = new Car("BMW", 400, 45_000);
        carShop.add(bmw);
        carShop.remove(bmw);
        Assert.assertEquals(1, carShop.getCount());
    }
    @Test
    public void testGetTheMostLuxuryCar() {
        Car bmw = new Car("BMW", 400, 45_000);
        Car ford = new Car("Ford", 500, 50_000);
        Car opel = new Car("Opel", 700, 43_000);
        carShop.add(bmw);
        carShop.add(ford);
        carShop.add(opel);

        Car actual = carShop.getTheMostLuxuryCar();
        Assert.assertEquals(ford, actual);
    }
    @Test
    public void testFindAllCarByModel() {
        Car bmw = new Car("BMW", 400, 45_000);
        Car ford = new Car("Ford", 500, 50_000);
        Car opel = new Car("Opel", 700, 43_000);
        Car opel1 = new Car("Opel", 300, 15_00);
        carShop.add(bmw);
        carShop.add(ford);
        carShop.add(opel);
        carShop.add(opel1);
        List<Car> expected = List.of(opel, opel1);
        List<Car> actual = carShop.findAllCarByModel("Opel");
        Assert.assertEquals(expected, actual);

    }

}

