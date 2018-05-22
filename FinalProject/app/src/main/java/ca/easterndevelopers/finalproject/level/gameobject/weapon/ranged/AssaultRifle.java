package ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged;

import android.graphics.Point;

import ca.easterndevelopers.finalproject.level.gameobject.Unit;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;

public class AssaultRifle extends RangedWeapon{

    public AssaultRifle(Unit unit) {
        super(unit, 0xffff00ff, new Point(10, 10));
        this.damage = 5;
        this.range = 12;
        this.speed = 5;
        this.ammoCapacity = 4;
        this.ammo = ammoCapacity;
    }
}
