package StudentSystem_03;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentSystem studentSystem = new StudentSystem();
        while (true) {
            String[] arguments = scanner.nextLine().split("\\s+");
            if (arguments[0].equals("Exit")) {
                break;
            }
            studentSystem.parsCommand(arguments);
        }
    }
}
