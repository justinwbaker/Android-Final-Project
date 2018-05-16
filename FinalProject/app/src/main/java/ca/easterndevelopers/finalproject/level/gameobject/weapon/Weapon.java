package ca.easterndevelopers.finalproject.level.gameobject.weapon;

import android.graphics.Bitmap;

public abstract class Weapon {

    float damage;
    float range;

    int timePerTurn;
    int timesUsed;

    boolean hasBeenUsed;

    Bitmap UIImage;
}
