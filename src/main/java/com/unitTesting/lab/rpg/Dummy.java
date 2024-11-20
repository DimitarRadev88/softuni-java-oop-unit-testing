package com.unitTesting.lab.rpg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Dummy implements Target {
    private int health;
    private int experience;
    private List<Weapon> possibleLoot;

    public Dummy(int health, int experience, List<Weapon> possibleLoot) {
        this.health = health;
        this.experience = experience;
        this.possibleLoot = possibleLoot;
    }

    public Dummy(int health, int experience) {
        this(health, experience, new ArrayList());
    }

    public int getHealth() {
        return this.health;
    }

    public void takeAttack(int attackPoints) {
        if (this.isDead()) {
            throw new IllegalStateException("Dummy is dead.");
        } else {
            this.health -= attackPoints;
        }
    }

    public int giveExperience() {
        if (!this.isDead()) {
            throw new IllegalStateException("Target is not dead.");
        } else {
            return this.experience;
        }
    }

    public boolean isDead() {
        return this.health <= 0;
    }

    public Weapon dropLoot() {
        if (!this.isDead()) {
            throw new IllegalStateException("Target is not dead.");
        } else if (this.possibleLoot.isEmpty()) {
            throw new IllegalStateException("Target has no loot");
        } else {
            int index = ThreadLocalRandom.current().nextInt(this.possibleLoot.size());
            return this.possibleLoot.get(index);
        }
    }
}
