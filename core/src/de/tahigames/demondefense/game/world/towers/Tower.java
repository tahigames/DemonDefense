package de.tahigames.demondefense.game.world.towers;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.physics.Circle;
import de.tahigames.demondefense.engine.physics.PhysicsComponent;
import de.tahigames.demondefense.engine.rendering.DrawComponent;
import de.tahigames.demondefense.engine.rendering.RenderComponent;
import de.tahigames.demondefense.game.world.enemies.Enemy;
import de.tahigames.demondefense.game.world.towers.projectiles.Projectile;

/**
 * Created by Marcel on 14.04.2015.
 */
public abstract class Tower extends Entity{

    public static final float SIZE = 16;

    private String name;
    private int level;
    private TowerAttributes[] levels;

    public Tower(String name, DrawComponent renderComponent) {
        super(0, 0);
        this.name = name;
        level = 0;
        levels = new TowerAttributes[3];
        generateLevels(levels);
        addComponent(renderComponent);
        PhysicsComponent physicsComponent = new PhysicsComponent(new Circle(getX(), getY(), levels[level].getRange())) {
            @Override
            public boolean canCollideWith(Entity e) {
                return e instanceof Enemy;
            }

            @Override
            public void onCollisionWith(Entity e) {
            }
        };
        addComponent(physicsComponent);
        addComponent(new TowerAI(physicsComponent, this));
    }

    protected abstract void generateLevels(TowerAttributes[] levels);

    public void upgrade(){
        level++;
    }

    public TowerAttributes getAttributes(int level){
        return levels[level];
    }

    public String getName(){
        return name;
    }

    public int getLevel(){
        return level;
    }

    public void shoot(Enemy target){
        addChild(new Projectile(getX(), getY(), target));
    }

}
