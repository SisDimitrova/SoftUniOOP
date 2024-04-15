package catHouse.entities.houses;

import catHouse.entities.cat.Cat;
import catHouse.entities.toys.Toy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public abstract class BaseHouse implements House{
    private String name;
    private int capacity;
    private Collection<Toy> toys;
    private Collection<Cat> cats;

    public BaseHouse(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.toys = new ArrayList<>();
        this.cats = new ArrayList<>();
    }

    @Override
    public int sumSoftness() {
        return toys.stream().mapToInt(Toy::getSoftness).sum();
    }

    @Override
    public void addCat(Cat cat) {
       if (cats.size() >= this.capacity) {
           throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_CAT);
       }
       cats.add(cat);
    }

    @Override
    public void removeCat(Cat cat) {
      cats.remove(cat);
    }

    @Override
    public void buyToy(Toy toy) {
     toys.add(toy);
    }

    @Override
    public void feeding() {
        for (Cat cat : cats) {
            cat.eating();
        }

    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s:", this.name, this.getClass().getSimpleName()))
                .append(System.lineSeparator());
        sb.append("Cats:");
        if (this.cats.isEmpty()) {
            sb.append(" none");
        } else {
            for (Cat cat : cats) {
                sb.append(String.format(" %s", cat.getName()));
            }
        }
        sb.append(System.lineSeparator());
        sb.append(String.format("Toys: %d Softness: %d", this.toys.size(), sumSoftness()))
                .append(System.lineSeparator());

        return sb.toString().trim();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(HOUSE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;

    }

    @Override
    public Collection<Cat> getCats() {
        return Collections.unmodifiableCollection(cats);
    }

    @Override
    public Collection<Toy> getToys() {
        return Collections.unmodifiableCollection(toys);
    }
}
