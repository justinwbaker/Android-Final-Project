package ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged;

import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;

public class Pistol extends RangedWeapon{

    public Pistol() {
        this.damage = 3;
        this.range = 5;
        this.speed = 8;
        this.ammoCapacity = 3;
    }
}
