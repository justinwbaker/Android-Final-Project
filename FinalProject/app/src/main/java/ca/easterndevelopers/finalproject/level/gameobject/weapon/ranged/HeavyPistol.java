package ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged;

import android.graphics.Point;

import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.level.gameobject.Unit;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;

public class HeavyPistol extends RangedWeapon{

    public HeavyPistol(Unit unit) {
        super(unit, 0xffff00ff, new Point((int) MainActivity.getTileSize()/5, (int)MainActivity.getTileSize()/5));
        this.damage = 6;
        this.range = 10;
        this.speed = 5;
        this.ammoCapacity = 3;
        this.ammo = ammoCapacity;
    }
}
