package ca.easterndevelopers.finalproject.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import ca.easterndevelopers.finalproject.GUI.GUI;
import ca.easterndevelopers.finalproject.GUI.GameGUI;
import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.MainScreen;
import ca.easterndevelopers.finalproject.R;
import ca.easterndevelopers.finalproject.level.Level;
import ca.easterndevelopers.finalproject.level.LevelManager;
import ca.easterndevelopers.finalproject.level.gameobject.Soldier;
import ca.easterndevelopers.finalproject.level.tile.GrassTile;
import ca.easterndevelopers.finalproject.player.Player;

public class Game {

    private Context context;
    private static GUI gui;

    private static boolean isLookingAtMap = false;

    public static boolean debug = true;

    public Game(Context context) {
        this.context = context;
        gui = new GameGUI();

        MainScreen.getPlayer().addUnit(new Soldier(new Point((int) MainActivity.getTileSize()*6, (int)MainActivity.getTileSize()*6)));
        MainScreen.getPlayer().playLevel(LevelManager.getLevel(0));
        MainScreen.getPlayer().startTurn();
    }

    public void update(double fps) {
        MainScreen.getPlayer().getCurrentLevel().update(fps);
    }

    public void render(Canvas canvas, Paint paint) {
        MainScreen.getPlayer().getCurrentLevel().render(canvas, paint);
        gui.render(canvas, paint);
    }

    public static Player getPlayer() {
        return MainScreen.getPlayer();
    }

    public static boolean isLookingAtMap() {
        return isLookingAtMap;
    }

    public static void setIsLookingAtMap(boolean _isLookingAtMap) {isLookingAtMap = _isLookingAtMap; }

    public static GUI getGameGUI() {
        return gui;
    }

}
