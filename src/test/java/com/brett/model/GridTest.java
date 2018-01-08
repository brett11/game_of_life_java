package com.brett.model;

import com.brett.model.Grid;
import static com.brett.model.GridExamples.*;
// junit book (Jeff Lang) pg 10
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;

public class GridTest {
    private Grid grid;
    private Grid blinker1Grid;

    @Before
    public void create() {
        grid = new Grid();

        blinker1Grid = new Grid(GridExamples.BLINKER_1_CELLS);
    }

    @Test
    public void initializesCellArray() {
        assertThat(grid.getCells(), equalTo(GridExamples.DEFAULT_GRID_CELLS));
    }

    @Test
    public void flipCellsTest() {
        assertThat(blinker1Grid.getCells(), equalTo(GridExamples.BLINKER_1_CELLS));
    }

    @Test
    public void findNeighborsTest () {
        int[][] expectedResult = {
                {4,4}, {4,5}, {4,6},
                {5,4},        {5,6},
                {6,4}, {6,5}, {6,6}

        };
        assertThat(grid.findNeighbors(5, 5), equalTo(expectedResult));
    }

    @Test
    public void findNeighborsTest2 () {
        int[][] expectedResult = {
                {9,9}, {9,0}, {9,1},
                {0,9},        {0,1},
                {1,9}, {1,0}, {1,1}

        };
        assertThat(grid.findNeighbors(0, 0), equalTo(expectedResult));
    }

    @Test
    public void aliveNeighborCountTest(){
        assertThat(grid.aliveNeighborCount(2,1), equalTo(0));
    }

    @Test
    public void aliveNeighborCountTest2(){
        assertThat(blinker1Grid.aliveNeighborCount(2,1), equalTo(3));
    }


    @Test
    public void aliveNeighborCountTest3(){
        assertThat(blinker1Grid.aliveNeighborCount(2,2), equalTo(2));
    }

    @Test
    public void shouldFlipTest(){
        // alive cell with 2 alive neighbors stays alive
        assertTrue(blinker1Grid.shouldFlip(2,1));
    }

    @Test
    public void shouldFlipTest2(){
        //dead cell with 0 live neighors stays dead
        assertFalse(blinker1Grid.shouldFlip(0,0));
    }

    @Test
    public void findCoorsToBeFlippedTest(){
        Integer[][] expectedResult = new Integer[][]{{1,2},{2,1},{2,3},{3,2}};
        assertThat(blinker1Grid.findCoordsToBeFlipped(), equalTo(expectedResult));
    }

    @Test
    public void generationTest(){
        blinker1Grid.generation();

        Grid blinker2Grid = new Grid(GridExamples.BLINKER_2_CELLS);
        assertThat(blinker1Grid.getCells(), equalTo(blinker2Grid.getCells()));

        blinker1Grid.generation();

        Grid blinker3Grid = new Grid(GridExamples.BLINKER_3_CELLS);
        assertThat(blinker1Grid.getCells(), equalTo(blinker3Grid.getCells()));
    }
}
