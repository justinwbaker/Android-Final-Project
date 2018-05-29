package ca.easterndevelopers.finalproject.level.gameobject;

import android.graphics.Point;

import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.R;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.melee.CombatKnife;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged.Pistol;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged.RocketLauncher;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged.Shotgun;
import ca.easterndevelopers.finalproject.utils.Utils;

public class Tank extends Unit {

    public Tank(Point position, boolean isEnemy) {
        super(position, new Point((int) MainActivity.getTileSize(), (int)MainActivity.getTileSize()), isEnemy);
        if(!isEnemy)
            this.setBitmap(Utils.loadBitmap(GameActivity.getContext(), R.drawable.tank));
        else
            this.setBitmap(Utils.loadBitmap(GameActivity.getContext(), R.drawable.tank_enemy));

        this.timeCanMove = 1;
        this.movementRange = 5;
        this.ranged = new Shotgun(this);
        this.ranged2 = new RocketLauncher(this);
        this.melee = new CombatKnife(this);
    }

}
