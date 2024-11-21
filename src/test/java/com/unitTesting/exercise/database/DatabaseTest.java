package com.unitTesting.exercise.database;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DatabaseTest {

    private static final Integer[] ELEMENTS = new Integer[] {1, 2, 3};
    private static final Integer[] TOO_MANY_ELEMENTS_ARRAY = new Integer[17];
    private static final Integer ADD_ELEMENT = 4;
    private static final int ADD_ELEMENT_INDEX = 3;
    private static final Integer LAST_ELEMENT_AFTER_REMOVE = ELEMENTS[ELEMENTS.length - 2];
    private static final int EXPECTED_LENGTH_AFTER_ADD = ELEMENTS.length + 1;
    private static final int EXPECTED_LENGTH_AFTER_REMOVE = ELEMENTS.length - 1;
    private static final Integer NULL_ELEMENT = null;

    private Database database;

    @Before
    public void init() throws OperationNotSupportedException {
        database = new Database(ELEMENTS);
    }

    @Test
    public void testConstructorCreates() throws OperationNotSupportedException {
        Database database = new Database(ELEMENTS);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowsWhenNoElementsArePassed() throws OperationNotSupportedException {
        Database database = new Database();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowsWhenElementCountIsOver16Symbols() throws OperationNotSupportedException {
        Database database = new Database(TOO_MANY_ELEMENTS_ARRAY);
    }

    @Test
    public void testGetElementsReturnsCorrectArrayLength() {
        assertEquals(ELEMENTS.length, database.getElements().length);
    }

    @Test
    public void testAddIncreasesSize() throws OperationNotSupportedException {
        database.add(ADD_ELEMENT);
        assertEquals(EXPECTED_LENGTH_AFTER_ADD, database.getElements().length);
    }

    @Test
    public void testAddInsertsCorrectElement() throws OperationNotSupportedException {
        database.add(ADD_ELEMENT);
        assertEquals(ADD_ELEMENT, database.getElements()[ADD_ELEMENT_INDEX]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddThrowsWhenNullElementIsPassed() throws OperationNotSupportedException {
        database.add(NULL_ELEMENT);
    }

    @Test
    public void testGetElementsReturnsCorrectArray() {
        Integer[] actual = database.getElements();

        assertArrayEquals(ELEMENTS, actual);
    }

    @Test
    public void testRemoveReducesLength() throws OperationNotSupportedException {
        database.remove();
        assertEquals(EXPECTED_LENGTH_AFTER_REMOVE, database.getElements().length);
    }

    @Test
    public void testRemoveRemovesLastElement() throws OperationNotSupportedException {
        database.remove();
        assertEquals(LAST_ELEMENT_AFTER_REMOVE, database.getElements()[database.getElements().length - 1]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowsWhenArrayIsEmpty() throws OperationNotSupportedException {
        database.remove();
        database.remove();
        database.remove();
        database.remove();
    }

}
