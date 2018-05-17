package ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged;

import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;

public class Rifle extends RangedWeapon{

    int damage;
    int range;
    int speed;
    int ammoCapacity;

    public Rifle() {
        this.damage = 6;
        this.range = 11;
        this.speed = 4;
        this.ammoCapacity = 4;
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
