package de.tahigames.demondefense.engine.rendering;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Mirco on 15.04.2015.
 */
public class TextureAtlas {

    private Texture sheet;
    private int rows;
    private int cols;

    public TextureAtlas(Texture sheet, int rows, int cols){
        this.sheet = sheet;
        this.rows = rows;
        this.cols = cols;
    }

    public Texture getSheet() {
        return sheet;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }
}
