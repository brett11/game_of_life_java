package com.brett.controller;

import com.brett.model.Grid;

public class Controller {
    private Grid grid;

    public void setGrid(String s) {
        if (s == "Glider") {
            System.out.println("Grid should be Glider");
        } else if (s == "Blinker") {
            System.out.println("Grid should be Blinker");
        }

    }

    public Integer[][] getCells(){
        return grid.getCells();
    }
}
