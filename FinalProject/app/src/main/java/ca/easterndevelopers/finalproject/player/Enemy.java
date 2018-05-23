package ca.easterndevelopers.finalproject.player;

import java.util.ArrayList;

import ca.easterndevelopers.finalproject.level.gameobject.Unit;

public class Enemy extends Player {

    public Enemy() {
        this.isPlayersTurn = false;
        this.units = new ArrayList<Unit>();
    }

    @Override
    public void updateUnits() {
        for (int i = 0; i < units.size(); i++) {
            Unit u = units.get(i);
            u.update(0.0);
            if (u.isRemoved()) {
                this.units.remove(u);
            }
        }
    }
}
