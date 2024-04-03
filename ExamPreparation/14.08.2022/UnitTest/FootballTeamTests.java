package football;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class FootballTeamTests {
   private Footballer footballer;
   private FootballTeam footballTeam;
   @Before
    public void setUp() {
       this.footballer = new Footballer("test");
       this.footballTeam = new FootballTeam("Testing", 10);
   }
   @Test(expected = IllegalArgumentException.class)
    public void testCreatingTeamWithNoVacant() {
    new FootballTeam("test", -1);
   }
   @Test
    public void testCreatingTeamCorrectVacant() {
       assertEquals(10, footballTeam.getVacantPositions());
   }
   @Test(expected = NullPointerException.class)
    public void testSetNameWithNullNameShouldThrow() {
       new FootballTeam(null, 1);
   }
   @Test(expected = NullPointerException.class)
    public void testSetNameWithEmptyNameShouldThrow() {
       new FootballTeam("   ", 1);
   }
   @Test
    public void testCorrectCreateTeam() {
       assertEquals("Testing", footballTeam.getName());
   }
   @Test
    public void testAddCorrectPlayer() {
       footballTeam.addFootballer(footballer);
       assertEquals(1, footballTeam.getCount());
   }
   @Test(expected = IllegalArgumentException.class)
    public void testAddPlayerWhenTeamIsFullShouldThrow() {
       FootballTeam team = new FootballTeam("test", 0);
       team.addFootballer(footballer);
   }
   @Test
    public void testRemoveSFootballerShouldReduceTeamCount() {
       footballTeam.addFootballer(footballer);
       assertEquals(1, footballTeam.getCount());

       footballTeam.removeFootballer(footballer.getName());
       assertEquals(0, footballTeam.getCount());
   }
   @Test(expected = IllegalArgumentException.class)
    public void testRemoveFootballerShouldThrow() {
       footballTeam.addFootballer(footballer);
       footballTeam.removeFootballer("not");
   }
   @Test
    public void testFootballerForSaleShouldDeactivatePlayer() {
       footballTeam.addFootballer(footballer);

       footballTeam.footballerForSale(footballer.getName());
       assertFalse(footballer.isActive());
   }
}
