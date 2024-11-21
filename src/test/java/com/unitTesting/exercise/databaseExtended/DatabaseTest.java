package com.unitTesting.exercise.databaseExtended;

import com.unitTesting.exercise.extendedDatabase.Database;
import com.unitTesting.exercise.extendedDatabase.Person;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {

    private static final Person FIRST_PERSON = new Person(1, "First");
    private static final Person SECOND_PERSON = new Person(2, "Second");
    private static final Person THIRD_PERSON = new Person(3, "Third");
    private static final Person FIND_PERSON = SECOND_PERSON;
    private static final int FIND_PERSON_ID = FIND_PERSON.getId();
    private static final String FIND_PERSON_USERNAME = FIND_PERSON.getUsername();
    private static final String MISSING_PERSON_USERNAME = "Missing";
    private static final int MISSING_PERSON_ID = 5;
    private static final Person ADD_PERSON = new Person(4, "Added");
    private static final Person REMOVE_PERSON = THIRD_PERSON;
    private static final int ADD_PERSON_INDEX = 3;
    private static final Person[] ELEMENTS = {FIRST_PERSON, SECOND_PERSON, THIRD_PERSON};
    private static final int LENGTH_AFTER_ADD = ELEMENTS.length + 1;
    public static final Person NULL_PERSON = null;
    public static final int LENGTH_AFTER_REMOVE = 2;
    public static final String NULL_USERNAME = null;

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
    public void testConstructorThrowsWhenElementsLengthIsOver16() throws OperationNotSupportedException {
        Database database = new Database(new Person[17]);
    }

    @Test
    public void testGetElementsReturnsCorrectArray() {
        Person[] actual = database.getElements();
        assertArrayEquals(ELEMENTS, actual);
    }

    @Test
    public void testAddPersonIncreasesArrayLength() throws OperationNotSupportedException {
        database.add(ADD_PERSON);
        assertEquals(LENGTH_AFTER_ADD, database.getElements().length);
    }

    @Test
    public void testAddPersonAddsCorrectPerson() throws OperationNotSupportedException {
        database.add(ADD_PERSON);
        assertEquals("Wrong id", ADD_PERSON.getId(), database.getElements()[ADD_PERSON_INDEX].getId());
        assertEquals("Wrong name", ADD_PERSON.getUsername(), database.getElements()[ADD_PERSON_INDEX].getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddThrowsWhenPersonIsNull() throws OperationNotSupportedException {
        database.add(NULL_PERSON);
    }

    @Test
    public void testRemoveRemovesLastPerson() throws OperationNotSupportedException {
        database.remove();
        int newLength = database.getElements().length;
        assertEquals("Wrong length", LENGTH_AFTER_REMOVE, newLength);
        assertNotEquals("Wrong element", REMOVE_PERSON, database.getElements()[newLength - 1]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveThrowsWhenNoElementsLeft() throws OperationNotSupportedException {
        database.remove();
        database.remove();
        database.remove();
        database.remove();
    }

    @Test
    public void testFindByUsernameReturnsCorrectPerson() throws OperationNotSupportedException {
        Person actual = database.findByUsername(FIND_PERSON_USERNAME);
        assertEquals(FIND_PERSON, actual);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByThrowsWhenNameCasingIsNotEqual() throws OperationNotSupportedException {
        database.findByUsername(FIND_PERSON_USERNAME.toUpperCase());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowsWhenTwoOrMoreNamesAreEqual() throws OperationNotSupportedException {
        database.add(FIND_PERSON);
        database.findByUsername(FIND_PERSON_USERNAME);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameThrowsIfNoMatchIsFound() throws OperationNotSupportedException {
        database.findByUsername(MISSING_PERSON_USERNAME);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdThrowsWhenNameIsNull() throws OperationNotSupportedException {
        database.findByUsername(NULL_USERNAME);
    }

    @Test
    public void testFindByIdReturnsCorrectPerson() throws OperationNotSupportedException {
        Person actual = database.findById(FIND_PERSON_ID);
        assertEquals(FIND_PERSON, actual);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdThrowsIfNoMatchIsFound() throws OperationNotSupportedException {
        database.findById(MISSING_PERSON_ID);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIdThrowsWhenMoreThanOneIdsAreFound() throws OperationNotSupportedException {
        database.add(FIND_PERSON);
        database.findById(FIND_PERSON.getId());
    }

}
