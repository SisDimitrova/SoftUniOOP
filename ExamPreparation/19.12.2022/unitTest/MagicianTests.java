package magicGame;


import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.*;

public class MagicianTests {
    Magician magician;
    Magic magic;
    @Before
    public void setUp() {
        magician = new Magician("Test", 10);
        magic = new Magic("testMagic", 10);
    }
    @Test
    public void testConstructor() {
        Magician magician = new Magician("Test", 10);
        assertEquals("Test", magician.getUsername());
        assertEquals(10, magician.getHealth());
        magician.addMagic(magic);
        assertEquals(1, magician.getMagics().size());
    }
    @Test(expected = NullPointerException.class)
    public void testSetNameNullShouldThrow() {
        Magician magician = new Magician(null, 10);
    }
    @Test(expected = NullPointerException.class)
    public void testSetNameIsEmptyShouldThrow() {
        Magician magician = new Magician("   ", 10);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeHealth() {
        Magician magician = new Magician("test", -1);
    }
    @Test
    public void testAddMagic() {
        Magician magician = new Magician("test", 10);
        Magic magic = new Magic("TestMagic", 12);
        magician.addMagic(magic);
        assertEquals(1, magician.getMagics().size());
    }
    @Test
    public void getMagic() {
        magician.addMagic(magic);
        assertEquals(magic, magician.getMagic("testMagic"));
    }


    @Test(expected = NullPointerException.class)
    public void testAddMagicNull() {
        Magic magic1 = null;
        magician.addMagic(magic1);
    }
    @Test
    public void testTakeDamageLeftWithMoreThanZeroHealth() {
        magician.takeDamage(5);
        assertEquals(5, magician.getHealth());
    }
    @Test(expected = IllegalStateException.class)
    public void tesTakeDamageZeroHealth() {
        magician.takeDamage(10);
        magician.takeDamage(20);
    }
    @Test
    public void testRemoveMagicFalse() {
        Magic magic1 = new Magic("Magic", 12);
        assertFalse(magician.removeMagic(magic1));
    }
    @Test
    public void testRemoveTrue() {
        magician.addMagic(magic);
        assertTrue(magician.removeMagic(magic));

    }

}
