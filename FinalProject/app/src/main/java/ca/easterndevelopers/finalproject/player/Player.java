package ca.easterndevelopers.finalproject.player;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

import ca.easterndevelopers.finalproject.level.Level;
import ca.easterndevelopers.finalproject.level.gameobject.Unit;

public class Player {

    private ArrayList<Unit> units;
    private boolean isPlayersTurn;

    private int indexOfAcrtiveUnit = 0;

    private Level currentLevel;

    public Player() {
        this.isPlayersTurn = false;
        this.units = new ArrayList<Unit>();
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void startTurn() {
        this.isPlayersTurn = true;
        if(this.units.size() != 0) {
            this.units.get(indexOfAcrtiveUnit).setActive();
        }
    }

    public boolean isPlayersTurn() {
        return isPlayersTurn;
    }

    public void playLevel(Level level) {
        currentLevel = level;
        level.init(this);
        for(Unit u : units){
            u.init(level);
        }
    }

    public Level getCurrentLevel() {
        return this.currentLevel;
    }

    public void renderUnits(Canvas canvas, Paint paint) {
        for(Unit u : units){
            u.render(canvas, paint);
        }
    }
}
