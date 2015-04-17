package de.tahigames.demondefense.game.world.towers;

/**
 * Created by Marcel on 15.04.2015.
 */
public class TowerAttributes {

    private int range;
    private int damage;
    private float attackSpeed;
    private int cost;

    public TowerAttributes(int range, int damage, float attackSpeed, int cost) {
        this.range = range;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.cost = cost;
    }

    public int getRange() {
        return range;
    }

    public int getDamage() {
        return damage;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public int getCost() {
        return cost;
    }

    public int getWorth() {
        return cost/2;
    }
}
