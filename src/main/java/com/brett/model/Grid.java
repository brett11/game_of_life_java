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
            this.cells = createEmptyCells();
        } else {
            this.cells = cells;
        }
    }

    private boolean multiDimArrayEmpty(Integer[][] multiArray) {
        return (multiArray == null || multiArray.length == 0 || multiArray[0].length == 0);
    }

    //this is simpler and easier to read than the stream version
    public Integer[][] createEmptyCells() {
        Integer[][] zeroArray = new Integer[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                zeroArray[r][c] = 0;
            }
        }
        return zeroArray;
    }

    public void flipCell(Integer [] coord) {
        int origValue = getCellValue(coord);
        int newValue = flip(origValue);
        setCellValue(coord, newValue);
    }

    public void flipCells(Integer[][] positions) {
        if (multiDimArrayEmpty(positions)) {
            return;
        }
        for (Integer[] position : positions) {
            flipCell(position);
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

    public int getCellValue(Integer[] coord) {
        int row = coord[0];
        int col = coord[1];
        return cells[row][col];
    }

    public void setCellValue(Integer[] coord, Integer newValue) {
        int row = coord[0];
        int col = coord[1];
        cells[row][col] = newValue ;
    }

    public boolean isAlive(Integer[] coord) {
        return getCellValue(coord) == 1;
    }

    public Integer[][] findNeighbors(Integer[] coord) {
//        int neighborCount = GridHelpers.TRADITIONAL_NEIGHBOR_COORDINATES.length;
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

    public int aliveNeighborCount(Integer[] coord) {
        Integer[][] neighborCoords = findNeighbors(coord);

        int count = (int) Arrays.stream(neighborCoords)
                .filter(neighborCoord -> isAlive(neighborCoord))
                .count();
        return count;
    }

    public boolean shouldFlip(Integer[] coord) {
        if (isAlive(coord)) {
            switch (aliveNeighborCount(coord)) {
                case 0: case 1:
                    return true; //live cell with fewer than two neighbors dies (gets flipped)
                case 2: case 3:
                    return false; //live cell with 2 or 3 neighbors stays alive
                default:
                    return true; //overpopulation
            }
        } else {
            // dead cell with 3 neighbors becomes alive, otherwise stays dead
            switch (aliveNeighborCount(coord)) {
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
                        .filter(e -> shouldFlip(e))
                        .toArray(Integer[][]::new)
                        )
                .flatMap(Arrays::stream)
                .toArray(Integer[][]::new); ;
        return result;
    }

    // flipCells is not a pure function. as such, this is not implemented in a functional approach
    // functional approach would be too costly since there does not appear to be a good way to
    // update one cell in a multidimensional array without copying the array. to do this for each cell
    // while calling reduce to get the new cells array would be too costly
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
