package PolymorphismExercisesVehiclesExtension_02;

public class Bus extends VehicleImpl{
    private static final boolean DEFAULT_IS_EMPTY = true;
    private boolean isEmpty;
    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
        this.isEmpty = DEFAULT_IS_EMPTY;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public boolean isEmpty() {
        return isEmpty;

    }
}
