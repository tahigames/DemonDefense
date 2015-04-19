package de.tahigames.demondefense.engine.rendering;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import de.tahigames.demondefense.engine.Core;

/**
 * Created by Mirco on 18.04.2015.
 */
public class TiledMapRenderComponent extends RenderComponent {

    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    public TiledMapRenderComponent(TiledMap map, Realm realm, Layer layer) {
        super(realm, layer);
        mapRenderer = new OrthogonalTiledMapRenderer(map);
    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    @Override
    public void onAddToCore(Core core) {
        super.onAddToCore(core);
        this.camera = core.getRenderingEngine().getGameCamera();
    }
}
