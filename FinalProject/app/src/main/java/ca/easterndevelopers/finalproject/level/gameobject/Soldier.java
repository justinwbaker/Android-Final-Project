package ca.easterndevelopers.finalproject.level.gameobject;

import android.graphics.Point;

import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.R;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.melee.CombatKnife;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged.Pistol;
import ca.easterndevelopers.finalproject.utils.Utils;

public class Soldier extends Unit {

    public Soldier(Point position, boolean isEnemy) {
        super(position, new Point((int) MainActivity.getTileSize(), (int)MainActivity.getTileSize()));
        if(!isEnemy)
            this.setBitmap(Utils.loadBitmap(GameActivity.getContext(), R.drawable.soldier));
        else
            this.setBitmap(Utils.loadBitmap(GameActivity.getContext(), R.drawable.enemy_soldier));

        this.timeCanMove = 1;
        this.movementRange = 5;
        this.ranged = new Pistol(this);
        this.melee = new CombatKnife(this);
    }

}
