package ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged;

import android.graphics.Point;

import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.level.gameobject.Unit;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;

public class SniperRifle extends RangedWeapon{

    public SniperRifle(Unit unit) {
        super(unit, 0xffff00ff, new Point((int) MainActivity.getTileSize()/5, (int)MainActivity.getTileSize()/5));
        this.damage = 10;
        this.range = 19;
        this.speed = 2;
        this.ammoCapacity = 3;
        this.ammo = ammoCapacity;
    }
}
