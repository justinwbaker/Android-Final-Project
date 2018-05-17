package ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged;

import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;

public class Rifle extends RangedWeapon{

    public Rifle() {
        this.damage = 6;
        this.range = 11;
        this.speed = 4;
        this.ammoCapacity = 4;
    }
}
