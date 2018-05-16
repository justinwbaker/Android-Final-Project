package ca.easterndevelopers.finalproject.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.widget.Button;

import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.level.Level;
import ca.easterndevelopers.finalproject.level.tile.GrassTile;

public class Game {

    private Context context;

    Level testLevel;

    float x = 0;

    public Game(Context context) {
        this.context = context;
        testLevel = new Level(100, 100, context);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                testLevel.setTile(i, j, new GrassTile(new Point(i, j), testLevel));
            }
        }
    }

    public void update(double fps) {
        x+= GameActivity.getTileSize()/fps;
        testLevel.update(fps);
    }

    public void render(Canvas canvas, Paint paint) {
        testLevel.render(canvas, paint);
        paint.setColor(Color.BLUE);
        canvas.drawRect(x, 100, x+100, 200, paint);

    }

}
