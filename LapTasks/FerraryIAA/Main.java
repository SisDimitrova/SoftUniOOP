package FerrariInterfaceAndAbstraction;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nameDriver = scanner.nextLine();

        Ferrari ferrari = new Ferrari(nameDriver);
        System.out.printf("%s%n", ferrari);
    }
}
