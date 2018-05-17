package ca.easterndevelopers.finalproject.level.gameobject.weapon.melee;

import ca.easterndevelopers.finalproject.level.gameobject.weapon.Weapon;

public class CombatKnife extends Weapon{
    int damage;
    int speed;
    int range;

    public CombatKnife() {
        this.damage = 5;
        this.speed = 10;
        this.range = 1;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
