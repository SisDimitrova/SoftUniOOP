package archeologicalExcavations;

import static org.junit.Assert.*;
import org.junit.Test;

public class ExcavationTests {
    @Test
    public void testCreateExcavation() {
        Excavation excavation = new Excavation("test", 10);
        assertEquals("test", excavation.getName());
        assertEquals(10, excavation.getCapacity());
        assertEquals(0, excavation.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowWithNullName() {
        new Excavation(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowWithEmptyName() {
        new Excavation("    ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowWithCapacityNegative() {
        new Excavation("test", -1);
    }

    @Test
    public void testAddArcheologistShouldAdd() {
        Excavation excavation = new Excavation("test", 10);
        Archaeologist valentin = new Archaeologist("Valentin", 26);
        excavation.addArchaeologist(valentin);
        assertEquals(1, excavation.getCount());
        Archaeologist silviya = new Archaeologist("Silviya", 12);
        excavation.addArchaeologist(silviya);
        assertEquals(2, excavation.getCount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArcheologistShouldThrowNoCapacity() {
        Excavation excavation = new Excavation("test", 1);
        Archaeologist valentin = new Archaeologist("Valentin", 26);
        excavation.addArchaeologist(valentin);
        Archaeologist silviya = new Archaeologist("Silviya", 12);
        excavation.addArchaeologist(silviya);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddArcheologistShouldThrowWithExists() {
        Excavation excavation = new Excavation("test", 10);
        Archaeologist valentin = new Archaeologist("Valentin", 26);
        excavation.addArchaeologist(valentin);
        excavation.addArchaeologist(valentin);
    }
    @Test
    public void testRemoveArcheologistShouldRemove() {
        Excavation excavation = new Excavation("test", 10);
        Archaeologist valentin = new Archaeologist("Valentin", 26);
        excavation.addArchaeologist(valentin);
        Archaeologist silviya = new Archaeologist("Silviya", 12);
        excavation.addArchaeologist(silviya);
        excavation.removeArchaeologist("Silviya");
        assertEquals(1, excavation.getCount());
    }
    @Test
    public void testRemoveArcheologistShouldNotRemove() {
        Excavation excavation = new Excavation("test", 10);
        Archaeologist valentin = new Archaeologist("Valentin", 26);
        excavation.addArchaeologist(valentin);
        Archaeologist silviya = new Archaeologist("Silviya", 12);
        excavation.addArchaeologist(silviya);
        excavation.removeArchaeologist("Missing");
        assertFalse(excavation.removeArchaeologist("Missing"));
    }
}