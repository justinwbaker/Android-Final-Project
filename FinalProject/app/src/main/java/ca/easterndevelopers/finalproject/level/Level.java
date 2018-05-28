package ca.easterndevelopers.finalproject.level;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

import ca.easterndevelopers.finalproject.GameActivity;
import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.game.Game;
import ca.easterndevelopers.finalproject.level.gameobject.Collider;
import ca.easterndevelopers.finalproject.level.gameobject.GameObject;
import ca.easterndevelopers.finalproject.level.gameobject.Soldier;
import ca.easterndevelopers.finalproject.level.gameobject.Unit;
import ca.easterndevelopers.finalproject.level.gameobject.projectile.Projectile;
import ca.easterndevelopers.finalproject.level.tile.Tile;
import ca.easterndevelopers.finalproject.player.Enemy;
import ca.easterndevelopers.finalproject.player.Player;

import static ca.easterndevelopers.finalproject.MainActivity.friendlyFire;

public class Level {

    private int width, height;
    private Context context;

    private Tile tiles[];
    private ArrayList<GameObject> objects;

    private Enemy enemy;
    private Player player;

    public Level(int width, int height, Context context) {
        this.width = width;
        this.height = height;
        this.context = context;

        tiles = new Tile[width*height];
        objects = new ArrayList<GameObject>();
        this.enemy = new Enemy();
        this.enemy.playLevel(this);
    }

    public void init(Player player){
        this.player = player;
    }

    public void setTile(int x, int y, Tile tile) {
        if(x >= 0 && y >= 0 && x < width && y < height) {
            tiles[x+y*width] = tile;
        }
    }

    public Tile getTile(int x, int y) {
        if(x >= 0 && y >= 0 && x < width && y < height) {
            return tiles[x+y*width];
        }else {
            return null;
        }
    }

    public void update(double fps) {

        this.player.updateUnits();
        this.enemy.updateUnits();

        for (GameObject go: objects) {
            go.update(fps);

            if( (go instanceof Projectile)  ){
                Projectile projectile = (Projectile) go;


                for(GameObject object: objects) {
                    if( (go instanceof Projectile || object instanceof Collider)  ) {
                        GameObject go2 = object;
                        if(projectile != go2 && Rect.intersects(projectile.getHitbox(), go2.getHitbox())) {
                            projectile.remove();
                        }
                        // if ( abs of((projectile location) + unit location ) > weapon range
                        else if((Math.abs(Math.sqrt(Math.pow((projectile.getPosition().x - projectile.getUnit().getPosition().x), 2)
                                + Math.pow((projectile.getPosition().y - projectile.getUnit().getPosition().y), 2))))
                                >= (projectile.getUnit().getRangedWeapon().getRange() * MainActivity.getTileSize())){

                            projectile.remove();
                            if(Game.debug) System.out.println("Projectile removed due to out of range!");

                        }
                    }
                }

                for(Unit unit: player.getUnits()) {
                    if(unit != projectile.getUnit() && Rect.intersects(unit.getHitbox(), projectile.getHitbox())) {
                        if(player.isPlayersTurn()) {
                            if (friendlyFire) {
                                unit.takeDamage(projectile.getUnit().getRangedWeapon().getDamage());
                                projectile.remove();
                                if (Game.debug) System.out.println("HIT Friendly!");

                            }
                        }

                        else{
                            if (Game.debug) System.out.println("HIT by Enemy!");
                            unit.takeDamage(projectile.getUnit().getRangedWeapon().getDamage());
                            projectile.remove();
                        }
                    }
                }

                for(Unit unit: enemy.getUnits()) {
                    if(unit != projectile.getUnit() && Rect.intersects(unit.getHitbox(), projectile.getHitbox())) {
                        unit.takeDamage(projectile.getUnit().getRangedWeapon().getDamage());
                        projectile.remove();

                        if (Game.debug) {
                            System.out.println("HIT Enemy");
                            System.out.println(unit.getHealth());
                        }
                    }
                }

            }

            if(go.isRemoved()) {
                objects.remove(go);
            }
        }

    }

    public void render(Canvas canvas, Paint paint) {
        for(int i = 0; i < width*height; i++) {
            tiles[i].render(canvas,paint);
        }

        for (GameObject go: objects) {
            paint.setColor(Color.argb(255, 255, 255, 255));
            go.render(canvas, paint);
        }

        if(this.player != null) {
            this.player.renderUnits(canvas, paint);
            this.enemy.renderUnits(canvas, paint);
        }
    }

    public Context getContext() {
        return context;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addGameObject(GameObject GO){
        GO.init(this);
        objects.add(GO);
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void endPlayersTurn() {
        if(this.player.isPlayersTurn()) {
            this.player.endTurn();
            this.enemy.startTurn();
            this.enemy.setNextActiveUnit();
        }else {
            this.enemy.endTurn();
            this.player.startTurn();
        }
    }

    public boolean containsProjectiles() {
        boolean contains = false;
        for(int i = 0; i < objects.size(); i++) {
            if(objects.get(i) instanceof Projectile) {
                contains = true;
                break;
            }
        }
        return contains;
    }
}
