package com.HuibVanStraten;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Display extends Application {

    @Override
    public void start(Stage stage) {
        MainView mainView = new MainView();
        Scene scene = new Scene(mainView, 642, 677);
        stage.setScene(scene);
        stage.show();

        mainView.draw();
    }

    public static void main(String[] args) {
        launch();
    }
}

