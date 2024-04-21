package com.cthiebaud.javafx;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class HelloJavaFXWorld extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle(this.getClass().getSimpleName());

        Label label = new Label(String.format("Hello, JavaFX %s, running on Java %s.",
                System.getProperty("java.version"),
                System.getProperty("javafx.version")));
        StackPane pane = new StackPane(label);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        Scene scene = new Scene(pane, bounds.getWidth(), bounds.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
