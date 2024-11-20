package com.unitTesting.lab.rpg;

public interface Target {
    void takeAttack(int var1);

    int giveExperience();

    int getHealth();

    boolean isDead();

    Weapon dropLoot();
}
