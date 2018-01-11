package com.brett.view;

import com.brett.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class MainFrame extends JFrame implements ComponentListener {
    private Toolbar toolbar;
    private FormPanel formPanel;
    private PaintPanel paintPanel;
    private Controller controller;
    private Timer timer;

    public MainFrame(String title){
        super(title);

        setLayout(new BorderLayout());

        toolbar = new Toolbar();
        formPanel = new FormPanel();
        paintPanel = new PaintPanel();

        controller = new Controller();
        timer = new Timer(50, paintPanel);

        paintPanel.setData(controller.getGrid());

        //add subcomponents
        add(toolbar, BorderLayout.NORTH);
        add(formPanel, BorderLayout.WEST);
        add(paintPanel, BorderLayout.CENTER);

        //add componenet listner to paintPanel
        //https://docs.oracle.com/javase/tutorial/uiswing/events/componentlistener.html
        paintPanel.addComponentListener(this);

        // set correct board before running
        formPanel.setFormListener(new FormListener() {
            public void formEventOccured(String s) {
                controller.setGrid(s);
                paintPanel.setData(controller.getGrid());
                formPanel.setData(controller.getGenerationCount());

                //width adjusted by 1 to force resize event so that columns and rows snap
                Dimension currentSize = paintPanel.getSize();
                currentSize.width += 1;
                paintPanel.setSize(currentSize);

                paintPanel.repaint();
            }
        });

        paintPanel.setPaintPanelListener(new PaintPanelListener() {
            @Override
            public void paintPanelRefreshed(int generationCount) {
                formPanel.setData(controller.getGenerationCount());
            }
        });

        toolbar.setToolbarListener(new ToolbarListener(){

            @Override
            public void runEventOccured() {
                timer.start();
                formPanel.disableOKBtn();
            }

            @Override
            public void pauseEventOccured() {
                timer.stop();
                formPanel.enableOKBtn();
            }
        });

        //standard
        setMinimumSize(new Dimension(500, 400));
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    @Override
    public void componentResized(ComponentEvent e) {
        paintPanel.setCurrentDimension(e.getComponent().getSize());
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
