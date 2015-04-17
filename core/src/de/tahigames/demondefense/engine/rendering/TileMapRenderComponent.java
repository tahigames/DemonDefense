package de.tahigames.demondefense.engine.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Created by Mirco on 17.04.2015.
 */
public class TileMapRenderComponent extends RenderComponent {

    private OrthogonalTiledMapRenderer tiledMapRenderer;

    public TileMapRenderComponent(TiledMap map, Realm realm, Layer layer) {
        super(realm, layer);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        tiledMapRenderer.render();
    }
}
