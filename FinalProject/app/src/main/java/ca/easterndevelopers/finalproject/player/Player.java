package ca.easterndevelopers.finalproject.player;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.MainScreen;
import ca.easterndevelopers.finalproject.ShopActivity;
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
        if(Game.debug) System.out.println("Started Turn");
    }

    public boolean isPlayersTurn() {
        return isPlayersTurn;
    }

    public void playLevel(Level level) {
        currentLevel = level;
        level.init(this);
    }

    public Level getCurrentLevel() {
        return this.currentLevel;
    }

    public void renderUnits(Canvas canvas, Paint paint) {
        for (Unit u : units) {
            u.render(canvas, paint);
        }
    }

    public void updateUnits() {
        for (int i = 0; i < units.size(); i++) {
            Unit u = units.get(i);
            u.update(0.0);
            if (GameRenderer.getWorldTouchedPoint() != null && !Game.isLookingAtMap() && this.isPlayersTurn) {
                int x = GameRenderer.getWorldTouchedPoint().x;
                int y = GameRenderer.getWorldTouchedPoint().y;

                Rect hitBox = new Rect((int) (u.getPosition().x), (int) (u.getPosition().y), (int) (u.getPosition().x) + u.getSize().x, (int) (u.getPosition().y) + u.getSize().y);
                if (Rect.intersects(hitBox, new Rect(x, y, x + 1, y + 1))) {
                    for (Unit unit : units) {
                        unit.setNotActive();
                    }
                    this.indexOfActiveUnit = i;
                    if(!u.isDoneTurn())
                        u.setActive();
                }
            }

            if (u.isRemoved()) {
                this.units.remove(u);
            }
        }

        if(Game.isLookingAtMap()) {
            for (Unit unit : units) {
                unit.setNotActive();
            }
        }
    }

    public void setNextActiveUnit() {
        if(!Game.isLookingAtMap()) {
            int unitsDone = 0;
            for (Unit u : units) {
                if (u.isDoneTurn()) unitsDone++;
            }

            if (unitsDone == this.units.size()) {

                this.getCurrentLevel().endPlayersTurn();

            } else {

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
    }

    public void endTurn(){
        this.isPlayersTurn = false;
        for (Unit unit : this.getUnits()) {
            unit.setNotActive();
            unit.endTurn();
        }
        if(Game.debug) System.out.println("Ended Turn");

    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void soldierWipe(){
        if(MainScreen.getGold() >= 100){
            // bring to store
            Intent i = new Intent(this.currentLevel.getContext(), ShopActivity.class);
            GameActivity.getContext().startActivity(i);

        }
        else{
            // bring to title
            Intent i = new Intent(this.currentLevel.getContext(), MainActivity.class);
            MainActivity.getLocalPrefs().edit().clear().apply();
            GameActivity.getContext().startActivity(i);
        }
    }

    public void placeUnit(int unitIndex, int x, int y) {
        units.get(unitIndex).setPosition(new Point((int)MainActivity.getTileSize() * x, (int)MainActivity.getTileSize() * y));
        units.get(unitIndex).init(this.getCurrentLevel());
    }

    public boolean hasPlacedAllUnits() {
        boolean hasPlacedAll = true;
        for(int i = 0; i < units.size(); i++) {
            if(!units.get(i).isOnLevel()) {
                hasPlacedAll = false;
                break;
            }
        }
        return hasPlacedAll;
    }

    public int getUnplacedUnit() {
        for(int i = 0; i < units.size(); i++) {
            if(!units.get(i).isOnLevel()) {
                return i;
            }
        }
        return -1;
    }
}
