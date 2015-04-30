package de.tahigames.demondefense.game.world.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import de.tahigames.demondefense.engine.core.Core;
import de.tahigames.demondefense.engine.core.ai.AIComponent;
import de.tahigames.demondefense.engine.core.physics.PhysicsComponent;

/**
 * Created by Mirco on 16.04.2015.
 */
public class EnemyAI extends AIComponent{

    private static final float MONSTER_SPEED = 10.0f;

    private Enemy enemy;
    private PhysicsComponent physicsComponent;

    private List<Vector2> path;
    private int currentWayPoint;
    private Vector2 target;

    public EnemyAI(Enemy enemy, PhysicsComponent physicsComponent, List<Vector2> path){
        this.enemy = enemy;
        this.physicsComponent = physicsComponent;
        this.path = path;

        currentWayPoint = 0;
        target = path.get(currentWayPoint);
        physicsComponent.getVelocity().set(calculateVelocity(target));
    }

    @Override
    public void think(float delta) {
        if(enemy.getCurrentHealth() <= 0)
            getParent().getParent().removeChild(enemy);

        if(target.epsilonEquals(getParent().getTransformedX(), getParent().getTransformedY(), 2f)){
            if(++currentWayPoint < path.size()){
                target = path.get(currentWayPoint);
                physicsComponent.getVelocity().set(calculateVelocity(target));
            } else {
                getParent().getParent().removeChild(getParent());
            }
        }
    }

    private Vector2 calculateVelocity(Vector2 target){
        Vector2 move = new Vector2(0, 0);

        move.x = target.x - enemy.getX();
        move.y = target.y - enemy.getY();

        float length = (float) Math.sqrt(move.x * move.x + move.y * move.y);

        move.x = (move.x / length) * MONSTER_SPEED;
        move.y = (move.y / length) * MONSTER_SPEED;

//        Gdx.app.log("enemyAI","move vector: " + move.toString());
        return move;
    }

}
