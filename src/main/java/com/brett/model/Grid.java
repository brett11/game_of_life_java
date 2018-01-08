package com.brett.model;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.stream.Stream;

import static org.apache.commons.lang3.ArrayUtils.toArray;

public class Grid {
    private int[][] cells;

    private int rows;
    private int cols;
    // point size is 2 because point of cell is represeneted as a 2 element array { x , y}
    private static final int POINT_SIZE = 2;
    private static final int NUMBER_OF_ROWS = 10;
    private static final int NUMBER_OF_COLS = 10;

    public Grid() {
        this(NUMBER_OF_ROWS, NUMBER_OF_COLS, new int[][]{{}});
    }

    public Grid(int [][] cells){
        this(cells.length, cells[0].length, cells);
    }

    public Grid(int rows, int cols, int [][] cells) {
        this.rows = rows;
        this.cols = cols;
        if (multiDimArrayEmpty(cells)) {
            this.cells = createEmptyCells(rows, cols);
        } else {
            this.cells = cells;
        }
    }

    public int[][] createEmptyCells(int rows, int cols) {
        int[][] zeroArray = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                zeroArray[r][c] = 0;
            }
        }
        return zeroArray;
    }

    public void flipCell(int row, int col) {
        int origValue = cells[row][col];
        cells[row][col] = flip(origValue);
    }

    public void flipCells(int[][] positions) {
        if (multiDimArrayEmpty(positions)) {
            return;
        }
        for (int[] position : positions) {
            int row = position[0];
            int col = position[1];
            flipCell(row, col);
        }
    }

    private boolean multiDimArrayEmpty(int[][] multiArray) {
        return (multiArray == null || multiArray.length == 0 || multiArray[0].length == 0);
    }

    public int[][] getCells() {
        return cells;
    }

    public int getCellValue(int row, int col) {
        return cells[row][col];
    }

    public boolean isAlive(int row, int col) {
        return getCellValue(row, col) == 1;
    }


    private int flip(int x) {
        if (x == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public int[][] findNeighbors(int row, int col) {
        int neighborCount = GridHelpers.TRADITIONAL_NEIGHBOR_COORDINATES.length;
        int[][] result = new int[neighborCount][POINT_SIZE];
        int[] pt1 = {row, col};
        for (int i = 0; i < neighborCount; i++) {
            int[] pt2 = GridHelpers.TRADITIONAL_NEIGHBOR_COORDINATES[i];
            int[] neighborCoord = addCoords(pt1, pt2);
            result[i] = neighborCoord;
        }
        return result;

    }

    private int[] addCoords(int[] pt1, int[] pt2) {
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

        int[] result = {newX, newY};
        return result;
    }

    public int aliveNeighborCount(int row, int col) {
        int[][] neighbors = findNeighbors(row, col);
        int count = 0;
        for (int[] neighborCoords : neighbors) {
            int x = neighborCoords[0];
            int y = neighborCoords[1];
            if (isAlive(x, y)) {
                count++;
            }
        }
        return count;
    }

    public boolean shouldFlip(int row, int col) {
        if (isAlive(row, col)) {
            switch (aliveNeighborCount(row, col)) {
                case 0:
                case 1: //live cell with fewer than two neighbors dies (gets flipped)
                    return true;
                case 2:
                case 3:
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
        List<Integer[]> result = new ArrayList<>();
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++) {
                if (shouldFlip(r, c)) {
                    result.add(new Integer[]{r , c});
                }
            }
        }
        //convert List<Integer[]> to primitive
        //https://stackoverflow.com/questions/26726366/convert-nested-list-to-2d-array
        Integer[][] resultAry = result.stream()
                .map(intAry -> intAry)
                .toArray(Integer[][]::new);

        return resultAry;
    }

    public void generation(){
        flipCells(findCoordsToBeFlipped());
    }
}
