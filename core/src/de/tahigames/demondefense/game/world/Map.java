package de.tahigames.demondefense.game.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.rendering.DrawComponent;
import de.tahigames.demondefense.engine.rendering.RenderComponent;
import de.tahigames.demondefense.engine.rendering.TiledMapRenderComponent;
import de.tahigames.demondefense.game.world.towers.Tower;

/**
 * Created by Marcel on 14.04.2015.
 */
public class Map extends Entity {

    public static final int cellSize = 16;

    private Cell[][] grid;
    private CellSelector selector;
    private Tower[][] towers;

    public Map(String mapName) {
        super(0, 0);
        TiledMap tiledMap = new TmxMapLoader().load("maps/" + mapName);
        generateGrid(tiledMap);
        addComponent(new TiledMapRenderComponent(tiledMap, RenderComponent.Realm.Game, RenderComponent.Layer.Nine));
        selector = new CellSelector();
    }

    private void generateGrid(TiledMap tiledMap){
        MapProperties props = tiledMap.getProperties();
        grid = new Cell[props.get("width", Integer.class)][props.get("height", Integer.class)];
        towers = new Tower[props.get("width", Integer.class)][props.get("height", Integer.class)];
        TiledMapTileLayer groundLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        TiledMapTileLayer objectLayer = (TiledMapTileLayer) tiledMap.getLayers().get(1);
        System.out.println(objectLayer.getCell(1,17).getTile().getProperties().get("blocked"));
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                int actualY = grid[0].length - 1 - y;
                boolean forConstruction = fetchProperty(groundLayer.getCell(x, actualY), "forConstruction", true);
                boolean blocked = fetchProperty(objectLayer.getCell(x, actualY), "blocked", false);
                Cell cell = new Cell(blocked, forConstruction);
                grid[x][y] = cell;
            }
        }
    }

    private Boolean fetchProperty(TiledMapTileLayer.Cell cell, String propertyName, boolean defaultValue){
        if(cell == null)
            return defaultValue;
        String propertyValue = cell.getTile().getProperties().get(propertyName, String.class);
        return propertyValue == null ? defaultValue : Boolean.parseBoolean(propertyValue);
    }

    private Cell getCellAt(float x, float y){
        return grid[(int) ((x + getWidth() / 2) / cellSize)][(int) ((y + getHeight() /2) / cellSize)];
    }

    private Tower getTowerAt(float x, float y) {
        return towers[(int) ((x + getWidth() / 2) / cellSize)][(int) ((y + getHeight() /2) / cellSize)];
    }

    public void select(float x, float y){
        if(inMapBounds(x, y)){
            Tower tower = getTowerAt(x, y);
            if(tower != null)
                ;
            else{
                Cell cell = getCellAt(x, y);

            }
        }
    }

    private boolean inMapBounds(float x, float y) {
        float halfMapWidth = getWidth() / 2;
        float halfMapHeight = getHeight() / 2;
        if(x < getX() - halfMapWidth || x >= getX() + halfMapWidth)
            return false;
        return y >= getY() - halfMapHeight && y < getY() + halfMapHeight;
    }

    public void placeTower(){
        //if(selectedCell != null){
        //   selectedCell.placeTower(new BaseTower());
        //    deselectCell();
        //}
    }

    public float getWidth(){
        return grid.length * cellSize;
    }

    public float getHeight(){
        return grid[0].length * cellSize;
    }

    private class CellSelector extends Entity {

        private RenderComponent renderComponent;

        private boolean enabled;

        public CellSelector() {
            super(0, 0);
            renderComponent = new DrawComponent(new Texture("cells/selector.png"), Cell.SIZE, Cell.SIZE, RenderComponent.Realm.Game, RenderComponent.Layer.Eight);
        }

        public void enable() {
            if(!enabled) {
                addComponent(renderComponent);
                enabled = true;
            }
        }

        public void disable() {
            removeComponent(renderComponent);
            enabled = false;
        }

        public boolean isEnabled() {
            return enabled;
        }
    }
}
