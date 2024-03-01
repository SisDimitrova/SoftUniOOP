package WorkingWithAbstraction;

import java.util.Scanner;

public class RhombusOfStars_01 {
    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    int n = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= n; i++) {
            printRow(i, n - i);
        }
        for (int i = n - 1; i > 0 ; i--) {
           printRow(i, n - i);
        }
    }
    public  static void printRow(int countStars, int countSpaces) {
        for (int spaces = 0; spaces < countSpaces; spaces++) {
            System.out.print(" ");
        }
        for (int stars = 0; stars < countStars; stars++) {
            System.out.print("* ");
        }
        System.out.println();
    }
    }

