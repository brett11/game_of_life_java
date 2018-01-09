package com.brett.view;

import com.brett.model.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaintPanel extends JPanel implements ActionListener {
    private Grid grid;
    private int rows;
    private int cols;
    private Dimension currentDimension;
    private int rowSize;
    private int colSize;

    private PaintPanelListener paintPanelListener;

    public PaintPanel() {
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }

    public void setCurrentDimension(Dimension dimension) {
        this.currentDimension = dimension;
        this.rowSize = dimension.height / rows;
        this.colSize = dimension.width / cols;
        System.out.println(this.currentDimension);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);


        //Draw Text
        graphics.setColor(Color.RED);
        drawRowLines(graphics);
        drawColLines(graphics);
        drawBoxes(graphics);
    }

    private void drawRowLines(Graphics graphics) {
        for (int rowIndex = 1; rowIndex < rows; rowIndex++) {
            int y = rowSize * rowIndex;
            graphics.drawLine(0, y, currentDimension.width, y);
        }
    }

    private void drawColLines(Graphics graphics) {
        for (int colIndex = 1; colIndex < cols; colIndex++) {
            int x = colSize * colIndex;
            graphics.drawLine(x, 0, x, currentDimension.height);
        }
    }

    private void drawBoxes(Graphics graphics){
        for (int rowIndex = 0; rowIndex <rows; rowIndex++) {
            for (int colIndex = 0; colIndex <cols; colIndex++) {
                if (grid.isAlive(rowIndex, colIndex)) {
                    graphics.fillRect(colIndex * colSize, rowIndex * rowSize, colSize, rowSize);
                }
            }
        }
    }

    public void setData(Grid grid) {
        this.grid = grid;
        this.rows = grid.getRows();
        this.cols = grid.getCols();
    }

    public void setPaintPanelListener(PaintPanelListener paintPanelListener) {
        this.paintPanelListener = paintPanelListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        grid.generation();
        this.repaint();
        paintPanelListener.paintPanelRefreshed(grid.getGenerationCount());
    }
}
