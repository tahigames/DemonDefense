package de.tahigames.demondefense.game.world.enemies;

import com.badlogic.gdx.math.Vector2;

import java.util.Queue;

/**
 * Created by Marcel on 22.04.2015.
 */
public class EnemyFactory {

    public static enum MONSTER_TYPE{
        Slime,
        Demon,
        Bat
    };


    public static Enemy create(float x, float y, MONSTER_TYPE type, Queue<Vector2> path){

        switch (type){
            case Slime: return new HellSlime(x, y, path);
            case Demon: break;
            case Bat: break;
            default: break;
        }

        return null;
    }

}
