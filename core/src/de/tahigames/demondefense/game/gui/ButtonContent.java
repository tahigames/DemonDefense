package de.tahigames.demondefense.game.gui;

import de.tahigames.demondefense.engine.core.Entity;

/**
 * Created by Marcel on 21.04.2015.
 */
public class ButtonContent extends Entity {

    private String text;
    //private Texture texture;  //fuer die muenze zb

    public ButtonContent(float x, float y, String text) {
        super(x, y);
        this.text = text;
    }


}
