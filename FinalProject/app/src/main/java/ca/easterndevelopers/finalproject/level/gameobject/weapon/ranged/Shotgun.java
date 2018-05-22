package ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged;

import android.graphics.Point;

import ca.easterndevelopers.finalproject.level.gameobject.Unit;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;

public class Shotgun extends RangedWeapon{

    public Shotgun(Unit unit) {
        super(unit, 0xffff00ff, new Point(10, 10));
        this.damage = 7;
        this.range = 8;
        this.speed = 6;
        this.ammoCapacity = 2;
        this.ammo = ammoCapacity;
    }
}
