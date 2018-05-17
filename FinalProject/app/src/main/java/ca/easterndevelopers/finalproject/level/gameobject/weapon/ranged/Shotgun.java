package ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged;

import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;

public class Shotgun extends RangedWeapon{

    int damage;
    int range;
    int speed;
    int ammoCapacity;

    public Shotgun() {
        this.damage = 7;
        this.range = 5;
        this.speed = 6;
        this.ammoCapacity = 2;
    }

    public int getAmmoCapacity() {
        return ammoCapacity;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public int getSpeed() {
        return speed;
    }

    public void setAmmoCapacity(int ammoCapacity) {
        this.ammoCapacity = ammoCapacity;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
