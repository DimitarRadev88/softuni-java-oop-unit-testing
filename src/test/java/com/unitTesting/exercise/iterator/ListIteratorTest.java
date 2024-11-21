package com.unitTesting.exercise.iterator;

import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ListIteratorTest {

    private static final String[] ELEMENTS = {"First", "Second", "Third"};
    private static final String[] SINGLE_ELEMENT_ARRAY = new String[1];
    private static final String[] ZERO_ELEMENTS_ARRAY = new String[0];
    private static final String[] NULL_ARRAY = null;
    private ListIterator iterator;
    private ListIterator singleElementIterator;
    private ListIterator zeroElementsIterator;

    @Before
    public void init() throws OperationNotSupportedException {
        iterator = new ListIterator(ELEMENTS);
        singleElementIterator = new ListIterator(SINGLE_ELEMENT_ARRAY);
        zeroElementsIterator = new ListIterator(ZERO_ELEMENTS_ARRAY);
    }

    @Test
    public void testConstructorCreates() throws OperationNotSupportedException {
        ListIterator iterator = new ListIterator(ELEMENTS);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowsWhenCalledWithNullParameter() throws OperationNotSupportedException {
        new ListIterator(NULL_ARRAY);
    }

    @Test
    public void testPrintReturnsCurrentFirstElement() {
        assertEquals(ELEMENTS[0], iterator.print());
    }

    @Test(expected = IllegalStateException.class)
    public void testPrintThrowsWhenElementsLengthIsZero() {
        zeroElementsIterator.print();
    }

    @Test
    public void testHasNextReturnsTrueIfPointingIsNotAtLastElementIndex() {
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testHasNextReturnsFalseIfPointerIsAtLastElementIndex() {
        assertFalse(singleElementIterator.hasNext());
    }

    @Test
    public void testMoveReturnsTrueIfPointerIndexNotAtLastElementIndex() {
        assertTrue(iterator.move());
    }

    @Test
    public void testMoveReturnsFalseIfPointerIndexAtLastElementIndex() {
        assertFalse(singleElementIterator.move());
    }

    @Test
    public void testMoveMovesPointerIndexToNextElement() {
        assertEquals(ELEMENTS[0], iterator.print());
        iterator.move();
        assertEquals(ELEMENTS[1], iterator.print());
        iterator.move();
        assertEquals(ELEMENTS[2], iterator.print());
    }




}
