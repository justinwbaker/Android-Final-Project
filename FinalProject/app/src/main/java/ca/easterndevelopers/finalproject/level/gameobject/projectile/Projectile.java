package ca.easterndevelopers.finalproject.level.gameobject.projectile;

import android.graphics.Color;
import android.graphics.Point;

import ca.easterndevelopers.finalproject.level.gameobject.GameObject;

public abstract class Projectile extends GameObject {

    private int x;
    private int y;
    private double distance;
    private int targetX;
    private int targetY;
    private int speed = 100;
    private float direction;
    private float changeX;
    private float changeY;

    Color color;

    public Projectile(int targetX, int targetY, int startX, int startY) {

        super(new Point(startX, startY), new Point(1, 1));

        this.x = startX;
        this.y = startY;
        this.targetX = targetX;
        this.targetY = targetY;

        distance = Math.sqrt(Math.pow((startX-targetX), 2) + Math.pow((startY-targetY), 2));

        direction = (float)Math.toDegrees(Math.atan2(targetX - x, targetY - y));

        changeX = speed * (float)Math.cos(direction);
        changeY = speed * (float)Math.sin(direction);
    }

    public void update(double fps){

        x = x + (int)(changeX/fps);
        y = y + (int)(changeY/fps);
    }
}
