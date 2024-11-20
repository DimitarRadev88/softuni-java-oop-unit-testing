package com.unitTesting.lab.rpg;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class AxeTest {

    private static final int AXE_ATTACK = 10;
    private static final int AXE_DURABILITY = 10;
    public static final int BROKEN_AXE_DURABILITY = 0;
    private static final int EXPECTED_DURABILITY = AXE_DURABILITY - 1;

    private Axe axe;
    private Axe brokenAxe;
    private Target target;

    @Before
    public void initialize() {
        axe = new Axe(AXE_ATTACK, AXE_DURABILITY);
        brokenAxe = new Axe(AXE_ATTACK, BROKEN_AXE_DURABILITY);
        target = Mockito.mock(Target.class);
    }

    @Test
    public void testConstructorShouldCreateAxeWithProperValues() {
        Axe axe = new Axe(AXE_ATTACK, AXE_DURABILITY);
        assertEquals(AXE_ATTACK, axe.getAttackPoints());
        assertEquals(AXE_DURABILITY, axe.getDurabilityPoints());
    }

    @Test
    public void testAttackShouldReduceDurability() {
        axe.attack(target);
        assertEquals("Wrong durability", EXPECTED_DURABILITY, axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void testAttackShouldThrowWhenDurabilityIsZeroOrNegative() {
        brokenAxe.attack(target);
    }

}
