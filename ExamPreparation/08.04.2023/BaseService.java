package robotService.entities.services;

import robotService.entities.robot.Robot;
import robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static robotService.common.ConstantMessages.*;
import static robotService.common.ExceptionMessages.*;

public abstract class BaseService implements Service{
    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Robot> robots;

    protected BaseService(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
       if (name == null || name.trim().isEmpty()) {
           throw new NullPointerException(SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY);
       }
       this.name = name;
    }

    @Override
    public Collection<Robot> getRobots() {
        return Collections.unmodifiableCollection(this.robots);
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return Collections.unmodifiableCollection(this.supplements);
    }

    @Override
    public void addRobot(Robot robot) {
      if (this.getRobots().size() >= this.capacity) {
          throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_ROBOT);
      }
      this.robots.add(robot);
    }

    @Override
    public void removeRobot(Robot robot) {
        this.robots.remove(robot);
    }

    @Override
    public void addSupplement(Supplement supplement) {
       this.supplements.add(supplement);
    }

    @Override
    public void feeding() {
        this.robots.forEach(Robot::eating);
    }

    @Override
    public int sumHardness() {
        return this.getSupplements().stream().mapToInt(Supplement::getHardness).sum();
    }

    @Override
    public String getStatistics() {
        String robotInfo = "Robots: " + ((robots.isEmpty()) ? "none"
                : robots.stream().map(Robot::getName)
                .collect(Collectors.joining(" ")));
        return String.format("%s %s:%n%s%nSupplements: %d Hardness: %d",name,
                this.getClass().getSimpleName(), robotInfo, supplements.size(), sumHardness());
    }
}
