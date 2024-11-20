package com.unitTesting.lab.rpg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeroInventory implements Inventory {
    private List<Weapon> inventory = new ArrayList();
    private int capacity;

    public HeroInventory(int capacity) {
        this.capacity = capacity;
    }

    public void addWeapon(Weapon weapon) {
        if (this.capacity <= this.inventory.size()) {
            throw new IllegalStateException("No slot in inventory.");
        } else {
            this.inventory.add(weapon);
        }
    }

    public Iterable<Weapon> getWeapons() {
        return Collections.unmodifiableCollection(this.inventory);
    }
}
