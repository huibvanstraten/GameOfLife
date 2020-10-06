package com.HuibVanStraten;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

public class MainView extends VBox {
    private Canvas canvas;
    private GameOfLife gameOfLife;
    private Affine affine;

    private int drawMode = GameOfLife.ALIVE;

    public MainView() {

        this.canvas = new Canvas(1400, 700);
        this.canvas.setOnMousePressed(this::handleDraw);
        this.canvas.setOnMouseDragged(this::handleDraw);

        this.setOnKeyPressed(this::onKeyPressed);

        Toolbar toolbar = new Toolbar(this);

        this.getChildren().addAll(toolbar, this.canvas);

        this.affine = new Affine();
        this.affine.appendScale(400/10f, 400/10f);

        this.gameOfLife = new GameOfLife(16, 16);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.D) {
            this.drawMode = GameOfLife.ALIVE;
        } else if (keyEvent.getCode() == KeyCode.E) {
            this.drawMode = GameOfLife.DEAD;
        }
    }

    private void handleDraw(MouseEvent mouseEvent) {
        double mouseX = mouseEvent.getX();
        double mouseY = mouseEvent.getY();

        try {
            Point2D simCoord = this.affine.inverseTransform(mouseX, mouseY);

            int simX = (int) simCoord.getX();
            int simY = (int) simCoord.getY();

            this.gameOfLife.setState(simX, simY, drawMode);

            draw();

        } catch (NonInvertibleTransformException e) {
            e.printStackTrace();
        }
    }

    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0,0,400,400);

        g.setFill(Color.BLACK);
        for (int x = 0; x < this.gameOfLife.width; x++) {
            for (int y = 0; y < this.gameOfLife.height; y++) {
                if(this.gameOfLife.getState(x, y) == GameOfLife.ALIVE) {
                    g.fillRect(x, y, 1, 1);
                }
            }
        }
        g.setStroke(Color.GRAY);
        g.setLineWidth(0.05f);
        for (int x = 0; x <=this.gameOfLife.width; x++) {
            g.strokeLine(x, 0, x, 16);
        }
        for (int y = 0; y <= this.gameOfLife.height; y++) {
            g.strokeLine(0, y, 16, y);
        }
    }

    public GameOfLife getGameOfLife() {
        return this.gameOfLife;
    }

    public void setDrawMode(int newDrawMode) {
        this.drawMode = newDrawMode;
    }
}


