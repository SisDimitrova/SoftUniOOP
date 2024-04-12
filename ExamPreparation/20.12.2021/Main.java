package christmasRaces;

import christmasRaces.core.EngineImpl;
import christmasRaces.core.interfaces.Controller;
import christmasRaces.core.interfaces.ControllerImpl;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.races.Race;
import christmasRaces.io.ConsoleReader;
import christmasRaces.io.ConsoleWriter;
import christmasRaces.repositorie.interfaces.CarRepository;
import christmasRaces.repositorie.interfaces.DriverRepository;
import christmasRaces.repositorie.interfaces.RaceRepository;

import christmasRaces.repositorie.interfaces.Repository;

public class Main {
    public static void main(String[] args) {
        Repository<Car> carRepository = new CarRepository();
        Repository<Race> raceRepository = new RaceRepository();
        Repository<Driver> driverRepository = new DriverRepository();

        Controller controller = new ControllerImpl(carRepository, driverRepository, raceRepository);

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        EngineImpl engine = new EngineImpl(reader, writer, controller);
        engine.run();
    }
}
