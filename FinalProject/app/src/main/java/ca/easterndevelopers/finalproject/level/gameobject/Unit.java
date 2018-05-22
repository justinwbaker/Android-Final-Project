package ca.easterndevelopers.finalproject.level.gameobject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.game.Game;
import ca.easterndevelopers.finalproject.level.gameobject.projectile.Projectile;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.Weapon;
import ca.easterndevelopers.finalproject.renderer.GameRenderer;
import ca.easterndevelopers.finalproject.utils.Utils;

public abstract class Unit extends GameObject {

    protected float baseDamage;
    protected float movementRange;

    protected int timeCanMove;
    protected int timesHasMoved;
    private boolean hasAttackedRanged;
    private boolean hasAttackedMelee;

    protected boolean isActiveUnit;

    int costToLevel;

    private Bitmap image;
    protected Weapon melee;
    protected RangedWeapon ranged;
    private Rect hitbox;

    public Unit(Point position, Point size) {
        super(position, size);
        this.baseDamage = 1;
        this.movementRange = 2;
        this.timesHasMoved = 0;
        this.timeCanMove = 1;
        this.isActiveUnit = false;
        this.costToLevel = 10;
    }

    public void update(double fps) {
        if(this.isActiveUnit && timesHasMoved < timeCanMove) {
            if(Math.abs(Utils.getDistance(Utils.toTiledPosition(this.getPosition()), Utils.toTiledPosition(GameRenderer.getTouchedPoint()))) < movementRange) {
                this.setPosition(Utils.toWorldPosition(Utils.toTiledPosition(GameRenderer.getTouchedPoint())));
                this.timesHasMoved++;
            }
        }
        else /* if(!hasAttackedRanged && !hasAttackedMelee)*/{ // will also need " && shoot option is selected"
            if(ranged.getAmmo() != 0) {

                rangedAttack();
            }
        }
/*
        else if(!hasAttackedRanged && !hasAttackedMelee) { // will be the " && melee option selected"

            meleeAttack();
        }*/
    }

    public void render(Canvas canvas, Paint paint){
        if(this.isActiveUnit && timesHasMoved != timeCanMove) {
            this.showMovementRange(canvas, paint);
            this.showAttackRange(canvas, paint); // need to change this to only check if we want to attack
        }
        paint.setColor(Color.WHITE);
        canvas.drawBitmap(image, getPosition().x, getPosition().y, paint);

        if(Game.debug){

        }
    }

    public void move() {

    }

    public void showMovementRange(Canvas canvas, Paint paint) {
        for(int i = 0; i < this.getLevel().getHeight(); i++) {
            for(int j = 0; j < this.getLevel().getWidth(); j++) {
                Point tilePosition = new Point(j, i);
                if(Math.abs(Utils.getDistance(Utils.toTiledPosition(this.getPosition()), tilePosition)) < movementRange){
                    //draw transparent squares
                    paint.setColor(Color.argb(100, 0, 153, 204));
                    canvas.drawRect(tilePosition.x* MainActivity.getTileSize(), tilePosition.y*MainActivity.getTileSize(), tilePosition.x*MainActivity.getTileSize() + MainActivity.getTileSize(), tilePosition.y*MainActivity.getTileSize() + MainActivity.getTileSize(),  paint);
                }
            }
        }
    }

    public void showAttackRange(Canvas canvas, Paint paint) {
        for(int i = 0; i < this.getLevel().getHeight(); i++) {
            for(int j = 0; j < this.getLevel().getWidth(); j++) {
                Point tilePosition = new Point(j, i);
                if(Math.abs(Utils.getDistance(Utils.toTiledPosition(this.getPosition()), tilePosition)) < ranged.getRange()){
                    //draw transparent squares
                    paint.setColor(Color.argb(150, 153, 50, 0));
                    canvas.drawRect(tilePosition.x*MainActivity.getTileSize(), tilePosition.y*MainActivity.getTileSize(), tilePosition.x*MainActivity.getTileSize() + MainActivity.getTileSize(), tilePosition.y*MainActivity.getTileSize() + MainActivity.getTileSize(),  paint);
                }
            }
        }
    }

    public void meleeAttack() {
        if(!hasAttackedMelee && !hasAttackedRanged) {

        }
    }

    public void rangedAttack() {
        if(!hasAttackedMelee && !hasAttackedRanged) {
            hasAttackedRanged = true;
            ranged.setAmmo(ranged.getAmmo() - 1);
            Projectile projectile = new Projectile(
                    GameRenderer.getTouchedPoint().x,
                    GameRenderer.getTouchedPoint().y,
                    this.getPosition().x + (int) MainActivity.getTileSize() / 2,
                    this.getPosition().y + (int) MainActivity.getTileSize() / 2,
                    this.ranged.getSize(), this.ranged.getColor());
            this.getLevel().addGameObject(projectile);
        }
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
        this.isActiveUnit = true;
    }

    public void initHitbox(){
        hitbox = new Rect(this.getPosition().x+1, this.getPosition().y+1, this.getSize().x-1, this.getSize().y-1);

    }



}
