package de.tahigames.demondefense.engine.core.rendering;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Mirco on 01.05.2015.
 */
public class TextRenderComponent extends RenderComponent {

    private BitmapFont font;
    private String text;

    public TextRenderComponent(String text, Realm realm, Layer layer) {
        super(realm, layer);
        this.text = text;
        font = new BitmapFont();
        font.setScale(0.4f);
    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        BitmapFont.TextBounds bounds = font.getBounds(text);
        float width = bounds.width;
        float height = bounds.height;
        float x = getParent().getTransformedX() - width / 2f;
        float y = getParent().getTransformedY() + height / 2f;
        font.draw(batch, text, x, y);
    }
}
