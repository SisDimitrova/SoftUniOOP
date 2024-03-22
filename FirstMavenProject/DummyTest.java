package rpg_lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DummyTest {
  private final static int DUMMY_START_HEALTH = 100;
  private final int DUMMY_EXPERIENCE = 10;
  private final int DEAD_DUMMY_HEALTH = 0;
  private final int ATTACK_POINTS = 20;

  private Dummy dummy;
  private Dummy deadDummy;

  @Before
  public void setup() {
    dummy = new Dummy(DUMMY_START_HEALTH, DUMMY_EXPERIENCE);
    deadDummy = new Dummy(DEAD_DUMMY_HEALTH, DUMMY_EXPERIENCE);
  }

  @Test
    public void testAttackedDummyLosesHealth() {
      dummy.takeAttack(ATTACK_POINTS);

      Assert.assertEquals(DUMMY_START_HEALTH - ATTACK_POINTS, dummy.getHealth());
  }
  @Test (expected = IllegalStateException.class)
    public void testAttackDeadDummyThrowsException() {
      deadDummy.takeAttack(ATTACK_POINTS);
  }
  @Test
  public void testDeadDummyGivesXp() {
    int exp = deadDummy.giveExperience();
    Assert.assertEquals(DUMMY_EXPERIENCE, exp);
  }
    @Test(expected = IllegalStateException.class)
   public void testAliveDummyDoesntGiveXP() {
      dummy.giveExperience();

    }
}