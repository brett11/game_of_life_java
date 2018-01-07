package com.brett.view;

import javax.swing.*;
import java.awt.*;

public class PaintPanel extends JPanel {
    private Dimension currentDimension;
    private static int NUMBER_OF_ROWS = 10;
    private static int NUMBER_OF_COLS = 10;

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
        for (int rowIndex = 1; rowIndex < NUMBER_OF_ROWS; rowIndex++) {
            int y = currentDimension.height/NUMBER_OF_ROWS * rowIndex;
            graphics.drawLine(0, y, currentDimension.width, y);
        }
    }

    private void drawColLines(Graphics graphics) {
        for (int colIndex = 1; colIndex < NUMBER_OF_COLS; colIndex++) {
            int x = currentDimension.width/NUMBER_OF_COLS * colIndex;
            graphics.drawLine(x, 0, x, currentDimension.height);
        }
    }
}
