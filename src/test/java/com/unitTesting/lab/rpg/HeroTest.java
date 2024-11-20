package com.unitTesting.lab.rpg;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class HeroTest {

    private static final String HERO_NAME = "Invoker";
    private static final Weapon HERO_WEAPON = Mockito.mock(Weapon.class);
    private static final int HERO_EXPERIENCE = 0;
    private static final int HERO_INVENTORY_CAPACITY = 4;
    private static final Inventory HERO_INVENTORY = new HeroInventory(4);
    private static final int TARGET_EXPERIENCE = 10;
    private static final int EXPECTED_EXPERIENCE = HERO_EXPERIENCE + TARGET_EXPERIENCE;
    private static final Weapon TARGET_DROP_LOOT_WEAPON = Mockito.mock(Weapon.class);

    private Hero hero;
    private Target target;

    @Before
    public void initialize() {
        this.hero = new Hero(HERO_NAME, HERO_WEAPON, HERO_INVENTORY);
        this.target = Mockito.mock(Target.class);
    }

    @Test
    public void testConstructorShouldCreate() {
        Hero hero = new Hero(HERO_NAME, HERO_WEAPON, HERO_INVENTORY);
        assertEquals("Wrong name", HERO_NAME, hero.getName());
        assertEquals("Wrong inventory", HERO_INVENTORY, hero.getInventory());
        assertEquals("Wrong experience", HERO_EXPERIENCE, hero.getExperience());
        assertEquals("Wrong weapon", HERO_WEAPON, hero.getWeapon());
    }

    @Test
    public void testAttackShouldAddExperienceWhenTargetIsDeadIsTrue() {
        Mockito.when(target.isDead()).thenReturn(true);
        Mockito.when(target.giveExperience()).thenReturn(TARGET_EXPERIENCE);
        hero.attack(target);
        assertEquals("Wrong experience gain", EXPECTED_EXPERIENCE, hero.getExperience());
    }

    @Test
    public void testAttackShouldAddWeaponToInventoryWhenTargetIsDeadIsTrue() {
        Mockito.when(target.isDead()).thenReturn(true);
        Mockito.when(target.dropLoot()).thenReturn(TARGET_DROP_LOOT_WEAPON);
        hero.attack(target);
        Weapon actual = hero.getInventory().getWeapons().iterator().next();
        assertEquals("Wrong weapon in inventory", TARGET_DROP_LOOT_WEAPON, actual);
    }

}
