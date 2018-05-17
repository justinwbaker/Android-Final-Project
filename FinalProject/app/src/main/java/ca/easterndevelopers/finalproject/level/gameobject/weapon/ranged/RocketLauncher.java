package ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged;

import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;

public class RocketLauncher extends RangedWeapon{

    public RocketLauncher() {
        this.damage = 15;
        this.range = 9;
        this.speed = 1;
        this.ammoCapacity = 1;
    }
}
