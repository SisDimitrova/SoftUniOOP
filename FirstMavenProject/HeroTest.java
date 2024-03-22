package rpg_lab;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class HeroTest {

  @Test
    public void testHeroGainsXPWhenTargetDies() {
      Weapon weaponMock = Mockito.mock(Weapon.class);
      Target targetMock = Mockito.mock(Target.class);

      Mockito.when(targetMock.isDead()).thenReturn(true);
      Mockito.when(targetMock.giveExperience()).thenReturn(10);

      Hero hero = new Hero("Valentin", weaponMock);

      hero.attack(targetMock);

    Assert.assertEquals(10, hero.getExperience());
  }
}