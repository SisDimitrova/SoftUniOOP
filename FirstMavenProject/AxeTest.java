package rpg_lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AxeTest {
    private static final int DUMMY_START_HEALTH = 10;
    private static final int DUMMY_EXPERIENCE = 10;
    private static final int AXE_ATTACK_POINTS = 10;
    private static final int AXE_DURABILITY = 10;
    private static final int AXE_WITHOUT_DURABILITY = 0;
    private static final int DURABILITY_LOSS = 1;


    private Dummy dummy;

    @Before
    public void setup() {
        dummy = new Dummy(DUMMY_START_HEALTH, DUMMY_EXPERIENCE);
    }
  @Test
    public void testAxeLosesDurabilityAfterAttack(){

    // arrange
      Axe axe = new Axe(AXE_ATTACK_POINTS, AXE_DURABILITY);
    // act
      axe.attack(dummy);
    // assert
    Assert.assertEquals(AXE_ATTACK_POINTS - DURABILITY_LOSS, axe.getDurabilityPoint());
    }
    @Test (expected = IllegalStateException.class)
   public  void testAttackWithBrokenAxeShouldThrowException() {
      Axe axe = new Axe(AXE_ATTACK_POINTS,AXE_WITHOUT_DURABILITY);
      axe.attack(dummy);
    }
}