package ca.easterndevelopers.finalproject.level.gameobject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.Weapon;

public abstract class Unit extends GameObject {

    float baseDamage;
    float movementRange;

    int timeCanMove;
    int timesHasMoved;

    Weapon melee;
    RangedWeapon ranged;

    Bitmap image;

    boolean isAcrtiveUnit;

    public Unit() {

    }

    public void move() {

    }

    public void meleeAttack() {

    }

    public void rangedAttack() {

    }

}
