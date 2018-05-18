package ca.easterndevelopers.finalproject.player;

import java.util.ArrayList;

import ca.easterndevelopers.finalproject.level.gameobject.Unit;

public class Enemy extends Player {

    public Enemy() {
        this.isPlayersTurn = false;
        this.units = new ArrayList<Unit>();
    }
}
