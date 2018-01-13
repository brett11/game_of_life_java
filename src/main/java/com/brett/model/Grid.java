package com.brett.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Grid {
    private Integer[][] cells;

    private int rows;
    private int cols;

    private int generationCount = 0;

    // point size is 2 because point of cell is represeneted as a 2 element array { x , y}
    private static final int POINT_SIZE = 2;
    private static final int NUMBER_OF_ROWS = 10;
    private static final int NUMBER_OF_COLS = 10;

    public Grid() {
        this(NUMBER_OF_ROWS, NUMBER_OF_COLS, new Integer[][]{{}});
    }

    public Grid(Integer[][] cells) {
        this(cells.length, cells[0].length, cells);
    }

    public Grid(int rows, int cols, Integer[][] cells) {
        this.rows = rows;
        this.cols = cols;
        if (multiDimArrayEmpty(cells)) {
            this.cells = createEmptyCells(rows, cols);
        } else {
            this.cells = cells;
        }
    }

    private boolean multiDimArrayEmpty(Integer[][] multiArray) {
        return (multiArray == null || multiArray.length == 0 || multiArray[0].length == 0);
    }

    public Integer[][] createEmptyCells(int rows, int cols) {
        Integer[][] cells = IntStream.range(0, rows)
                .mapToObj(r -> IntStream.range(0, cols)
                        .map(e -> 0)
                        .boxed()
                        .toArray(Integer[]::new))
                .toArray(Integer[][]::new);
        return cells;
    }

    public void flipCell(int row, int col) {
        int origValue = cells[row][col];
        cells[row][col] = flip(origValue);
    }

    public void flipCells(Integer[][] positions) {
        if (multiDimArrayEmpty(positions)) {
            return;
        }
        for (Integer[] position : positions) {
            int row = position[0];
            int col = position[1];
            flipCell(row, col);
        }
    }

    private int flip(int x) {
        if (x == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public Integer[][] getCells() {
        return cells;
    }

    public int getCellValue(int row, int col) {
        return cells[row][col];
    }

    public boolean isAlive(int row, int col) {
        return getCellValue(row, col) == 1;
    }

    public Integer[][] findNeighbors(int row, int col) {
//        int neighborCount = GridHelpers.TRADITIONAL_NEIGHBOR_COORDINATES.length;
        Integer[] coord = {row, col};
        Integer[][] result =
                GridHelpers.TRADITIONAL_NEIGHBOR_COORDINATES.stream()
                        .map(otherCoord -> addCoords(coord, otherCoord))
                        .toArray(Integer[][]::new);
        return result;
    }

    private Integer[] addCoords(Integer[] pt1, Integer[] pt2) {
        // figured out formula using this example of a 3x3 grid. 0,0 as target cell
        //   2, 2   2, 0   2,1
        //   0, 2   0, 0   0,1
        //   1, 2   1, 0   1,1
        int pt1X = pt1[0];
        int pt1Y = pt1[1];
        int pt2X = pt2[0];
        int pt2Y = pt2[1];

        int intermediatePtX = pt1X + pt2X;
        int intermediatePtY = pt1Y + pt2Y;

        int newX;
        if (intermediatePtX >= 0) {
            newX = intermediatePtX % cols;
        } else {
            newX = cols + intermediatePtX;
        }

        int newY;
        if (intermediatePtY >= 0) {
            newY = intermediatePtY % rows;
        } else {
            newY = rows + intermediatePtY;
        }

        Integer[] result = {newX, newY};
        return result;
    }

    public int aliveNeighborCount(int row, int col) {
        Integer[][] neighborCoords = findNeighbors(row, col);

        int count = (int) Arrays.stream(neighborCoords)
                .filter(coords -> isAlive(coords[0], coords[1]))
                .count();
        return count;
    }

    public boolean shouldFlip(int row, int col) {
        if (isAlive(row, col)) {
            switch (aliveNeighborCount(row, col)) {
                case 0: case 1:
                    return true; //live cell with fewer than two neighbors dies (gets flipped)
                case 2: case 3:
                    return false; //live cell with 2 or 3 neighbors stays alive
                default:
                    return true; //overpopulation
            }
        } else {
            // dead cell with 3 neighbors becomes alive, otherwise stays dead
            switch (aliveNeighborCount(row, col)) {
                case 3:
                    return true;
                default:
                    return false;
            }
        }
    }

    public Integer[][] findCoordsToBeFlipped() {
        Integer[][] result = IntStream.range(0, rows)
                .mapToObj(r -> IntStream.range(0, cols)
                        .mapToObj(c -> new Integer[]{r, c})
                        .filter(e -> shouldFlip(e[0], e[1]))
                        .toArray(Integer[][]::new)
                        )
                .flatMap(Arrays::stream)
                .toArray(Integer[][]::new); ;
        return result;
    }

    public void generation() {
        flipCells(findCoordsToBeFlipped());
        generationCount++;
    }

    public int getGenerationCount() {
        return generationCount;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
