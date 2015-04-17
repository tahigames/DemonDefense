package de.tahigames.demondefense.game.world;

import com.badlogic.gdx.graphics.Texture;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.rendering.DrawComponent;
import de.tahigames.demondefense.game.world.towers.BaseTower;

/**
 * Created by Marcel on 14.04.2015.
 */
public class Map extends Entity {

    public static final int cellSize = 16;
    private Cell[][] grid;
    private Cell selectedCell;
    private DrawComponent selectRenderer;

    public Map(int cellsX, int cellsY) {
        super(0, 0);
        validateCellCount(cellsX, cellsY);
        generateGrid(cellsX, cellsY);
        selectRenderer = new DrawComponent(new Texture("cells/testbox16selected.png"), Cell.SIZE, Cell.SIZE, DrawComponent.Layer.Eight);
    }

    private void generateGrid(int cellsX, int cellsY){
        grid = new Cell[cellsX][cellsY];
        for (int x = 0; x < cellsX; x++) {
            for (int y = 0; y < cellsY; y++) {
                Cell cell = new Cell((x - cellsX / 2) * cellSize + cellSize / 2f, (y - cellsY / 2) * cellSize + cellSize / 2f);
                grid[x][y] = cell;
                addChild(cell);
            }
        }
    }

    public Cell getCellAt(float x, float y){
        return grid[(int) ((x + getWidth() / 2) / cellSize)][(int) ((y + getHeight() /2) / cellSize)];
    }

    public void selectCellAt(float x, float y){
        float halfMapWidth = getWidth() / 2;
        float halfMapHeight = getHeight() / 2;
        if(x >= getX() - halfMapWidth && x < getX() + halfMapWidth
                && y >= getY() - halfMapHeight && y < getY() + halfMapHeight){
            if(selectedCell != null)
                selectedCell.removeComponent(selectRenderer);
            selectedCell = getCellAt(x, y);
            selectedCell.addComponent(selectRenderer);
        }
    }

    public void deselectCell(){
        selectedCell.removeComponent(selectRenderer);
        selectedCell = null;
    }

    public void placeTower(){
        if(selectedCell != null){
            selectedCell.placeTower(new BaseTower());
            deselectCell();
        }
    }

    public float getWidth(){
        return grid.length * cellSize;
    }

    public float getHeight(){
        return grid[0].length * cellSize;
    }

    private void validateCellCount(int cellsX, int cellsY){
        if(cellsX % 2 != 0)
            throw new IllegalArgumentException("The amount of horizontal cells has to be even!");
        if(cellsY % 2 != 0)
            throw new IllegalArgumentException("The amount of vertical cells has to be even!");
    }
}
