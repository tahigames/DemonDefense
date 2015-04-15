package de.tahigames.demondefense.engine.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import de.tahigames.demondefense.engine.Component;
import de.tahigames.demondefense.engine.Core;

/**
 * Created by Mirco on 14.04.2015.
 */
public class RenderComponent extends Component implements Comparable<RenderComponent> {

    public static  enum Layer {
        Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine;
    }

    private float width, height;
    private Layer layer;

    private Animation animation;
    private float stateTime;

    public RenderComponent(TextureAtlas atlas, float timeBetweenFrames, Layer layer){
        this(atlas, atlas.getSheet().getWidth() / atlas.getCols(), atlas.getSheet().getHeight() / atlas.getRows(), layer);
    }

    public RenderComponent(TextureAtlas atlas, float width, float height, Layer layer){
        initAnimation(atlas);

        this.width = width;
        this.height = height;
        this.layer = layer;
    }

    private void initAnimation(TextureAtlas atlas){
        final int FRAME_COLS = 1;
        final int FRAME_ROWS = 1;
        final float timeBetweenFrames = 0.025f;

        final int width = atlas.getSheet().getWidth();
        final int height = atlas.getSheet().getHeight();
        TextureRegion[][] tmp = TextureRegion.split(atlas.getSheet(), width/FRAME_COLS, height/FRAME_ROWS);
        TextureRegion[] frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        animation = new Animation(timeBetweenFrames, frames);
    }

    public void render(SpriteBatch batch, float delta){
        stateTime += delta;
        TextureRegion currentFrame = animation.getKeyFrame(stateTime);
        float x = getParent().getX();
        float y = getParent().getY();
        batch.draw(currentFrame, x, y, width, height);
    }

    @Override
    public void onAddToCore(Core core) {
        core.getRenderingEngine().addComponent(this);
    }

    @Override
    public void onRemoveFromCore(Core core) {
        core.getRenderingEngine().removeComponent(this);
    }

    @Override
    public int compareTo(RenderComponent o) {
        return layer.compareTo(o.layer);
    }
}
