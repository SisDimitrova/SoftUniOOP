package p04_BubbleSortTest;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleTest {
    @Test
    public void testBubbleSort() {
        int[] numbers = {3, 5, 26, 18, 0, -12};
        int[] expectedArray = {-12, 0, 3, 5, 18, 26};
        Bubble.sort(numbers);
        Assert.assertArrayEquals(expectedArray, numbers);
    }

}