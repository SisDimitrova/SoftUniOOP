package PolymorphismExercisesVehicles_01;

public class Car extends VehicleImpl{


    public Car(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption);
        super.setFuelConsumption(fuelConsumption + 0.9);
    }
}
