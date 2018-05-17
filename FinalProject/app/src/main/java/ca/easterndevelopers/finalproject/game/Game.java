package ca.easterndevelopers.finalproject.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.level.Level;
import ca.easterndevelopers.finalproject.level.gameobject.Soldier;
import ca.easterndevelopers.finalproject.level.tile.GrassTile;
import ca.easterndevelopers.finalproject.player.Player;

public class Game {

    private Context context;

    private static boolean isLookingAtMap = false;

    Level testLevel;

    private static Player player;

    float x = 0;

    public Game(Context context) {
        this.context = context;
        player = new Player();
        testLevel = new Level(100, 100, context);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                testLevel.setTile(i, j, new GrassTile(new Point(i, j), testLevel));
            }
        }
        player.addUnit(new Soldier(new Point(480, 480)));
        player.playLevel(testLevel);
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
    }

    public static Player getPlayer() {
        return player;
    }

    public static boolean isLookingAtMap() {
        return isLookingAtMap;
    }

}
