package ca.easterndevelopers.finalproject.level.gameobject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import junit.runner.TestSuiteLoader;

import ca.easterndevelopers.finalproject.GUI.GUI;
import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.game.Game;
import ca.easterndevelopers.finalproject.level.Level;
import ca.easterndevelopers.finalproject.level.gameobject.projectile.Projectile;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.Weapon;
import ca.easterndevelopers.finalproject.player.Enemy;
import ca.easterndevelopers.finalproject.renderer.GameRenderer;
import ca.easterndevelopers.finalproject.utils.Utils;

public abstract class Unit extends GameObject {

    protected float baseDamage;
    protected float movementRange;

    protected int timeCanMove;
    protected int timesHasMoved;
    private boolean hasAttackedRanged;
    protected int health;

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
        this.health = 7;
    }

    public void update(double fps) {
            if (this.isActiveUnit && !GUI.isOnGUI) {
                if (timesHasMoved < timeCanMove) {
                    if (GameRenderer.getWorldTouchedPoint() != null) {
                        int x = GameRenderer.getWorldTouchedPoint().x;
                        int y = GameRenderer.getWorldTouchedPoint().y;

                        Rect tilePosition = new Rect(x, y, x + 1, y + 1);
                        if (Math.abs(Utils.getDistance(Utils.toTiledPosition(this.getPosition()), Utils.toTiledPosition(new Point(x, y)))) < movementRange) {
                            int xpos = GameRenderer.getWorldTouchedPoint().x - (int) (GameRenderer.getWorldTouchedPoint().x % MainActivity.getTileSize());
                            int ypos = GameRenderer.getWorldTouchedPoint().y - (int) (GameRenderer.getWorldTouchedPoint().y % MainActivity.getTileSize());

                            this.setPosition(new Point(xpos, ypos));
                            this.timesHasMoved++;
                            if (Game.debug) System.out.println("Is in range");

                            for (Unit u : this.getLevel().getEnemy().getUnits()) {
                                if ((Math.abs(Math.sqrt(Math.pow((this.getPosition().x - u.getPosition().x), 2) +
                                        Math.pow((this.getPosition().y - u.getPosition().y), 2)))) <= MainActivity.getTileSize()) {
                                    u.takeDamage(this.melee.getDamage());
                                    u.update(0);

                                } // melee attack check and action upon move
                            }
                        }
                    }
                } else if (ranged.getAmmo() != 0 && GameRenderer.getWorldTouchedPoint() != null && !hasAttackedRanged) {
                    rangedAttack();
                }
            }
    }

    public void render(Canvas canvas, Paint paint){
        if(this.isActiveUnit) {
            this.showAttackRange(canvas, paint); // need to change this to only check if we want to attack
        }
        if(this.isActiveUnit && timesHasMoved != timeCanMove) {
            this.showMovementRange(canvas, paint);
        }
        paint.setColor(Color.WHITE);
        canvas.drawBitmap(image, getPosition().x, getPosition().y, paint);

        if(Game.debug){
            paint.setColor(Color.argb(100, 255, 20, 20));
            canvas.drawRect(this.getHitbox(), paint);

        }
    }

    public void showMovementRange(Canvas canvas, Paint paint) {
        for(int i = 0; i < this.getLevel().getHeight(); i++) {
            for(int j = 0; j < this.getLevel().getWidth(); j++) {
                Point tilePosition = new Point(j, i);
                if(Math.abs(Utils.getDistance(Utils.toTiledPosition(this.getPosition()), tilePosition)) < movementRange){
                    //draw transparent squares
                    paint.setColor(Color.argb(200, 0, 153, 204));
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
                    paint.setColor(Color.argb(70, 0, 0, 0));
                    canvas.drawRect(tilePosition.x*MainActivity.getTileSize(), tilePosition.y*MainActivity.getTileSize(), tilePosition.x*MainActivity.getTileSize() + MainActivity.getTileSize(), tilePosition.y*MainActivity.getTileSize() + MainActivity.getTileSize(),  paint);
                }
            }
        }
    }

    public void rangedAttack() {
        hasAttackedRanged = true;
        ranged.setAmmo(ranged.getAmmo() - 1);
        Projectile projectile = new Projectile(
                GameRenderer.getWorldTouchedPoint().x,
                GameRenderer.getWorldTouchedPoint().y,
                this.getPosition().x + (int) MainActivity.getTileSize() / 2,
                this.getPosition().y + (int) MainActivity.getTileSize() / 2,
                this.ranged.getSize(), this.ranged.getColor(), this);
        this.getLevel().addGameObject(projectile);
    }

    public void levelUp() {

    }

    public void takeDamage(int damage){

        this.health -= damage;

        if(Game.debug) System.out.println(this.health);

        if(health <= 0){
            if(Game.debug) System.out.println("Unit has died");
            this.remove();
        }
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

    public void setNotActive() {
        this.isActiveUnit = false;
    }

    public Rect getHitbox(){
        hitbox = new Rect(this.getPosition().x+((int)getPixelSize()*3), this.getPosition().y+((int)getPixelSize()), this.getPosition().x + this.getSize().x-((int)getPixelSize()*3), this.getPosition().y+this.getSize().y-((int)getPixelSize()));
        return hitbox;
    }

    public Rect getWorldHitbox(){
        hitbox = new Rect(this.getPosition().x+1, this.getPosition().y+1, this.getSize().x-1, this.getSize().y-1);
        return hitbox;
    }

    public boolean isDoneTurn() {
        return (this.hasAttackedRanged && (this.timeCanMove == this.timesHasMoved));
    }

    public float getPixelSize() {
        return MainActivity.getTileSize()/16;
    }

    public RangedWeapon getRangedWeapon(){
        return this.ranged;
    }

    public int getHealth(){
        return health;
    }
}
