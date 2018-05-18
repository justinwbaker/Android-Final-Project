package ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged;

import android.graphics.Point;

import ca.easterndevelopers.finalproject.level.gameobject.Unit;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;

public class HeavyPistol extends RangedWeapon{

    public HeavyPistol(Unit unit) {
        super(unit, 0xffff00ff, new Point(10, 10));
        this.damage = 6;
        this.range = 6;
        this.speed = 5;
        this.ammoCapacity = 3;
        this.ammo = ammoCapacity;
    }
}
