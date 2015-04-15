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

    public RenderComponent(Texture texture, float width, float height, Layer layer){
        this(new TextureAtlas(texture, 1, 1), 0, Animation.PlayMode.NORMAL, width, height, layer);
    }

    public RenderComponent(TextureAtlas atlas, float frameTime,Animation.PlayMode playMode, Layer layer){
        this(atlas, frameTime, playMode, atlas.getWidth(), atlas.getHeight(), layer);
    }

    public RenderComponent(TextureAtlas atlas, float frameTime, Animation.PlayMode playMode, float width, float height, Layer layer){
        initAnimation(atlas, frameTime, playMode);

        this.width = width;
        this.height = height;
        this.layer = layer;
    }

    private void initAnimation(TextureAtlas atlas, float frameTime, Animation.PlayMode playMode){
        final int cols = atlas.getCols();
        final int rows = atlas.getRows();

        final int width = atlas.getSheet().getWidth();
        final int height = atlas.getSheet().getHeight();
        TextureRegion[][] tmp = TextureRegion.split(atlas.getSheet(), width/cols, height/rows);
        TextureRegion[] frames = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        animation = new Animation(frameTime, frames);
        animation.setPlayMode(playMode);
    }

    public void render(SpriteBatch batch, float delta){
        stateTime += delta;
        TextureRegion currentFrame = animation.getKeyFrame(stateTime);
        float x = getParent().getX() - width / 2f;
        float y = getParent().getY() - height / 2f;
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
        return -layer.compareTo(o.layer);
    }
}
