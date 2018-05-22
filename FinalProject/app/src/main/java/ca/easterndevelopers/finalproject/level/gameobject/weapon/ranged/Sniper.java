package ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged;

import android.graphics.Point;

import ca.easterndevelopers.finalproject.level.gameobject.Unit;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;

public class Sniper extends RangedWeapon{

    public Sniper(Unit unit) {
        super(unit, 0xffff00ff, new Point(10, 10));
        this.damage = 10;
        this.range = 14;
        this.speed = 2;
        this.ammoCapacity = 3;
        this.ammo = ammoCapacity;
    }
}
