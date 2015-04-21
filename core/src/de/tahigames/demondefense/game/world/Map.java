package de.tahigames.demondefense.game.world;

import com.badlogic.gdx.Gdx;
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

    public static final int CELL_SIZE = 16;

    private Cell[][] grid;
    private Cell selectedCell;

    public Map(String mapName) {
        super(0, 0);
        TiledMap tiledMap = new TmxMapLoader().load("maps/" + mapName);
        generateGrid(tiledMap);
        addComponent(new TiledMapRenderComponent(tiledMap, RenderComponent.Realm.Game, RenderComponent.Layer.Nine));
    }

    private void generateGrid(TiledMap tiledMap){
        MapProperties props = tiledMap.getProperties();
        grid = new Cell[props.get("width", Integer.class)][props.get("height", Integer.class)];

        TiledMapTileLayer groundLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        TiledMapTileLayer objectLayer = (TiledMapTileLayer) tiledMap.getLayers().get(1);
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                int actualY = grid[0].length - 1 - y;
                boolean forConstruction = fetchProperty(groundLayer.getCell(x, actualY), "forConstruction", true);
                boolean blocked = fetchProperty(objectLayer.getCell(x, actualY), "blocked", false);
                float cellX = (x - grid.length / 2f) * CELL_SIZE + (CELL_SIZE / 2);
                float cellY = (y - grid[0].length / 2f) * CELL_SIZE + (CELL_SIZE / 2);
                Cell cell = new Cell(cellX, cellY, blocked, forConstruction);
                addChild(cell);
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
        return grid[(int) ((x + getWidth() / 2) / CELL_SIZE)][(int) ((y + getHeight() /2) / CELL_SIZE)];
    }

    public void select(float x, float y){
        if(inMapBounds(x, y)){
            Cell cell = getCellAt(x, y);
            if(cell.isForConstruction()){

                if(selectedCell != null){
                    selectedCell.deselect();
                }
                cell.select();
                selectedCell = cell;
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
        return grid.length * CELL_SIZE;
    }

    public float getHeight(){
        return grid[0].length * CELL_SIZE;
    }
}
