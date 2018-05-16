package ca.easterndevelopers.finalproject.level;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import ca.easterndevelopers.finalproject.level.tile.Tile;

public class Level {

    private int width, height;
    private Context context;

    private Tile tiles[];

    public Level(int width, int height, Context context) {
        this.width = width;
        this.height = height;
        this.context = context;

        tiles = new Tile[width*height];
    }

    public void setTile(int x, int y, Tile tile) {
        if(x >= 0 && y >= 0 && x < width && y < height) {
            tiles[x+y*width] = tile;
        }
    }

    public void update(double fps) {

    }

    public void render(Canvas canvas, Paint paint) {
        for(int i = 0; i < width*height; i++) {
            tiles[i].render(canvas,paint);
        }
    }

    public Context getContext() {
        return context;
    }
}
