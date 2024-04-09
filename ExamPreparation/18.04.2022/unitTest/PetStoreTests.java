package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PetStoreTests {

    private PetStore petStore;
    private Animal animal;
    @Before
    public void setUp() {
        this.petStore = new PetStore();
        this.animal = new Animal("Cat", 5, 50.50);
    }

     @Test(expected = UnsupportedOperationException.class)
    public void testGetAnimalsShouldReturnUnmodifiableList() {
         List<Animal> animals = petStore.getAnimals();
          animals.remove(1);
     }
     @Test
    public void testGetCountCorrect() {
        assertEquals(0, petStore.getCount());
        petStore.addAnimal(animal);
        assertEquals(1, petStore.getCount());
     }
     @Test
    public void testFindAllAnimalsWithMaxKilogramsReturnEmptyList() {
        petStore.addAnimal(animal);
         List<Animal> animals = petStore.findAllAnimalsWithMaxKilograms(animal.getMaxKilograms() + 10);
         assertTrue(animals.isEmpty());
     }
     @Test
    public void testFindAllAnimalsWithMaxKilogramsReturnList() {
         petStore.addAnimal(animal);
         petStore.addAnimal(new Animal("Cat", 3, 120.00));
         List<Animal> animals = petStore.findAllAnimalsWithMaxKilograms(4);
         assertEquals(1, animals.size());
         assertEquals(animal.getSpecie(), animals.get(0).getSpecie());
     }
     @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalShouldThrowNull() {
        petStore.addAnimal(null);
     }
     @Test
    public void testAddAnimalCorrect() {
        petStore.addAnimal(animal);
        assertEquals(1, petStore.getCount());
     }
     @Test
    public void testGetTheMostExpensiveAnimalShouldReturnEmpty() {
         Animal animal1 = petStore.getTheMostExpensiveAnimal();
         assertNull(animal1);
     }
    @Test
    public void testGetTheMostExpensiveAnimalShouldReturnOne() {
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("Cat", 5, 43.99));
        Animal actualAnimal = petStore.getTheMostExpensiveAnimal();
        assertEquals(animal.getPrice(), actualAnimal.getPrice(), 0.00);
    }
    @Test
    public void testFindAllAnimalBySpecieShouldEmptyList() {
        List<Animal> dog = petStore.findAllAnimalBySpecie("Dog");
        assertTrue(dog.isEmpty());
    }
    @Test
    public void testFindAllAnimalBySpecieShouldList() {
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("Dog", 6, 59.99));
        List<Animal> animals = petStore.findAllAnimalBySpecie("Cat");
        assertEquals(1, animals.size());

    }
}

