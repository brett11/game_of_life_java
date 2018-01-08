package com.brett.view;

import javax.swing.*;
import java.awt.*;

public class PaintPanel extends JPanel {
    private Integer[][] cells;
    private int rows = 10;
    private int cols = 10;
    private Dimension currentDimension;

    public PaintPanel() {
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }

    public void setCurrentDimension(Dimension dimension) {
        this.currentDimension = dimension;
        System.out.println(this.currentDimension);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);


        //Draw Text
        graphics.setColor(Color.RED);
        drawRowLines(graphics);
        drawColLines(graphics);
    }

    private void drawRowLines(Graphics graphics) {
        for (int rowIndex = 1; rowIndex < rows; rowIndex++) {
            int y = currentDimension.height/rows * rowIndex;
            graphics.drawLine(0, y, currentDimension.width, y);
        }
    }

    private void drawColLines(Graphics graphics) {
        for (int colIndex = 1; colIndex < cols; colIndex++) {
            int x = currentDimension.width/cols * colIndex;
            graphics.drawLine(x, 0, x, currentDimension.height);
        }
    }

    public void setCells(Integer[][] cells) {
        this.cells = cells;
    }
}
