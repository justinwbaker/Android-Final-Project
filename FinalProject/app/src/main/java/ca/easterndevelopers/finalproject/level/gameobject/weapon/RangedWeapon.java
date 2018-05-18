package ca.easterndevelopers.finalproject.level.gameobject.weapon;

import ca.easterndevelopers.finalproject.level.gameobject.projectile.Projectile;

public abstract class RangedWeapon extends Weapon {

    protected int ammo;
    protected int ammoCapacity;

    protected Projectile projectile;

    public RangedWeapon() {

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
}
