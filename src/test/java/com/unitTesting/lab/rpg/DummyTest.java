package com.unitTesting.lab.rpg;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DummyTest {

    private static final int DUMMY_HEALTH = 50;
    private static final int DUMMY_EXPERIENCE = 10;
    private static final List<Weapon> DUMMY_POSSIBLE_LOOT = List.of(new Axe(1, 1),
            new Axe(2, 2), new Axe(3, 3));

    private static final int ATTACK_POINTS = 10;
    private static final int ATTACK_KILL_POINTS = 50;
    private static final int DUMMY_HEALTH_AFTER_TAKE_ATTACK = DUMMY_HEALTH - ATTACK_POINTS;
    public static final int DEAD_DUMMY_HEALTH = 0;

    private Dummy dummy;
    private Dummy dummyWithLoot;
    private Dummy deadDummy;

    @Before
    public void initialize() {
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_EXPERIENCE);
        this.dummyWithLoot = new Dummy(DUMMY_HEALTH, DUMMY_EXPERIENCE, DUMMY_POSSIBLE_LOOT);
        this.deadDummy = new Dummy(DEAD_DUMMY_HEALTH, DUMMY_EXPERIENCE);
    }

    @Test
    public void testConstructorWithoutPossibleLootCreates() {
        Dummy dummy = new Dummy(DUMMY_HEALTH, DUMMY_EXPERIENCE);
        assertEquals("Wrong health", DUMMY_HEALTH, dummy.getHealth());
    }

    @Test
    public void testConstructorWithPossibleLootCreates() {
        Dummy dummy = new Dummy(DUMMY_HEALTH, DUMMY_EXPERIENCE, DUMMY_POSSIBLE_LOOT);
        assertEquals("Wrong health", DUMMY_HEALTH, dummy.getHealth());
    }

    @Test
    public void testIsDeadShouldReturnCorrectValue() {
        assertTrue(deadDummy.isDead());
        assertFalse(dummy.isDead());
        assertFalse(dummyWithLoot.isDead());
    }

    @Test
    public void testTakeAttackShouldReduceHealth() {
        dummy.takeAttack(ATTACK_POINTS);
        assertEquals("Wrong health", DUMMY_HEALTH_AFTER_TAKE_ATTACK, dummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void testTakeAttackShouldThrowWhenIsDeadIsTrue() {
        deadDummy.takeAttack(ATTACK_POINTS);
    }

    @Test
    public void testGiveExperienceShouldReturnWhenIsDeadIsTrue() {
        assertEquals("Wrong experience", DUMMY_EXPERIENCE, deadDummy.giveExperience());
    }

    @Test(expected = IllegalStateException.class)
    public void testGiveExperienceShouldThrowWhenTargetIsDeadIsFalse() {
        dummy.giveExperience();
    }

    @Test
    public void testDropLootShouldReturnWeaponWhenIsDeadIsTrueAndPossibleLootIsEmptyIsFalse() {
        dummyWithLoot.takeAttack(ATTACK_KILL_POINTS);
        Weapon weapon = dummyWithLoot.dropLoot();
        assertNotNull("Weapon is null", weapon);
    }

    @Test(expected = IllegalStateException.class)
    public void testDropLootShouldThrowWhenIsDeadIsFalseAndPossibleLootIsEmptyIsFalse() {
        dummyWithLoot.dropLoot();
    }

    @Test(expected = IllegalStateException.class)
    public void testDropLootShouldThrowWhenIsDeadIsTrueAndPossibleLootIsEmptyIsTrue() {
        deadDummy.dropLoot();
    }

}
