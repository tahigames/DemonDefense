package de.tahigames.demondefense.game.world.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;
import java.util.Queue;

import de.tahigames.demondefense.engine.ai.AIComponent;
import de.tahigames.demondefense.engine.physics.PhysicsComponent;

/**
 * Created by Mirco on 16.04.2015.
 */
public class EnemyAI extends AIComponent{

    private static final float MONSTER_SPEED = 10.0f;

    private Enemy enemy;
    private PhysicsComponent physicsComponent;
    private Queue<Vector2> path;

    private Vector2 target;

    public EnemyAI(Enemy enemy, PhysicsComponent physicsComponent){
        this.enemy = enemy;
        this.physicsComponent = physicsComponent;

        //TODO räudige queue richtig auslesen
        this.path = new LinkedList<>(enemy.getPath());

        target = path.remove();
        physicsComponent.getVelocity().set(calculateVelocity(target));
    }

    @Override
    public void think(float delta) {
        //goto 0 , 0
        Vector2 position = new Vector2(getParent().getX(), getParent().getY());
        if(position == target){
            target = path.remove();
            physicsComponent.getVelocity().set(calculateVelocity(target));
        }
    }

    private Vector2 calculateVelocity(Vector2 target){
        Vector2 move = new Vector2(0, 0);

        move.x = target.x - enemy.getX();
        move.y = target.y - enemy.getY();

        float length = (float) Math.sqrt(move.x * move.x + move.y * move.y);

        move.x = (move.x / length) * MONSTER_SPEED;
        move.y = (move.y / length) * MONSTER_SPEED;

        Gdx.app.log("enemyAI","move vector: " + move.toString());
        return move;
    }

}
