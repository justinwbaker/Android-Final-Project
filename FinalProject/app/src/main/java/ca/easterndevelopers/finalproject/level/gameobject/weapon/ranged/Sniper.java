package ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged;

import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;

public class Sniper extends RangedWeapon{

    public Sniper() {
        this.damage = 10;
        this.range = 14;
        this.speed = 2;
        this.ammoCapacity = 3;
    }
}
