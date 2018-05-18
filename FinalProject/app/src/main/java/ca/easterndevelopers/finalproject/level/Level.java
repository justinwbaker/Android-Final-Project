package ca.easterndevelopers.finalproject.level;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

import ca.easterndevelopers.finalproject.game.Game;
import ca.easterndevelopers.finalproject.level.gameobject.GameObject;
import ca.easterndevelopers.finalproject.level.tile.Tile;
import ca.easterndevelopers.finalproject.player.Enemy;
import ca.easterndevelopers.finalproject.player.Player;

public class Level {

    private int width, height;
    private Context context;

    private Tile tiles[];
    private ArrayList<GameObject> objects;

    private Enemy enemy;
    private Player player;

    public Level(int width, int height, Context context) {
        this.width = width;
        this.height = height;
        this.context = context;

        tiles = new Tile[width*height];
        objects = new ArrayList<GameObject>();
    }

    public void init(Player player){
        this.player = player;
    }

    public void setTile(int x, int y, Tile tile) {
        if(x >= 0 && y >= 0 && x < width && y < height) {
            tiles[x+y*width] = tile;
        }
    }

    public void update(double fps) {
        for (GameObject go: objects) {
            go.update(fps);
        }
    }

    public void render(Canvas canvas, Paint paint) {
        for(int i = 0; i < width*height; i++) {
            tiles[i].render(canvas,paint);
        }

        for (GameObject go: objects) {
            go.render(canvas, paint);
        }

        if(this.player != null) {
            this.player.renderUnits(canvas, paint);
        }
    }

    public Context getContext() {
        return context;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addGameObject(GameObject GO){
        GO.init(this);
        objects.add(GO);
    }
}
