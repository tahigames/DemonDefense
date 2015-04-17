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
public class DrawComponent extends RenderComponent {

    private float width, height;

    private Animation animation;
    private float stateTime;

    public DrawComponent(Texture texture, float width, float height, Realm realm, Layer layer){
        this(new TextureAtlas(texture, 1, 1), 0, Animation.PlayMode.NORMAL, width, height, realm, layer);
    }

    public DrawComponent(TextureAtlas atlas, float frameTime, Animation.PlayMode playMode, Realm realm, Layer layer){
        this(atlas, frameTime, playMode, atlas.getWidth(), atlas.getHeight(), realm, layer);
    }

    public DrawComponent(TextureAtlas atlas, float frameTime, Animation.PlayMode playMode, float width, float height, Realm realm, Layer layer){
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
        float x = getParent().getX() - width / 2f;
        float y = getParent().getY() - height / 2f;
        batch.draw(currentFrame, x, y, width, height);
    }

}
