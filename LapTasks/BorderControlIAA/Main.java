package BorderControlInterfaceAndAbstraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Identifiable> identifiableList = new ArrayList<>();

        String command = scanner.nextLine();
        while (!"End".equals(command)) {
            String[] commandArg = command.split("\\s+");
            if (commandArg.length == 2) {
                String robotName = commandArg[0];
                String idRobot = commandArg[1];
                Robot robot = new Robot(robotName, idRobot);
                identifiableList.add(robot);
            } else {
                String nameCitizen = commandArg[0];
                int age = Integer.parseInt(commandArg[1]);
                String id = commandArg[2];
                Citizen citizen = new Citizen(nameCitizen, age, id);
                identifiableList.add(citizen);
            }

            command = scanner.nextLine();
        }
        String lastDigit = scanner.nextLine();
        for (Identifiable entriesId : identifiableList) {
            if (entriesId.getId().endsWith(lastDigit)) {
                System.out.printf("%s%n", entriesId.getId());
            }
        }
        }
    }

