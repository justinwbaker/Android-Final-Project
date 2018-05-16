package ca.easterndevelopers.finalproject.level.gameobject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.level.Level;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.Weapon;
import ca.easterndevelopers.finalproject.utils.Utils;

public abstract class Unit extends GameObject {

    protected float baseDamage;
    protected float movementRange;

    protected int timeCanMove;
    protected int timesHasMoved;

    protected boolean isAcrtiveUnit;

    int costToLevel;

    private Bitmap image;
    protected Weapon melee;
    protected RangedWeapon ranged;

    public Unit(Point position, Point size) {
        super(position, size);
    }

    public void update(double fps) {

    }

    public void render(Canvas canvas, Paint paint){
        if(this.isAcrtiveUnit) {
            this.showMovementRange(canvas, paint);
        }
        canvas.drawBitmap(image, getPosition().x, getPosition().y, paint);
    }

    public void move() {

    }

    public void showMovementRange(Canvas canvas, Paint paint) {
        for(int i = 0; i < this.getLevel().getHeight(); i++) {
            for(int j = 0; j < this.getLevel().getWidth(); j++) {
                Point tilePosition = new Point(j, i);
                if(Math.abs(Utils.getDistance(Utils.toTiledPosition(Utils.toTiledPosition(this.getPosition())), tilePosition)) < movementRange){
                    //draw trasnparent squares
                    System.out.println(getPosition().y + " " + j + " " + Utils.toTiledPosition(this.getPosition()).x + " " + GameActivity.getTileSize());
                    paint.setColor(Color.argb(150, 0, 153, 204));
                    canvas.drawRect(j*GameActivity.getTileSize(), i*GameActivity.getTileSize(), j*GameActivity.getTileSize() + GameActivity.getTileSize(), i*GameActivity.getTileSize() + GameActivity.getTileSize(),  paint);
                }
            }
        }
    }

    public void meleeAttack() {

    }

    public void rangedAttack() {

    }

    public void levelUp() {

    }

    public void setBitmap(Bitmap image) {
        this.image = image;
        if(this.getSize().x != image.getWidth() || this.getSize().x != image.getWidth()){
            this.image = Utils.getResizedBitmap(image, getSize().x, getSize().y, true);
        }
    }

    public void setActive() {
        this.isAcrtiveUnit = true;
    }

}
