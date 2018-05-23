package ca.easterndevelopers.finalproject.level.gameobject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import ca.easterndevelopers.finalproject.level.Level;

public abstract class GameObject {

    protected Point position;
    protected Point size;

    protected boolean isRemoved;

    protected Level level;

    public GameObject(Point position, Point size) {
        this.isRemoved = false;
        this.position = position;
        this.size = size;
    }

    public void init(Level level) {
        this.level = level;
    }

    public abstract void update(double fps);
    public abstract void render(Canvas canvas, Paint paint);

    public boolean isRemoved() {
        return isRemoved;
    }

    public void remove() {
        this.isRemoved = true;
    }

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

    public Level getLevel() {
        return this.level;
    }

    public Rect getHitbox() {
        return new Rect(this.position.x, this.position.y, this.position.x + this.size.x, this.position.y + this.size.y);
    }
}
