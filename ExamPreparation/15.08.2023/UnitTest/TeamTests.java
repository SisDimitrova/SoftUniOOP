package handball;

import org.junit.Assert;
import org.junit.Test;

public class TeamTests {


    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowWhenInvalidName() {
        Team team = new Team("", 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowWhenInvalidPosition() {
        Team team = new Team("Bulgaria", -5);
    }
    @Test
    public void testConstructorShouldCreateCorrectTeam() {
        Team team = new Team("Bulgaria", 5);
        Assert.assertEquals("Bulgaria", team.getName());
        Assert.assertEquals(5, team.getPosition());
        Assert.assertEquals(0, team.getCount());
    }
    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowWhenNameWhitespace() {
        Team team = new Team("    ", 5);
    }
    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowWhenNameNull() {
        Team team = new Team(null, 5);
    }
    @Test
    public void testGetCountShouldReturnCorrectTeamCount() {
       Team team = new Team("Bulgaria", 5);
       team.add(new HandballPlayer("Valentin"));
       team.add(new HandballPlayer("Petar"));
       team.add(new HandballPlayer("Simo"));
       Assert.assertEquals(3, team.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldThrowFullTeam() {
        Team team = new Team("Bulgaria", 2);
        team.add(new HandballPlayer("Valentin"));
        team.add(new HandballPlayer("Petar"));
        team.add(new HandballPlayer("Simo"));
    }
    @Test
    public void testAddTeamIsNotFull() {
        Team team = new Team("Bulgaria", 5);
        team.add(new HandballPlayer("Valentin"));
        team.add(new HandballPlayer("Petar"));
        team.add(new HandballPlayer("Simo"));
        Assert.assertEquals(3, team.getCount());
    }
    @Test
    public void testRemoveShouldWhenPlayerExists() {
        Team team = new Team("Bulgaria", 5);
        team.add(new HandballPlayer("Valentin"));
        team.add(new HandballPlayer("Petar"));
        team.add(new HandballPlayer("Simo"));
        team.remove("Petar");
        Assert.assertEquals(2, team.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveShouldThrowWhenPlayerNonExist() {
        Team team = new Team("Bulgaria", 5);
        team.add(new HandballPlayer("Valentin"));
        team.add(new HandballPlayer("Petar"));
        team.add(new HandballPlayer("Simo"));
        team.remove("Peter");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testPlayerForAnotherTeamShouldThrowWhenPlayerNonExists() {
        Team team = new Team("Bulgaria", 5);
        team.add(new HandballPlayer("Valentin"));
        team.add(new HandballPlayer("Petar"));
        team.add(new HandballPlayer("Simo"));
        team.playerForAnotherTeam("Peter");
    }
    @Test
    public void testPlayerForAnotherTeamShouldSetActiveToFalse() {
        Team team = new Team("Bulgaria", 5);
        team.add(new HandballPlayer("Valentin"));
        team.add(new HandballPlayer("Petar"));
        team.add(new HandballPlayer("Simo"));
        HandballPlayer petar = team.playerForAnotherTeam("Petar");
        Assert.assertFalse(petar.isActive());
    }
    @Test
    public void testGetStatisticShouldReturnCorrectResult() {
        Team team = new Team("Bulgaria", 5);
        team.add(new HandballPlayer("Valentin"));
        team.add(new HandballPlayer("Petar"));
        team.add(new HandballPlayer("Simo"));
        String expectedStatistic = "The player Valentin, Petar, Simo is in the team Bulgaria.";
        String actualStatistics = team.getStatistics();
         Assert.assertEquals(expectedStatistic, actualStatistics);
    }
    @Test
    public void testGetStatisticShouldReturnCorrectResultWhenTeamIsEmpty() {
        Team team = new Team("Bulgaria", 5);

        String expectedStatistic = "The player  is in the team Bulgaria.";
        String actualStatistics = team.getStatistics();
        Assert.assertEquals(expectedStatistic, actualStatistics);
    }
}