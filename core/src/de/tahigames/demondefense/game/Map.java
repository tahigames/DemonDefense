package de.tahigames.demondefense.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.rendering.RenderComponent;
import de.tahigames.demondefense.engine.rendering.TextureAtlas;
import de.tahigames.demondefense.game.towers.BaseTower;

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
        selectRenderer = new RenderComponent(new Texture("testbox16selected.png"), Cell.SIZE, Cell.SIZE, RenderComponent.Layer.Eight);
    }

    private void generateGrid(int cellsX, int cellsY){
        grid = new Cell[cellsX][cellsY];
        for (int x = 0; x < cellsX; x++) {
            for (int y = 0; y < cellsY; y++) {
                Cell cell = new Cell(x * cellSize + cellSize / 2f, y * cellSize + cellSize / 2f);
                grid[x][y] = cell;
                addChild(cell);
            }
        }
    }

    public Cell getCellAt(float x, float y){
        return grid[(int) (x / cellSize)][(int) (y / cellSize)];
    }

    public void selectCellAt(float x, float y){
        float halfMapWidth = cellSize * grid.length;
        float halfMapHeight = cellSize * grid[0].length;
        if(x >= getX() - halfMapWidth && x < getX() + halfMapWidth
                && y >= getY() - halfMapHeight && y < getY() + halfMapHeight){
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

    public void placeTower(){
        selectedCell.placeTower(new BaseTower());

    }

}
