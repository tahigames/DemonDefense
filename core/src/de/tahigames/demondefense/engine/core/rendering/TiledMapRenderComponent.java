package de.tahigames.demondefense.engine.core.rendering;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import de.tahigames.demondefense.engine.core.Core;

/**
 * Created by Mirco on 18.04.2015.
 */
public class TiledMapRenderComponent extends RenderComponent {

    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    private final TiledMap map;
    private final int mapWidth;
    private final int mapHeight;

    public TiledMapRenderComponent(TiledMap map, Realm realm, Layer layer) {
        super(realm, layer);
        this.map = map;

        MapProperties props = map.getProperties();
        mapWidth = props.get("width", Integer.class) * props.get("tilewidth", Integer.class);
        mapHeight = props.get("height", Integer.class) * props.get("tileheight", Integer.class);
    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        camera.translate(mapWidth / 2, mapHeight / 2);
        camera.update();
        batch.end();
        mapRenderer.setView(camera);
        mapRenderer.render();
        batch.begin();
        camera.translate(-mapWidth / 2, -mapHeight / 2);
        camera.update();
        mapRenderer.setView(camera);
    }

    @Override
    public void onAddToCore(Core core) {
        super.onAddToCore(core);
        this.camera = core.getRenderingEngine().getGameCamera();
        mapRenderer = new OrthogonalTiledMapRenderer(map, core.getRenderingEngine().getSpriteBatch());
    }
}
