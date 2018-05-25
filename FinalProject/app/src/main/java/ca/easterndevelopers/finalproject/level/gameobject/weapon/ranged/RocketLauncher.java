package ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged;

import android.graphics.Point;

import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.level.gameobject.Unit;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;

public class RocketLauncher extends RangedWeapon{

    public RocketLauncher(Unit unit) {
        super(unit, 0xffff00ff, new Point((int) MainActivity.getTileSize()/4, (int)MainActivity.getTileSize()/4));
        this.damage = 15;
        this.range = 13;
        this.speed = 1;
        this.ammoCapacity = 1;
        this.ammo = ammoCapacity;
    }
}
