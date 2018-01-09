package com.brett.controller;

import com.brett.model.*;

public class Controller {
    private Grid grid;

    public Controller(){
        initDefaultGrid();
    }

    public void initDefaultGrid(){
        grid = new Grid(GridExamples.GLIDER_1_CELLS);
    }

    public void setGrid(String s) {
        if (s == "Glider") {
            this.grid = new Grid(GridExamples.GLIDER_1_CELLS);
//            System.out.println("Grid should be Glider");
        } else if (s == "Blinker") {
            this.grid = new Grid(GridExamples.BLINKER_1_CELLS);
//            System.out.println("Grid should be Blinker");
        }

    }

    public Grid getGrid(){
        return grid;
    }
}
