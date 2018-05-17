package ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged;

import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;

public class Shotgun extends RangedWeapon{

    public Shotgun() {
        this.damage = 7;
        this.range = 5;
        this.speed = 6;
        this.ammoCapacity = 2;
    }
}
