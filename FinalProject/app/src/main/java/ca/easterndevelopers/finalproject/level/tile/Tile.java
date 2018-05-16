package ca.easterndevelopers.finalproject.level.tile;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import ca.easterndevelopers.finalproject.GameActivity;

public abstract class Tile {

    protected Point position;
    protected Bitmap image;

    public Tile(Point position) {
        this.position = position;
    }

    public void update(double fps){

    }

    public void render(Canvas canvas, Paint paint){
        canvas.drawBitmap(image, position.x * GameActivity.getTileSize(), position.y * GameActivity.getTileSize(), paint);
    }

}
