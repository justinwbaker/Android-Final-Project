package ca.easterndevelopers.finalproject.player;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

import ca.easterndevelopers.finalproject.game.Game;
import ca.easterndevelopers.finalproject.level.Level;
import ca.easterndevelopers.finalproject.level.gameobject.Unit;
import ca.easterndevelopers.finalproject.renderer.GameRenderer;

public class Player {

    protected ArrayList<Unit> units;
    protected boolean isPlayersTurn;

    protected int indexOfActiveUnit = -1;

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
        for (Unit u : units) {
            u.render(canvas, paint);
            if(Game.debug) {
                if (GameRenderer.getWorldTouchedPoint() != null) {
                    int x = GameRenderer.getWorldTouchedPoint().x;
                    int y = GameRenderer.getWorldTouchedPoint().y;
                    paint.setColor(Color.BLUE);
                    Rect hitBox = new Rect((int) (u.getPosition().x), (int) (u.getPosition().y), (int) (u.getPosition().x) + u.getSize().x, (int) (u.getPosition().y) + u.getSize().y);
                    canvas.drawRect(hitBox, paint);
                }
            }
        }


    }

    public void updateUnits() {
        int index = 0;
        for(Unit u : units){
            u.update(0.0);
            if(GameRenderer.getWorldTouchedPoint() != null) {
                int x = GameRenderer.getWorldTouchedPoint().x;
                int y = GameRenderer.getWorldTouchedPoint().y;

                Rect hitBox = new Rect((int) (u.getPosition().x), (int) (u.getPosition().y ), (int) (u.getPosition().x) + u.getSize().x, (int) (u.getPosition().y ) + u.getSize().y);
                if(Rect.intersects(hitBox, new Rect(x, y, x+1, y+1))){
                    for(Unit unit : units){
                        unit.setNotActive();
                    }
                    this.indexOfActiveUnit = index;
                    u.setActive();
                }
            }
            index++;
        }
    }

    public void setNextActiveUnit() {
        int unitsDone = 0;
        for(Unit u : units){
            if(u.isDoneTurn()) unitsDone++;
        }

        if(unitsDone == this.units.size()) {
            this.endTurn();
        }else {

            this.indexOfActiveUnit++;
            if (this.indexOfActiveUnit >= this.units.size()) {
                this.indexOfActiveUnit = 0;
            }

            if (this.units.get(indexOfActiveUnit).isDoneTurn()) {
                setNextActiveUnit();
            } else {
                for (Unit unit : units) {
                    unit.setNotActive();
                }
                units.get(indexOfActiveUnit).setActive();
            }
        }
    }

    public void endTurn(){
        this.isPlayersTurn = false;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }
}
