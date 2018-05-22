package ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged;

import android.graphics.Point;

import ca.easterndevelopers.finalproject.level.gameobject.Unit;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;

public class Rifle extends RangedWeapon{

    public Rifle(Unit unit) {
        super(unit, 0xffff00ff, new Point(10, 10));
        this.damage = 6;
        this.range = 15;
        this.speed = 4;
        this.ammoCapacity = 4;
        this.ammo = ammoCapacity;
    }
}
