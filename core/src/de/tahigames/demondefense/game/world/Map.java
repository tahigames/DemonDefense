package de.tahigames.demondefense.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.rendering.RenderComponent;
import de.tahigames.demondefense.engine.rendering.TiledMapRenderComponent;

/**
 * Created by Marcel on 14.04.2015.
 */
public class Map extends Entity {

    public static final int CELL_SIZE = 16;

    private PathFinder pathFinder;
    private int startX, startY;
    private int endX, endY;

    private Cell[][] grid;
    private Cell selectedCell;

    public Map(String mapName) {
        super(0, 0);
        TiledMap tiledMap = new TmxMapLoader().load("maps/" + mapName);
        generateGrid(tiledMap);
        addComponent(new TiledMapRenderComponent(tiledMap, RenderComponent.Realm.Game, RenderComponent.Layer.Nine));

        pathFinder = new PathFinder(this);
    }

    private void generateGrid(TiledMap tiledMap){
        MapProperties props = tiledMap.getProperties();
        grid = new Cell[props.get("width", Integer.class)][props.get("height", Integer.class)];

        TiledMapTileLayer groundLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        TiledMapTileLayer objectLayer = (TiledMapTileLayer) tiledMap.getLayers().get(1);
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                int actualY = grid[0].length - 1 - y;
                boolean forConstruction = fetchProperty(groundLayer.getCell(x, y), "forConstruction", true);
                boolean blocked = fetchProperty(objectLayer.getCell(x, y), "blocked", false);
                if(fetchProperty(objectLayer.getCell(x, y), "start", false)){
                    startX = x;
                    startY = y - 1;
                } else if(fetchProperty(objectLayer.getCell(x, y), "end", false)){
                    endX = x;
                    endY = y + 1;
                }
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

    public Cell getCell(int x, int y){
        return grid[x][y];
    }

    private Cell getCellAt(float x, float y){
        return grid[(int) ((x + getPixelWidth() / 2) / CELL_SIZE)][(int) ((y + getPixelHeight() /2) / CELL_SIZE)];
    }

    public void select(float x, float y){
        if(inMapBounds(x, y)){
            Cell cell = getCellAt(x, y);
            Gdx.app.log("Map", "Get cell at " + x + " " + y);
            if(cell.isSelectable()){
                cell.setBlockingPath(true);
                if(pathFinder.findPath(startX, startY, endX, endY)){
                    if(selectedCell != null){
                        selectedCell.deselect();
                    }
                    cell.select();
                    Gdx.app.log("Map", "Select cell at " + x + " " + y);
                    selectedCell = cell;
                }
                cell.setBlockingPath(false);
            }
        }
    }

    private boolean inMapBounds(float x, float y) {
        float halfMapWidth = getPixelWidth() / 2;
        float halfMapHeight = getPixelHeight() / 2;
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

    public int getWidth() {
        return grid.length;
    }

    public int getHeight() {
        return grid[0].length;
    }

    public float getPixelWidth(){
        return grid.length * CELL_SIZE;
    }

    public float getPixelHeight(){
        return grid[0].length * CELL_SIZE;
    }
}
