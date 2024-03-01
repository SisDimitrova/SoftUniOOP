package EncapsulationExercisesFootballTeamGaenerator_05;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);

      String line = scanner.nextLine();
        Map<String, Team> teams = new LinkedHashMap<>();

      while (!"END".equals(line)) {
          String[] commandArg = line.split(";");
          String command = commandArg[0];
          String teamName = commandArg[1];
          try {

              switch (command) {
                  case "Team":
                      teams.put(teamName, new Team(teamName));
                      break;
                  case "Add":
                      String playerName = commandArg[2];
                      int endurance = Integer.parseInt(commandArg[3]);
                      int sprint = Integer.parseInt(commandArg[4]);
                      int dribble = Integer.parseInt(commandArg[5]);
                      int passing = Integer.parseInt(commandArg[6]);
                      int shooting = Integer.parseInt(commandArg[7]);
                      if (!teams.containsKey(teamName)) {
                          System.out.printf("Team %s does not exist.%n", teamName);
                      } else {
                          Player player = new Player(playerName, endurance, sprint, dribble, passing, shooting);
                          teams.get(teamName).addPlayer(player);
                      }
                      break;
                  case "Remove":
                      String playerToRemove = commandArg[2];
                      teams.get(teamName).removePlayer(playerToRemove);
                      break;
                  case "Rating":
                      if (!teams.containsKey(teamName)) {
                          System.out.printf("Team %s does not exist.%n", teamName);
                      } else {
                          System.out.printf("%s - %d%n", teamName, Math.round(teams.get(teamName).getRating()));
                      }
                      break;
              }
          } catch (IllegalArgumentException e) {
              System.out.println(e.getMessage());
          }

          line = scanner.nextLine();
      }

    }
}
