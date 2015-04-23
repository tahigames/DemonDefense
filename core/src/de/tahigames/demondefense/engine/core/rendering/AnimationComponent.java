package de.tahigames.demondefense.engine.core.rendering;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Mirco on 14.04.2015.
 */
public class AnimationComponent extends RenderComponent {

    private float width, height;

    private Animation animation;
    private float stateTime;

    public AnimationComponent(TextureAtlas atlas, float frameTime, Animation.PlayMode playMode, Realm realm, Layer layer){
        this(atlas, frameTime, playMode, atlas.getWidth(), atlas.getHeight(), realm, layer);
    }

    public AnimationComponent(TextureAtlas atlas, float frameTime, Animation.PlayMode playMode, float width, float height, Realm realm, Layer layer){
        super(realm, layer);
        initAnimation(atlas, frameTime, playMode);

        this.width = width;
        this.height = height;
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
        float x = getParent().getTransformedX() - width / 2f;
        float y = getParent().getTransformedY() - height / 2f;
        batch.draw(currentFrame, x, y, width, height);
    }

}
