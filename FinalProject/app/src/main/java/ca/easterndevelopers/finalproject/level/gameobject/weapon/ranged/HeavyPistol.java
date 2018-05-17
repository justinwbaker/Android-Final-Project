package ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged;

import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;

public class HeavyPistol extends RangedWeapon{

    public HeavyPistol() {
        this.damage = 6;
        this.range = 6;
        this.speed = 5;
        this.ammoCapacity = 3;
    }
}
