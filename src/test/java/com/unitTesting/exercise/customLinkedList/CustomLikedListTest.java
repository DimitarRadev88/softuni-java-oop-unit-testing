package com.unitTesting.exercise.customLinkedList;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class CustomLikedListTest {

    private static final Integer FIRST_ELEMENT = 1;
    private static final Integer SECOND_ELEMENT = 2;
    private static final Integer THIRD_ELEMENT = 3;
    private static final int ELEMENTS_COUNT = 3;
    private static final Integer SET_ELEMENT = 4;
    private static final Integer REMOVE_ELEMENT = SECOND_ELEMENT;
    private static final int REMOVE_ELEMENT_INDEX = 1;
    private static final int SET_ELEMENT_INDEX = 1;
    private static final int GET_ELEMENT_INDEX = 1;
    private static final Integer GET_ELEMENT = SECOND_ELEMENT;

    private CustomLinkedList<Integer> customLinkedList;

    @Before
    public void init() {
        customLinkedList = new CustomLinkedList<>();
        addElements();
    }

    @Test
    public void testConstructorCreates() {
        CustomLinkedList<String> customLinkedList = new CustomLinkedList<>();
    }

    @Test
    public void testAddAddsElement() {
        customLinkedList.add(FIRST_ELEMENT);
        customLinkedList.add(SECOND_ELEMENT);
        customLinkedList.add(THIRD_ELEMENT);
    }

    @Test
    public void testGetReturnsCorrectElement() {

        Integer actual = customLinkedList.get(GET_ELEMENT_INDEX);
        assertEquals("Wrong element", GET_ELEMENT, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetThrowsWhenIndexIsOutOfBounds() {
        try {
            customLinkedList.get(-1);
        } catch (IllegalArgumentException e) {
            customLinkedList.get(3);
        }
    }

    @Test
    public void testSetReplacesElementAtIndex() {
        customLinkedList.set(SET_ELEMENT_INDEX, SET_ELEMENT);
        assertEquals(SET_ELEMENT, customLinkedList.get(SET_ELEMENT_INDEX));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetThrowsWhenIndexIsOutOfBounds() {
        try {
            customLinkedList.set(4, SET_ELEMENT);
        } catch (IllegalArgumentException e) {
            customLinkedList.set(-1, SET_ELEMENT);
        }
    }

    @Test
    public void testContainsReturnsTrueWhenItemInList() {
        assertTrue(customLinkedList.contains(FIRST_ELEMENT));
    }

    @Test
    public void testContainsReturnsFalseWhenItemNotInList() {
        assertFalse(customLinkedList.contains(SET_ELEMENT));
    }

    @Test
    public void testRemoveAtRemovesElement() {
        customLinkedList.removeAt(REMOVE_ELEMENT_INDEX);
        assertFalse(customLinkedList.contains(REMOVE_ELEMENT));
    }

    @Test
    public void testRemoveAtRemovesReturnsElementAtIndex() {
        Integer actual = customLinkedList.removeAt(REMOVE_ELEMENT_INDEX);
        assertEquals(REMOVE_ELEMENT, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtThrowsWhenIndexIsOutOfBounds() {
        try {
            customLinkedList.removeAt(4);
        } catch (IllegalArgumentException e) {
            customLinkedList.removeAt(-1);
        }
    }

    @Test
    public void testRemoveRemovesElement() {
        customLinkedList.remove(REMOVE_ELEMENT);
        assertFalse(customLinkedList.contains(REMOVE_ELEMENT));
    }

    @Test
    public void testRemoveReturnsRemovedElementIndex() {
        int actual = customLinkedList.remove(REMOVE_ELEMENT);
        assertEquals(REMOVE_ELEMENT_INDEX, actual);
    }

    @Test
    public void testRemoveReturnsNegativeIndexWhenElementNotFound() {
        assertTrue(customLinkedList.remove(SET_ELEMENT) < 0);
    }

    @Test
    public void testGetCountReturnsCorrectCount() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method;

        method = CustomLinkedList.class.getDeclaredMethod("getCount");
        method.setAccessible(true);
        int actual = (int) method.invoke(customLinkedList);

        assertEquals(ELEMENTS_COUNT, actual);
    }

    @Test
    public void testRemovingAllElementsResetsHeadAndTailNodes() {
        customLinkedList.remove(FIRST_ELEMENT);
        customLinkedList.remove(SECOND_ELEMENT);
        customLinkedList.remove(THIRD_ELEMENT);
    }

    @Test
    public void testRemovingLastElementSetsTailToPrevNode() {
        customLinkedList.remove(THIRD_ELEMENT);
    }


    private void addElements() {
        customLinkedList.add(FIRST_ELEMENT);
        customLinkedList.add(SECOND_ELEMENT);
        customLinkedList.add(THIRD_ELEMENT);
    }

}
