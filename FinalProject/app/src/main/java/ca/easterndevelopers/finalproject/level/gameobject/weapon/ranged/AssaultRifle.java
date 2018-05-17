package ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged;

import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;

public class AssaultRifle extends RangedWeapon{

    public AssaultRifle() {
        this.damage = 5;
        this.range = 8;
        this.speed = 5;
        this.ammoCapacity = 4;
    }
}
