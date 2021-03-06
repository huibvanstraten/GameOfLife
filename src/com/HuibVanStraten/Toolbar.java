package com.HuibVanStraten;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar {

    private MainView mainView;

    public Toolbar(MainView mainView) {
        this.mainView = mainView;
        Button draw = new Button("Draw");
        draw.setOnAction(this::handleDraw);
        Button erase = new Button("Erase");
        erase.setOnAction(this::handleErase);
        Button step = new Button("Step");
        step.setOnAction(this::handleStart);

        this.getItems().addAll(draw,erase,step);
    }

    private void handleStart(ActionEvent actionEvent) {
        this.mainView.getGameOfLife().nextGeneration();
        this.mainView.draw();
    }

    private void handleErase(ActionEvent actionEvent) {
         this.mainView.setDrawMode(GameOfLife.DEAD);
    }

    private void handleDraw(ActionEvent actionEvent) {
        this.mainView.setDrawMode(GameOfLife.ALIVE);
    }
}
