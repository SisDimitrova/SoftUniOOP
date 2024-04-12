package christmasRaces.core.interfaces;

import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositorie.interfaces.Repository;


import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static christmasRaces.common.ExceptionMessages.*;
import static christmasRaces.common.OutputMessages.*;


public class ControllerImpl implements Controller {
    private Repository<Car> carRepository;
    private Repository<Driver> driverRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Car> carRepository, Repository<Driver> driverRepository, Repository<Race> raceRepository) {
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driver) {
        Driver driver1 = this.driverRepository.getByName(driver);
        if (driver1 == null) {
            Driver driver2 = new DriverImpl(driver);
            this.driverRepository.add(driver2);
        } else {
            throw  new IllegalArgumentException(String.format(DRIVER_EXISTS, driver));
        }
        return String.format(DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car car = carRepository.getByName(model);
        Car car1;
        if (car != null) {
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        }
        if (type.equals("Muscle")) {
            car1 = new MuscleCar(model, horsePower);
        } else {
            car1 = new SportsCar(model, horsePower);
        }
        this.carRepository.add(car1);

        return String.format(CAR_CREATED, car1.getClass().getSimpleName(), model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        Driver driver = driverRepository.getByName(driverName);
        Car car = carRepository.getByName(carModel);
        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        if (car == null) {
            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }
        driver.addCar(car);
        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        Race race = raceRepository.getByName(raceName);
        Driver driver = driverRepository.getByName(driverName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        if (driver == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        race.addDriver(driver);
        return String.format(DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = raceRepository.getByName(raceName);
        if (race == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        Collection<Driver> drivers = race.getDrivers();
        if (drivers.size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }
        for (Driver driver : drivers) {
            if (driver.getCanParticipate()) {
                driver.getCar().calculateRacePoints(race.getLaps());
                driver.winRace();
            }

        }
        List<Driver> driverList = drivers.stream().sorted(Comparator.comparing(driver ->
                        driver.getCar().calculateRacePoints(race.getLaps())))
                .collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(DRIVER_FIRST_POSITION, driverList.get(driverList.size() - 1).getName()
                , race.getName())).append(System.lineSeparator());
        sb.append(String.format(DRIVER_SECOND_POSITION, driverList.get(driverList.size() - 2).getName()
        , race.getName())).append(System.lineSeparator());
        sb.append(String.format(DRIVER_THIRD_POSITION, driverList.get(driverList.size() - 3).getName()
        , race.getName())).append(System.lineSeparator());
        return sb.toString().trim();
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = raceRepository.getByName(name);
        Race race1;
        if (race == null) {
            race1 = new RaceImpl(name, laps);
        } else {
          throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }
        this.raceRepository.add(race1);
        return String.format(RACE_CREATED, name);
    }
}
