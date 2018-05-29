package ca.easterndevelopers.finalproject.level.gameobject.projectile;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.level.gameobject.GameObject;
import ca.easterndevelopers.finalproject.level.gameobject.Unit;
import ca.easterndevelopers.finalproject.renderer.GameRenderer;

public class Projectile extends GameObject {

    private double distance;
    private double targetX;
    private double targetY;
    private int speed = 1000;
    private float direction;
    private float changeX;
    private float changeY;
    private int startX;
    private int startY;
    private Unit unit;

    int color;

    public Projectile(double targetX, double targetY, int startX, int startY, Point size, int color, Unit unit) {

        super(new Point(startX, startY), new Point (size));

        this.setSize(size);
        this.startX = startX;
        this.startY = startY;
        this.targetX = targetX;
        this.targetY = targetY;
        this.color = color;
        this.unit = unit;

        distance = Math.sqrt(Math.pow((startX-targetX), 2) + Math.pow((startY-targetY), 2));

        direction = -(float)(Math.atan2(targetX - this.position.x, targetY - this.position.y) - Math.PI/2);

        changeX = (float)Math.cos(direction);
        changeY = (float)Math.sin(direction);


    }

    public Projectile(double targetX, double targetY, int startX, int startY, Point size, int color, Unit unit, double directionRand) {

        super(new Point(startX, startY), new Point (size));

        this.setSize(size);
        this.startX = startX;
        this.startY = startY;
        this.targetX = targetX;
        this.targetY = targetY;
        this.color = color;
        this.unit = unit;

        distance = Math.sqrt(Math.pow((startX-targetX), 2) + Math.pow((startY-targetY), 2));

        direction = -(float)(Math.atan2(targetX - this.position.x, targetY - this.position.y) - Math.PI/2) + (float)directionRand;

        changeX = (float)Math.cos(direction);
        changeY = (float)Math.sin(direction);


    }



    public void update(double fps){

        this.position.x += (int)(speed * changeX/fps);
        this.position.y += (int)(speed * changeY/fps);

        if(this.position.x < 0 || this.position.y < 0 || this.position.x > level.getWidth()*MainActivity.getTileSize()-this.getSize().x ||  this.position.y > level.getHeight()*MainActivity.getTileSize()-this.getSize().y)
            this.remove();

    }

    @Override
    public void render(Canvas canvas, Paint paint) {
        paint.setColor(this.color);
        canvas.drawRect(this.getHitbox(), paint);
    }

    public Unit getUnit(){
        return unit;
    }
}
