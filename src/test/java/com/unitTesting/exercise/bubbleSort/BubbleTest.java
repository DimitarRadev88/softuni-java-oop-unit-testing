package com.unitTesting.exercise.bubbleSort;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertArrayEquals;


public class BubbleTest {

    @Test
    public void testSortSortsRandomArrayElementsFromIntegerMinToIntegerMaxValues() {
        for (int i = 0; i < 1000; i++) {
            int[] randomValuesArray = createRandomValuesArray(1000);
            int[] actual = Arrays.copyOf(randomValuesArray, randomValuesArray.length);
            int[] expected = Arrays.copyOf(randomValuesArray, randomValuesArray.length);
            Bubble.sort(actual);
            Arrays.sort(expected);
            assertArrayEquals(expected, actual);
        }

    }

    private int[] createRandomValuesArray(int length) {
        int[] elements = new int[length];

        for (int i = 0; i < length; i++) {
            elements[i] = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE) + Integer.MIN_VALUE + Integer.MAX_VALUE;
        }

        return elements;
    }

}
