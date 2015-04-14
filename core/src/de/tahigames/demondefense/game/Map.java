package de.tahigames.demondefense.game;

import de.tahigames.demondefense.engine.Entity;

/**
 * Created by Marcel on 14.04.2015.
 */
public class Map extends Entity {
    public static final int cellSize = 16;
    private Cell[][] grid;

    public Map(int cellsX, int cellsY) {
        super(0, 0);
        generateGrid(cellsX, cellsY);
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
}
