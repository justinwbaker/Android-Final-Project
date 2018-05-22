package ca.easterndevelopers.finalproject.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.R;
import ca.easterndevelopers.finalproject.level.Level;
import ca.easterndevelopers.finalproject.level.LevelManager;
import ca.easterndevelopers.finalproject.level.gameobject.Soldier;
import ca.easterndevelopers.finalproject.level.tile.GrassTile;
import ca.easterndevelopers.finalproject.player.Player;

public class Game {

    private Context context;

    private static boolean isLookingAtMap = false;

    private static Player player;

    public static boolean debug = true;

    float x = 0;

    public Game(Context context) {
        this.context = context;
        LevelManager.loadLevel(context, R.drawable.level1);
        System.out.println();

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
    }

    public static Player getPlayer() {
        return player;
    }

    public static boolean isLookingAtMap() {
        return isLookingAtMap;
    }

}
