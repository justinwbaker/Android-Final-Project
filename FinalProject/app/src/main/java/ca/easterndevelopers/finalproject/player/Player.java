package ca.easterndevelopers.finalproject.player;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

import ca.easterndevelopers.finalproject.level.Level;
import ca.easterndevelopers.finalproject.level.gameobject.Unit;
import ca.easterndevelopers.finalproject.renderer.GameRenderer;

public class Player {

    protected ArrayList<Unit> units;
    protected boolean isPlayersTurn;

    protected int indexOfActiveUnit = 0;

    protected Level currentLevel;

    public Player() {
        this.isPlayersTurn = false;
        this.units = new ArrayList<Unit>();
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void startTurn() {
        this.isPlayersTurn = true;
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

    public void updateUnits() {
        for(Unit u : units){
            u.update(0.0);
            if(GameRenderer.getTouchedPoint() != null) {
                
            }
        }
    }
}
