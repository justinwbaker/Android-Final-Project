package ca.easterndevelopers.finalproject.level.gameobject;

import android.graphics.Point;

import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.R;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged.Pistol;
import ca.easterndevelopers.finalproject.utils.Utils;

public class Soldier extends Unit {

    public Soldier(Point position) {
        super(position, new Point((int)GameActivity.getTileSize(), (int)GameActivity.getTileSize()));
        this.setBitmap(Utils.loadBitmap(GameActivity.getContext(), R.drawable.soldier));
        this.timeCanMove = 1;
        this.movementRange = 5;
        this.ranged = new Pistol(this);
    }

}