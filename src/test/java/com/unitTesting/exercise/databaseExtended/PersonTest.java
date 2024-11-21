package com.unitTesting.exercise.databaseExtended;

import com.unitTesting.exercise.extendedDatabase.Person;
import org.junit.Assert;
import org.junit.Test;

public class PersonTest {

    private static final int PERSON_ID = 123;
    private static final String PERSON_NAME = "Name";


    @Test
    public void testConstructorCreates() {
        Person person = new Person(PERSON_ID, PERSON_NAME);
        Assert.assertEquals("Wrong id", PERSON_ID, person.getId());
        Assert.assertEquals("Wrong name", PERSON_NAME, person.getUsername());
    }

}
