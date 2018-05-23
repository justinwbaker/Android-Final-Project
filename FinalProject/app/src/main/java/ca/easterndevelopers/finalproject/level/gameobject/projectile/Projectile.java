package ca.easterndevelopers.finalproject.level.gameobject.projectile;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import ca.easterndevelopers.finalproject.level.gameobject.GameObject;
import ca.easterndevelopers.finalproject.level.gameobject.Unit;

public class Projectile extends GameObject {

    private int x;
    private int y;
    private double distance;
    private int targetX;
    private int targetY;
    private int speed = 1000;
    private float direction;
    private float changeX;
    private float changeY;
    private int startX;
    private int startY;
    private Unit unit;
    private Rect rect;

    int color;

    public Projectile(int targetX, int targetY, int startX, int startY, Point size, int color, Unit unit) {

        super(new Point(startX, startY), new Point (size));

        this.setSize(size);
        this.x = startX;
        this.y = startY;
        this.startX = startX;
        this.startY = startY;
        this.targetX = targetX;
        this.targetY = targetY;
        this.color = color;
        this.unit = unit;

        distance = Math.sqrt(Math.pow((startX-targetX), 2) + Math.pow((startY-targetY), 2));

        direction = -(float)(Math.atan2(targetX - x, targetY - y) - Math.PI/2);

        changeX = (float)Math.cos(direction);
        changeY = (float)Math.sin(direction);


    }


    public void update(double fps){

        x = x + (int)(speed * changeX/fps);
        y = y + (int)(speed * changeY/fps);
        this.rect = new Rect((this.x - this.getSize().x/2), (this.y- this.getSize().y/2), (this.x + this.getSize().x/2), (this.y + this.getSize().y/2));

    }

    @Override
    public void render(Canvas canvas, Paint paint) {
        paint.setColor(this.color);
        canvas.drawRect(this.x - this.getSize().x/2, this.y- this.getSize().y/2, this.x + this.getSize().x/2, this.y + this.getSize().y/2, paint);
    }

    public Rect getHitbox(){
        return rect;
    }
}
