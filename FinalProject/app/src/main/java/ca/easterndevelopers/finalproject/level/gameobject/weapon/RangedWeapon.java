package ca.easterndevelopers.finalproject.level.gameobject.weapon;

import android.graphics.Point;

import ca.easterndevelopers.finalproject.level.gameobject.Unit;
import ca.easterndevelopers.finalproject.level.gameobject.projectile.Projectile;

public abstract class RangedWeapon extends Weapon {

    protected int ammo;
    protected int ammoCapacity;

    protected Projectile projectile;
    protected Unit unit;

    protected int color;
    protected Point size;

    public RangedWeapon(Unit unit, int color, Point size) {
        this.unit = unit;
        this.color = color;
        this.size = size;
    }

    public void updateUnit(Unit unit) {
        this.unit = unit;
        this.projectile.setPosition(unit.getPosition());
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getAmmoCapacity() {
        return ammoCapacity;
    }

    public void setAmmoCapacity(int ammoCapacity) {
        this.ammoCapacity = ammoCapacity;
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public int getColor() {
        return color;
    }

    public Point getSize() {
        return size;
    }
}
