package com.brett.controller;

import com.brett.model.*;

public class Controller {
    private Grid grid;

    public Controller(){
        initDefaultGrid();
    }

    public void initDefaultGrid(){
        grid = new Grid(GridExamples.GLIDER_1_CELLS_20x20);
    }

    public void setGrid(String s) {
        if (s == "Glider") {
            this.grid = new Grid(GridExamples.GLIDER_1_CELLS_20x20);
        } else if (s == "Blinker") {
            this.grid = new Grid(GridExamples.BLINKER_1_CELLS_9x9);
        } else if (s == "Spaceship") {
            this.grid = new Grid(GridExamples.SPACESHIP_1_CELLS_20x20);
        } else if (s == "Pentadecatholon") {
            this.grid = new Grid(GridExamples.PENTADECATHOLON_1_CELLS_20x20);
        } else if (s == "Pulsar") {
            this.grid = new Grid(GridExamples.PULSAR_1_CELLS_20x20);
        }
    }

    public Grid getGrid(){
        return grid;
    }

    public int getGenerationCount() {
        return grid.getGenerationCount();
    }
}
