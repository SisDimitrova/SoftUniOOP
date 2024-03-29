package robotService.core;


import robotService.entities.robot.FemaleRobot;
import robotService.entities.robot.MaleRobot;
import robotService.entities.robot.Robot;
import robotService.entities.services.MainService;
import robotService.entities.services.SecondaryService;
import robotService.entities.services.Service;
import robotService.entities.supplements.MetalArmor;
import robotService.entities.supplements.PlasticArmor;
import robotService.entities.supplements.Supplement;
import robotService.repositories.SupplementRepository;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static robotService.common.ConstantMessages.*;
import static robotService.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{
    private SupplementRepository supplements;
    private Map<String, Service> services;

    public ControllerImpl() {
        this.supplements = new SupplementRepository();
        this.services = new LinkedHashMap<>();
    }

    @Override
    public String addService(String type, String name) {
        Service service;
        if (type.equals("SecondaryService")) {
            service = new SecondaryService(name);
        } else if (type.equals("MainService")) {
            service = new MainService(name);
        } else {
            throw new NullPointerException(INVALID_SERVICE_TYPE);
        }
        services.put(service.getName(), service);
        return String.format(SUCCESSFULLY_ADDED_SERVICE_TYPE, type);
    }

    @Override
    public String addSupplement(String type) {
        Supplement supplement;
        if (type.equals("MetalArmor")) {
            supplement = new MetalArmor();
        } else if (type.equals("PlasticArmor")) {
            supplement = new PlasticArmor();
        } else {
            throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }
        supplements.addSupplement(supplement);
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForService(String serviceName, String supplementType) {
        Supplement supplement = supplements.findFirst(supplementType);
        if (supplement == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }
        supplements.removeSupplement(supplement);
        services.get(serviceName).addSupplement(supplement);
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE, supplementType, serviceName);
    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price) {
        Robot robot;
        if (robotType.equals("FemaleRobot")) {
            robot = new FemaleRobot(robotName, robotKind, price);
        } else if (robotType.equals("MaleRobot")) {
            robot = new MaleRobot(robotName, robotKind, price);
        } else {
            throw new IllegalArgumentException(INVALID_ROBOT_TYPE);
        }
        Service service = services.get(serviceName);
        boolean isSuitable = service.getClass().getSimpleName().equals("SecondaryService")
                && robotType.equals("FemaleRobot") ||
                service.getClass().getSimpleName().equals("MainService") && robotType.equals("MaleRobot");

        if (!isSuitable) {
            return UNSUITABLE_SERVICE;
        }
        this.services.get(serviceName).addRobot(robot);
        return String.format(SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);
    }

    @Override
    public String feedingRobot(String serviceName) {
        Service service = this.services.get(serviceName);
        service.feeding();
        return String.format(FEEDING_ROBOT, service.getRobots().size());
    }

    @Override
    public String sumOfAll(String serviceName) {
        return String.format(VALUE_SERVICE, serviceName,
                this.services.get(serviceName).getRobots().stream().mapToDouble(Robot::getPrice).sum());
    }

    @Override
    public String getStatistics() {
        return services.values().stream().map(Service::getStatistics)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}

