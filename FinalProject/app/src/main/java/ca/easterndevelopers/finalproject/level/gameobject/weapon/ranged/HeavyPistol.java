package ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged;

import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;

public class HeavyPistol extends RangedWeapon{

    int damage;
    int range;
    int speed;
    int ammoCapacity;

    public HeavyPistol() {
        this.damage = 6;
        this.range = 6;
        this.speed = 5;
        this.ammoCapacity = 3;
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
