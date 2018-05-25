package ca.easterndevelopers.finalproject.level.gameobject;

import android.graphics.Point;

import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.R;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.melee.CombatKnife;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged.SniperRifle;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged.Rifle;
import ca.easterndevelopers.finalproject.utils.Utils;

public class Sniper extends Unit {

    public Sniper(Point position, boolean isEnemy) {
        super(position, new Point((int) MainActivity.getTileSize(), (int)MainActivity.getTileSize()), isEnemy);
        if(!isEnemy)
            this.setBitmap(Utils.loadBitmap(GameActivity.getContext(), R.drawable.soldier));
        else
            this.setBitmap(Utils.loadBitmap(GameActivity.getContext(), R.drawable.enemy_soldier));

        this.timeCanMove = 1;
        this.movementRange = 4;

        this.ranged = new SniperRifle(this);
        this.ranged2 = new Rifle(this);
        this.melee = new CombatKnife(this);
    }

}
