package de.tahigames.demondefense.game;

import com.badlogic.gdx.graphics.Texture;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.rendering.RenderComponent;

/**
 * Created by Marcel on 14.04.2015.
 */
public class Map extends Entity {
    public static final int cellSize = 16;
    private Cell[][] grid;
    private Cell selectedCell;
    private RenderComponent selectRenderer;

    public Map(int cellsX, int cellsY) {
        super(0, 0);
        generateGrid(cellsX, cellsY);
        selectRenderer = new RenderComponent(new Texture("testbox16selected.png"));
    }

    private void generateGrid(int cellsX, int cellsY){
        grid = new Cell[cellsX][cellsY];
        for (int x = 0; x < cellsX; x++) {
            for (int y = 0; y < cellsY; y++) {
                Cell cell = new Cell(x * cellSize, y * cellSize);
                grid[x][y] = cell;
                addChild(cell);
            }
        }
    }

    public Cell getCellAt(int x, int y){
        return grid[x / cellSize][y / cellSize];
    }

    public void selectCellAt(int x, int y){
        if(x >= getX() && x < getX() + cellSize * grid.length
                && y >= getY() && y < getY() + cellSize * grid.length){
            if(selectedCell != null)
                selectedCell.removeComponent(selectRenderer);
            selectedCell = getCellAt(x, y);
            selectedCell.addComponent(selectRenderer);
        }

    }

    public void deselectCell(){
        if(selectedCell != null)
            selectedCell.removeComponent(selectRenderer);
        selectedCell = null;
    }

}
