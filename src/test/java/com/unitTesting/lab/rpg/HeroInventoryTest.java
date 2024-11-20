package com.unitTesting.lab.rpg;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HeroInventoryTest {

    private static final int INVENTORY_CAPACITY = 4;
    private static final List<Weapon> INVENTORY_CONTENT = List.of(
            Mockito.mock(Weapon.class),
            Mockito.mock(Weapon.class),
            Mockito.mock(Weapon.class)
    );

    private static final Weapon ADDED_WEAPON = Mockito.mock(Weapon.class);

    private HeroInventory heroInventory;

    @Before
    public void initialize() {
        this.heroInventory = new HeroInventory(INVENTORY_CAPACITY);
        INVENTORY_CONTENT.forEach(heroInventory::addWeapon);
    }

    @Test
    public void testConstructorShouldCreate() {
        HeroInventory heroInventory = new HeroInventory(INVENTORY_CAPACITY);
    }

    @Test
    public void testAddWeaponShouldAddCorrectWeaponAtCorrectSlot() {
        heroInventory.addWeapon(ADDED_WEAPON);

        Iterator<Weapon> iterator = heroInventory.getWeapons().iterator();
        for (int i = 0; i < INVENTORY_CONTENT.size(); i++) {
            iterator.next();
        }

        assertEquals("Wrong weapon added", ADDED_WEAPON, iterator.next());
    }

    @Test(expected = IllegalStateException.class)
    public void testAddWeaponShouldThrowWhenNoCapacityToAdd() {
        heroInventory.addWeapon(ADDED_WEAPON);
        heroInventory.addWeapon(Mockito.mock(Weapon.class));
    }

}
