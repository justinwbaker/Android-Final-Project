package ca.easterndevelopers.finalproject.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import ca.easterndevelopers.finalproject.GUI.GUI;
import ca.easterndevelopers.finalproject.GUI.GameGUI;
import ca.easterndevelopers.finalproject.GameActivity;
<<<<<<< HEAD
=======
import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.MainScreen;
>>>>>>> 83f0a9fd15766e477a1c935f0cd967711c400176
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

<<<<<<< HEAD
    private static Player player;

    public static boolean debug = true;

    float x = 0;

=======
    public static boolean debug = true;

>>>>>>> 83f0a9fd15766e477a1c935f0cd967711c400176
    public Game(Context context) {
        this.context = context;
        gui = new GameGUI();

<<<<<<< HEAD
        player = new Player();
        player.addUnit(new Soldier(new Point((int)(GameActivity.getTileSize()*7), (int)(GameActivity.getTileSize()*5))));
        player.playLevel(LevelManager.getLevel(0));
        player.startTurn();
    }

    public void update(double fps) {
        x += GameActivity.getTileSize()*2/fps;
        if(x > GameActivity.getResolution().x - GameActivity.getTileSize()) {
            x = 0;
        }
        player.getCurrentLevel().update(fps);
    }

    public void render(Canvas canvas, Paint paint) {
        player.getCurrentLevel().render(canvas, paint);
=======
        MainScreen.getPlayer().addUnit(new Soldier(new Point((int) MainActivity.getTileSize()*3, (int)MainActivity.getTileSize()*3)));
        MainScreen.getPlayer().playLevel(LevelManager.getLevel(0));
        MainScreen.getPlayer().startTurn();
    }

    public void update(double fps) {
        MainScreen.getPlayer().getCurrentLevel().update(fps);
    }

    public void render(Canvas canvas, Paint paint) {
        MainScreen.getPlayer().getCurrentLevel().render(canvas, paint);
        gui.render(canvas, paint);
>>>>>>> 83f0a9fd15766e477a1c935f0cd967711c400176
    }

    public static Player getPlayer() {
        return player;
    }

    public static boolean isLookingAtMap() {
        return isLookingAtMap;
    }

    public static void setIsLookingAtMap(boolean _isLookingAtMap) {isLookingAtMap = _isLookingAtMap; }

    public static GUI getGameGUI() {
        return gui;
    }

}
