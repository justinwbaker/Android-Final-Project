package ca.easterndevelopers.finalproject.level.gameobject;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public abstract class GameObject {

    private Point position;
    private Point size;

    public abstract void update(double fps);
    public abstract void render(Canvas canvas, Paint paint);

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getSize() {
        return size;
    }

    public void setSize(Point size) {
        this.size = size;
    }
}
