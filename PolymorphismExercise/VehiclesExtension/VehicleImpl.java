package PolymorphismExercisesVehiclesExtension_02;

import java.text.DecimalFormat;

public abstract class VehicleImpl implements Vehicle {
   private double fuelQuantity;
   private double fuelConsumption;
   private double tankCapacity;

    public VehicleImpl(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.setFuelConsumption(fuelConsumption);
        this.setTankCapacity(tankCapacity);
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
    @Override
    public  void refuel(double litres) {
        if (litres <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
        if (this.fuelQuantity + litres > this.tankCapacity) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }
        this.fuelQuantity += litres;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    @Override
    public String drive(double distance) {
        double neededFuel = this.fuelConsumption * distance;
        DecimalFormat df = new DecimalFormat("##.##");
        String result = String.format("%s needs refueling", this.getClass().getSimpleName());

        if (this.fuelQuantity >= neededFuel) {
            result = String.format("%s travelled %s km", this.getClass().getSimpleName(), df.format(distance));
            this.fuelQuantity -= neededFuel;
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.fuelQuantity);
    }
}
