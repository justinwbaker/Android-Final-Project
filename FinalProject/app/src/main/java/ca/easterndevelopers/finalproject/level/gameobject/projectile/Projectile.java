package ca.easterndevelopers.finalproject.level.gameobject.projectile;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import ca.easterndevelopers.finalproject.level.gameobject.GameObject;

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
    public int startX;
    public int startY;

    int color;

    public Projectile(int targetX, int targetY, int startX, int startY, Point size, int color) {

        super(new Point(startX, startY), new Point (size));

        this.setSize(size);
        this.x = startX;
        this.y = startY;
        this.startX = startX;
        this.startY = startY;
        this.targetX = targetX;
        this.targetY = targetY;
        this.color = color;

        distance = Math.sqrt(Math.pow((startX-targetX), 2) + Math.pow((startY-targetY), 2));

        direction = -(float)(Math.atan2(targetX - x, targetY - y) - Math.PI/2);

        changeX = (float)Math.cos(direction);
        changeY = (float)Math.sin(direction);
    }

    public Projectile(Projectile projectile){
        super(new Point(projectile.x, projectile.y), new Point(10, 10));

        this.setSize(projectile.getSize());
        this.x = projectile.x;
        this.y = projectile.y;
        this.distance = projectile.distance;
        this.targetX = projectile.targetX;
        this.targetY = projectile.targetY;
        this.speed = projectile.speed;
        this.direction = projectile.direction;
        this.changeX = projectile.changeX;
        this.changeY = projectile.changeY;

    }

    public void update(double fps){

        x = x + (int)(speed * changeX/fps);
        y = y + (int)(speed * changeY/fps);
    }

    @Override
    public void render(Canvas canvas, Paint paint) {
        paint.setColor(this.color);
        canvas.drawRect(this.x - this.getSize().x/2, this.y- this.getSize().y/2, this.x + this.getSize().x/2, this.y + this.getSize().y/2, paint);
    }
}
