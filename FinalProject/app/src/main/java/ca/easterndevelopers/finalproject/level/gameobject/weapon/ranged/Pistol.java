package ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged;

import android.graphics.Point;

import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.level.gameobject.Unit;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;

public class Pistol extends RangedWeapon{
    
    public Pistol(Unit unit) {
        super(unit, 0xffffffff, new Point((int)MainActivity.getTileSize()/5, (int)MainActivity.getTileSize()/5));

        this.damage = 3;
        this.range = 9;
        this.speed = 8;
        this.ammoCapacity = 3;
        this.ammo = this.ammoCapacity;
    }
}
