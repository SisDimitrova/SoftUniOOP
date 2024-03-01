package EncapsulationExercisesPizzaCalories_04;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

          String[] pizzaInfo = scanner.nextLine().split("\\s+");
          String namePizza = pizzaInfo[1];
          int numberOfToppings = Integer.parseInt(pizzaInfo[2]);

          String[] doughInfo = scanner.nextLine().split("\\s+");
          String flourType = doughInfo[1];
          String bakingTechnique = doughInfo[2];
          double weight = Double.parseDouble(doughInfo[3]);


          try {
              Pizza pizza = new Pizza(namePizza, numberOfToppings);
              Dough dough = new Dough(flourType, bakingTechnique, weight);
              pizza.setDough(dough);
               String command = scanner.nextLine();
               while (!"END".equals(command)) {
                   String[] toppingInfo = command.split("\\s+");
                   String nameTopping = toppingInfo[1];
                   double toppingWeight = Double.parseDouble(toppingInfo[2]);
                   Topping topping = new Topping(nameTopping, toppingWeight);
                   pizza.addTopping(topping);
                   command = scanner.nextLine();

               }
              System.out.printf("%s - %.2f", pizza.getName(), pizza.getOverallCalories());
          } catch (IllegalArgumentException e) {
              System.out.println(e.getMessage());
          }

    }
}
