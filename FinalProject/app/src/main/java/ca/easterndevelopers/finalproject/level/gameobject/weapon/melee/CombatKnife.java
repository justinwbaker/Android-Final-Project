package ca.easterndevelopers.finalproject.level.gameobject.weapon.melee;

import ca.easterndevelopers.finalproject.level.gameobject.Unit;
import ca.easterndevelopers.finalproject.level.gameobject.weapon.Weapon;

public class CombatKnife extends Weapon{

    public CombatKnife(Unit unit) {
        this.damage = 5;
        this.speed = 10;
        this.range = 1;
    }
}
