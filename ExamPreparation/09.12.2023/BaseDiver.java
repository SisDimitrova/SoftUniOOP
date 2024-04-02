package harpoonDiver.models.diver;

import harpoonDiver.models.seaCatch.BaseSeaCatch;
import harpoonDiver.models.seaCatch.SeaCatch;

import static harpoonDiver.common.ConstantMessages.*;
import static harpoonDiver.common.ExceptionMessages.DIVER_NAME_NULL_OR_EMPTY;
import static harpoonDiver.common.ExceptionMessages.DIVER_OXYGEN_LESS_THAN_ZERO;

public abstract class BaseDiver implements Diver {
    private String name;
    private double oxygen;
    private SeaCatch seaCatch;

    public BaseDiver(String name, double oxygen) {
        this.setName(name);
        this.setOxygen(oxygen);
        this.seaCatch = new BaseSeaCatch();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(DIVER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public double getOxygen() {
        return oxygen;
    }

    public void setOxygen(double oxygen) {
        if (oxygen < 0) {
            throw new IllegalArgumentException(DIVER_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;
    }

    @Override
    public SeaCatch getSeaCatch() {
        return seaCatch;
    }

    @Override
    public boolean canDive() {
        return this.oxygen > 0;
    }

    @Override
    public void shoot() {
        double currentOxygen = this.oxygen;
        currentOxygen -= 30;
        if (currentOxygen < 0) {
            currentOxygen = 0;
        }
        this.oxygen = currentOxygen;
    }

    @Override
    public String toString() {
        String format = FINAL_DIVER_NAME + System.lineSeparator() +
                FINAL_DIVER_OXYGEN + System.lineSeparator() +
                FINAL_DIVER_CATCH;
        return String.format(format,
                this.getName(),
                this.getOxygen(),
                this.getSeaCatch().getSeaCreatures().isEmpty() ? "None" :
                        String.join(FINAL_DIVER_CATCH_DELIMITER,
                        this.getSeaCatch().getSeaCreatures())).trim();
    }
}

