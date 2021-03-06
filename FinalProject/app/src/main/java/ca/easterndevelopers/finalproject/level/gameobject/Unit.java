package ca.easterndevelopers.finalproject.level.gameobject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import java.util.Random;
import ca.easterndevelopers.finalproject.GUI.GUI;
import ca.easterndevelopers.finalproject.MainActivity;
import ca.easterndevelopers.finalproject.MainScreen;
import ca.easterndevelopers.finalproject.game.Game;
import ca.easterndevelopers.finalproject.level.Level;
import ca.easterndevelopers.finalproject.level.gameobject.projectile.Projectile;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.RangedWeapon;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.Weapon;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.ranged.Shotgun;
import ca.easterndevelopers.finalproject.level.tile.Tile;
import ca.easterndevelopers.finalproject.renderer.GameRenderer;
import ca.easterndevelopers.finalproject.utils.Utils;

public abstract class Unit extends GameObject {

    protected float baseDamage;
    protected float movementRange;

    protected int timeCanMove;
    protected int timesHasMoved;
    private boolean hasAttackedRanged;
    protected int health;
    protected int totalHealth;
    protected float healthPercent;

    protected boolean isActiveUnit;
    protected boolean isOnLevel;
    int costToLevel;
    int unitLevel;

    private Bitmap image;
    protected Weapon melee;
    protected RangedWeapon ranged;
    protected RangedWeapon ranged2;
    private Rect hitbox;

    private boolean isEnemyUnit;


    public Unit(Point position, Point size, boolean isEnemyUnit) {
        super(position, size);
        this.baseDamage = 1;
        this.movementRange = 2;
        this.timesHasMoved = 0;
        this.timeCanMove = 1;
        this.isActiveUnit = false;
        this.costToLevel = 10;
        this.health = 7;
        this.totalHealth = health;
        this.healthPercent = health / totalHealth;
        this.hasAttackedRanged = false;
        this.unitLevel = 1;
        this.isEnemyUnit = isEnemyUnit;
        this.isOnLevel = false;
    }


    // Core Methods  ***************************************************
    public void update(double fps) {
        if(this.isDoneTurn()) this.isActiveUnit = false;
        if(!this.isEnemyUnit) {
            // player ai
            if (this.isActiveUnit && !GUI.isOnGUI && !this.level.containsProjectiles() ) {
                if (timesHasMoved < timeCanMove) {
                    if (GameRenderer.getWorldTouchedPoint() != null ) {
                        int x = GameRenderer.getWorldTouchedPoint().x;
                        int y = GameRenderer.getWorldTouchedPoint().y;

                        Rect tilePosition = new Rect(x, y, x + 1, y + 1);
                        Point center = new Point(this.getPosition().x + (int) MainActivity.getTileSize()/2, this.getPosition().y+ (int) MainActivity.getTileSize()/2);
                        if ((Math.abs(Utils.getDistance(Utils.toTiledPosition(center), Utils.toTiledPosition(new Point(x + (int) MainActivity.getTileSize()/2, y+ (int) MainActivity.getTileSize()/2)))) < movementRange)  && isValidPoint()) {
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
                } else if (ranged.getAmmo() != 0 && GameRenderer.getWorldTouchedPoint() != null && !hasAttackedRanged && timesHasMoved == timeCanMove) {
                    rangedAttack();
                }
                else if(ranged.getAmmo() == 0){
                    this.ranged.setAmmo(this.ranged.getAmmoCapacity());
                    this.hasAttackedRanged = true;
                    MainScreen.getPlayer().setNextActiveUnit();
                }
            }
        }else {
            //do enemy ai here

            //get closest player unit
            //move closer to it
            //shoot it

            //getting the closes player unit:
            if (this.isActiveUnit && !this.level.containsProjectiles()) {
                if (timesHasMoved < timeCanMove) {
                    int distance = Integer.MAX_VALUE;
                    Unit closest = null;
                    for (int i = 0; i < MainScreen.getPlayer().getUnits().size(); i++) {
                        Unit playersUnit = MainScreen.getPlayer().getUnits().get(i);
                        if (Utils.getDistance(this.getPosition(), playersUnit.getPosition()) < distance) {
                            distance = (int) Utils.getDistance(this.getPosition(), playersUnit.getPosition());
                            closest = playersUnit;
                        }
                    }
                    if(closest != null) {
                        Tile closestTile = null;
                        int closestTileDistance = Integer.MAX_VALUE;
                        for (int i = 0; i < this.getLevel().getHeight(); i++) {
                            for (int j = 0; j < this.getLevel().getWidth(); j++) {
                                Tile tile = this.getLevel().getTile(j, i);
                                if (Utils.getDistance(Utils.toTiledPosition(this.getPosition()), tile.getPosition()) < this.movementRange) {
                                    if (Utils.getDistance(Utils.toTiledPosition(closest.getPosition()), tile.getPosition()) < closestTileDistance) {
                                        closestTileDistance = (int) Utils.getDistance(Utils.toTiledPosition(closest.getPosition()), tile.getPosition());
                                        closestTile = tile;
                                    }
                                }
                            }
                        }
                        // Will perform some basic checks to put Unit Next to player Units instead of on top if possible. if all 4 sides of closest Unit is full it'll get stuck. Can remove enemy checks for valid position to put multiple Enemies on the same tile to prevent hanging I don't know any weird ass algorithms lol
                        int tempx = this.position.x;
                        int tempy = this.position.y;

                        if(Game.debug) {
                            for (Unit u : this.level.getEnemy().getUnits()) {
                                System.out.println(u.isDoneTurn());
                            }
                        }


                            System.out.println("I'm here in the do loop");

                        System.out.println(closestTile.getPosition().x);
                            if (!isValidPointAI((int) (closestTile.getPosition().x * MainActivity.getTileSize()),
                                    (int) (closestTile.getPosition().y * MainActivity.getTileSize()))) {

                                if (isValidPointAI((int) (closestTile.getPosition().x * MainActivity.getTileSize()) + (int) (MainActivity.getTileSize()), (int) (closestTile.getPosition().y * MainActivity.getTileSize()))) {
                                    this.position.x = (int) (closestTile.getPosition().x * MainActivity.getTileSize()) + (int) (MainActivity.getTileSize());
                                    this.position.y = (int) (closestTile.getPosition().y * MainActivity.getTileSize());
                                    System.out.println("first if ");


                                } else if (isValidPointAI((int) (closestTile.getPosition().x * MainActivity.getTileSize()) - (int) (MainActivity.getTileSize()), (int) (closestTile.getPosition().y * MainActivity.getTileSize()))) {
                                    this.position.x = (int) (closestTile.getPosition().x * MainActivity.getTileSize()) - (int) (MainActivity.getTileSize());
                                    this.position.y = (int) (closestTile.getPosition().y * MainActivity.getTileSize());
                                    System.out.println("first elseif");


                                } else if (isValidPointAI((int) (closestTile.getPosition().x * MainActivity.getTileSize()), (int) (closestTile.getPosition().y * MainActivity.getTileSize()) + (int) (MainActivity.getTileSize()))) {
                                    this.position.y = (int) (closestTile.getPosition().y * MainActivity.getTileSize()) + (int) (MainActivity.getTileSize());
                                    this.position.x = (int) (closestTile.getPosition().x * MainActivity.getTileSize());
                                    System.out.println("second elseif");


                                } else if (isValidPointAI((int) (closestTile.getPosition().x * MainActivity.getTileSize()), (int) (closestTile.getPosition().y * MainActivity.getTileSize()) - (int) (MainActivity.getTileSize()))) {
                                    this.position.y = (int) (closestTile.getPosition().y * MainActivity.getTileSize()) - (int) (MainActivity.getTileSize());
                                    this.position.x = (int) (closestTile.getPosition().x * MainActivity.getTileSize());
                                    System.out.println("last elseif");

                                }
                                else{
                                    System.out.println("nested else statement");
                                    this.position.x = tempx;
                                    this.position.y = tempy;

                                }
                            } else {
                                System.out.println("first else statement");
                                this.position.x = (int) (closestTile.getPosition().x * MainActivity.getTileSize());
                                this.position.y = (int) (closestTile.getPosition().y * MainActivity.getTileSize());
                            } // moves if it is a valid position



                        if (Game.debug)
                            System.out.println(this + " Moved to " + this.position.x + ":" + this.position.y);

                        for (Unit u : MainScreen.getPlayer().getUnits()) {
                            if ((Math.abs(Math.sqrt(Math.pow((this.getPosition().x - u.getPosition().x), 2) +
                                    Math.pow((this.getPosition().y - u.getPosition().y), 2))))
                                    <= MainActivity.getTileSize()) {

                                u.takeDamage(this.melee.getDamage());
                                u.update(0);

                            } // melee attack check and action upon move
                        }

                        this.timesHasMoved++;

                        if(closest != null && timesHasMoved >= timeCanMove && this.ranged.getAmmo() != 0) {
                            int targetX = closest.getPosition().x + (int)(MainActivity.getTileSize()/2);
                            int targetY = closest.getPosition().y + (int)(MainActivity.getTileSize()/2);

                            if(this.ranged.getAmmo() != 0) {
                                if ((Math.abs(Math.sqrt(Math.pow((this.getPosition().x - targetX), 2)
                                        + Math.pow((this.getPosition().y - targetY), 2))))
                                        <= (this.ranged.getRange() * MainActivity.getTileSize())) {

                                    rangedAttack(targetX, targetY);
                                    if (Game.debug) System.out.println("Ai fired a shot");
                                } else {
                                    hasAttackedRanged = true;
                                }
                            }
                        }
                        else if(closest != null && timesHasMoved >= timeCanMove){

                                this.ranged.setAmmo(this.ranged.getAmmoCapacity());
                                hasAttackedRanged = true;
                                this.getLevel().getEnemy().setNextActiveUnit();
                        }

                        else if(closest == null){
                            if(MainScreen.getPlayer().getUnits().size() <= 0) {
                                MainScreen.getPlayer().soldierWipe(); // end game call
                            }
                            else{
                                this.level.endPlayersTurn();
                            }
                        }
                        else{
                            this.level.endPlayersTurn();
                        }
                    }
                    else{
                        MainScreen.getPlayer().soldierWipe(); // end game call
                    }
                    System.out.println(this.getHitbox());
                }
                else {

                    this.endUnitTurn();
                    this.level.getEnemy().setNextActiveUnit();


                }

                this.level.getEnemy().setNextActiveUnit();
                this.endUnitTurn();
            }
        } // AI action turn end
    }

    public void render(Canvas canvas, Paint paint){
        if(this.isOnLevel) {
            if (this.isActiveUnit) {
                this.showAttackRange(canvas, paint); // need to change this to only check if we want to attack
            }
            if (this.isActiveUnit && timesHasMoved != timeCanMove) {
                this.showMovementRange(canvas, paint);
            }
            paint.setColor(Color.WHITE);
            canvas.drawBitmap(image, getPosition().x, getPosition().y, paint);

            paint.setColor(Color.BLACK);
            canvas.drawRect(getPosition().x + (getPixelSize() * 2),
                    getPosition().y - (getPixelSize() * 3),
                    getPosition().x + MainActivity.getTileSize() - (getPixelSize() * 2),
                    getPosition().y, paint);

            paint.setColor(Color.RED);
            canvas.drawRect(getPosition().x + (getPixelSize() * 3),
                    getPosition().y - (getPixelSize() * 2),
                    getPosition().x + MainActivity.getTileSize() - (getPixelSize() * 3),
                    getPosition().y - (getPixelSize() * 1), paint);


            paint.setColor(Color.GREEN);
            canvas.drawRect(getPosition().x + (getPixelSize() * 3),
                    getPosition().y - (getPixelSize() * 2),
                    ((getPosition().x + (getPixelSize() * 3)) + (healthPercent * ((MainActivity.getTileSize() - (getPixelSize() * 6))))),
                    getPosition().y - (getPixelSize() * 1), paint);

            if (Game.debug) {
                paint.setColor(Color.argb(100, 255, 20, 20));
                canvas.drawRect(this.getHitbox(), paint);

            }
        }
    }

    // Grapical related Methods  *****************************************************

    public void setBitmap(Bitmap image) {
        this.image = image;
        if(this.getSize().x != image.getWidth() || this.getSize().x != image.getWidth()){
            this.image = Utils.getResizedBitmap(image, getSize().x, getSize().y, true);
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


    // Action Methods  ****************************************************

        public void rangedAttack() {
        if(this.ranged instanceof Shotgun){
            System.out.println("SHOOT DAH SHOTGUN");
            System.out.println(Utils.getRandom());
            rangedAttackShotgun();
        }
        else {
            hasAttackedRanged = true;
            ranged.setAmmo(ranged.getAmmo() - 1);
            Projectile projectile = new Projectile(
                    GameRenderer.getWorldTouchedPoint().x,
                    GameRenderer.getWorldTouchedPoint().y,
                    this.getPosition().x + (int) MainActivity.getTileSize() / 2,
                    this.getPosition().y + (int) MainActivity.getTileSize() / 2,
                    this.ranged.getSize(), this.ranged.getColor(), this);
            this.getLevel().addGameObject(projectile);
            MainScreen.getPlayer().setNextActiveUnit();
        }
    }

    public void rangedAttackShotgun() {
        hasAttackedRanged = true;
        ranged.setAmmo(ranged.getAmmo() - 1);
        for(int i = 0; i < 5; i++) {
            Projectile projectile = new Projectile(
                    GameRenderer.getWorldTouchedPoint().x,
                    GameRenderer.getWorldTouchedPoint().y,
                    this.getPosition().x + (int) MainActivity.getTileSize() / 2,
                    this.getPosition().y + (int) MainActivity.getTileSize() / 2,
                    this.ranged.getSize(), this.ranged.getColor(), this, (Utils.getRadians(Utils.getRandom()) + Utils.getRadians(Utils.getRandom()) + Utils.getRadians(Utils.getRandom()) + Utils.getRadians(Utils.getRandom())));
            this.getLevel().addGameObject(projectile);
        }
        MainScreen.getPlayer().setNextActiveUnit();
    }

    public void rangedAttack(int targetX, int targetY){
        hasAttackedRanged = true;
        ranged.setAmmo(ranged.getAmmo() - 1);
        Projectile projectile = new Projectile(
                targetX + Utils.getRadians(Utils.getRandom()),
                targetY + Utils.getRadians(Utils.getRandom()),
                this.getPosition().x + (int) MainActivity.getTileSize() / 2,
                this.getPosition().y + (int) MainActivity.getTileSize() / 2,
                this.ranged.getSize(), this.ranged.getColor(), this);
        this.getLevel().addGameObject(projectile);
        getLevel().getEnemy().setNextActiveUnit();
    } // ai ranged attack

    public void takeDamage(int damage){

        this.health -= damage;
        if(health <= 0) {
            if(Game.debug) System.out.println("Unit has died");
            this.health = 0;
            this.remove();
        }
        this.healthPercent = (float)health / (float)totalHealth;
        if(Game.debug) System.out.println("Health Percent: " + this.healthPercent);
        if(Game.debug) System.out.println("Health: " + this.health);


    }

    @Override
    public void remove() {
        super.remove();
        if(!this.isEnemyUnit) {
            if(this instanceof Soldier) MainScreen.setNumSoldiers(MainScreen.getNumSoldiers()-1);
            if(this instanceof Tank) MainScreen.setNumTanks(MainScreen.getNumTanks()-1);
            if(this instanceof Sniper) MainScreen.setNumSnipers(MainScreen.getNumSnipers()-1);
        }else {
            if(this instanceof Soldier) MainScreen.addGold(50);
            if(this instanceof Tank) MainScreen.addGold(50);
            if(this instanceof Sniper) MainScreen.addGold(50);
        }
    }

    public void levelUp() {
        Random rand = new Random();
        int number = rand.nextInt(4) + 1;
        this.health += number;
        this.baseDamage++;
        this.movementRange += 1;

        if(unitLevel >= 4){
            this.timeCanMove += 1;

        }
        this.costToLevel = 10 * ((unitLevel + 10) * 5);
        this.totalHealth = health;
        this.healthPercent = health / totalHealth;
        this.unitLevel++;

    }

    public void switchWeapon(){

        if(this.ranged2 != null) {
            if (Game.debug) System.out.println("" + ranged);
            RangedWeapon rangedTemp = this.ranged;
            this.ranged = this.ranged2;
            this.ranged2 = rangedTemp;
            if (Game.debug) System.out.println("" + ranged);
        }
    }


    // Simple Getter/Setter Methods  **************************************************

    public boolean isActive() {
        return this.isActiveUnit;
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

    public float getHealthPercent(){
        return healthPercent;
    }

    public void resetUnit(){
        this.hasAttackedRanged = false;
        this.timesHasMoved = 0;
        this.level = null;
        this.isOnLevel = false;
        this.health = this.totalHealth;
        this.position = null;
    }

    public void endTurn() {
        this.hasAttackedRanged = false;
        this.timesHasMoved = 0;
    }

    public void endUnitTurn(){
        this.hasAttackedRanged = true;
        this.timesHasMoved = timeCanMove;
    }

    @Override
    public void init(Level level) {
        super.init(level);
        this.isOnLevel = true;
    }

    public boolean isOnLevel() {
        return isOnLevel;
    }

    public boolean isValidPoint() {
        boolean isValid = true;


        for (int i = 0; i < level.getObjects().size(); i++) {
            GameObject go = level.getObjects().get(i);

            if (go instanceof Collider) {
                if (GameRenderer.getWorldTouchedPoint().x > go.getHitbox().left
                        && GameRenderer.getWorldTouchedPoint().x <  go.getHitbox().right
                        && GameRenderer.getWorldTouchedPoint().y > go.getHitbox().top
                        && GameRenderer.getWorldTouchedPoint().y <  go.getHitbox().bottom) {
                    System.out.println("Is an occupied location, COLLIDER");
                    if(Game.debug) System.out.println("Is an occupied location");
                    isValid = false;
                }
            }
        }

        for (Unit go : this.getLevel().getEnemy().getUnits()) {
            if (GameRenderer.getWorldTouchedPoint().x > go.getHitbox().left
                    && GameRenderer.getWorldTouchedPoint().x <  go.getHitbox().right
                    && GameRenderer.getWorldTouchedPoint().y > go.getHitbox().top
                    && GameRenderer.getWorldTouchedPoint().y <  go.getHitbox().bottom) {
                System.out.println("Is an occupied location, ENEMY");
                if(Game.debug) System.out.println("Is an occupied location");
                isValid = false;
            }
        }

        for (Unit go : MainScreen.getPlayer().getUnits()){
            if (GameRenderer.getWorldTouchedPoint().x > go.getHitbox().left
                    && GameRenderer.getWorldTouchedPoint().x <  go.getHitbox().right
                    && GameRenderer.getWorldTouchedPoint().y > go.getHitbox().top
                    && GameRenderer.getWorldTouchedPoint().y <  go.getHitbox().bottom) {
                System.out.println("Is an occupied location, ALLY");
                if(Game.debug) System.out.println("Is an occupied location");
                isValid = false;
            }
        }


        return isValid;
    }

    public boolean isValidPointAI(int x, int y) {
        boolean isValid = true;


        for (int i = 0; i < level.getObjects().size(); i++) {
            GameObject go = level.getObjects().get(i);

            if (go instanceof Collider) {
                if (x >= go.getHitbox().left + MainActivity.getTileSize()/2
                        && x <=  go.getHitbox().right - MainActivity.getTileSize()/2
                        && y >= go.getHitbox().top + MainActivity.getTileSize()/2
                        && y <=  go.getHitbox().bottom - MainActivity.getTileSize()/2) {
                    if(Game.debug)   System.out.println("Is an occupied location, COLLIDER");

                    isValid = false;
                }
            }
        }

        for (Unit go : this.getLevel().getEnemy().getUnits()) {
            if (x >= go.getHitbox().left + MainActivity.getTileSize()/2
                    && x <=  go.getHitbox().right - MainActivity.getTileSize()/2
                    && y >= go.getHitbox().top + MainActivity.getTileSize()/2
                    && y <=  go.getHitbox().bottom - MainActivity.getTileSize()/2) {
                if(Game.debug) System.out.println("Is an occupied location, ALLY");

                isValid = false;
            }
        }

        for (Unit go : MainScreen.getPlayer().getUnits()){
            if (x >= go.getHitbox().left + MainActivity.getTileSize()/2
                    && x <=  go.getHitbox().right - MainActivity.getTileSize()/2
                    && y >= go.getHitbox().top + MainActivity.getTileSize()/2
                    && y <=  go.getHitbox().bottom - MainActivity.getTileSize()/2) {
                if(Game.debug) System.out.println("Is an occupied location, ENEMY");

                isValid = false;
            }
        }


        return isValid;
    }
}
