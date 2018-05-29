package ca.easterndevelopers.finalproject.player;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.icu.util.UniversalTimeScale;

import java.util.ArrayList;

import ca.easterndevelopers.finalproject.GUI.GUI;
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
        unit.init(this.getCurrentLevel());
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
        }
    }

    public void updateUnits() {
        int index = 0;
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
                    this.indexOfActiveUnit = index;
                    if(!u.isDoneTurn())
                        u.setActive();
                }
            }
            index++;

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
            unit.resetUnit();
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
            GameActivity.getContext().startActivity(i);
        }
    }
}
