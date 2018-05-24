package ca.easterndevelopers.finalproject.level.gameobject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import ca.easterndevelopers.finalproject.game.Game;

public class Collider extends GameObject {

    public Collider(Point position, Point size) {
        super(position, size);
    }

    @Override
    public void update(double fps) {

    }

    @Override
    public void render(Canvas canvas, Paint paint) {
        if(Game.debug) {
            paint.setColor(Color.argb(100, 0, 0, 255));
            canvas.drawRect(this.getHitbox(), paint);
        }
    }
}
