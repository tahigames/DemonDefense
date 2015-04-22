package de.tahigames.demondefense.game.world.enemies;

/**
 * Created by Marcel on 22.04.2015.
 */
public class EnemyFactory {

    public static enum MONSTER_TYPE{
        Slime,
        Demon,
        Bat
    };


    public static Enemy create(float x, float y, MONSTER_TYPE type){

        switch (type){
            case Slime: return new HellSlime(x, y);
            case Demon: break;
            case Bat: break;
            default: break;
        }

        return null;
    }

}
