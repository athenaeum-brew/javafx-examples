package com.cthiebaud.javafx;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class HelloJavaFXWorld extends Application {

    @Override
    public void start(Stage stage) {
        // Set the title of the stage to the simple name of the class
        stage.setTitle(this.getClass().getSimpleName());

        // Create a label with a message containing JavaFX and Java versions
        Label label = new Label(String.format("Hello, JavaFX %s, running on Java %s.",
                System.getProperty("javafx.version"), // Retrieve JavaFX version
                System.getProperty("java.version") // Retrieve Java version
        ));

        // Increase font size
        label.setFont(new Font(48)); // Set font size to 48

        // Create a StackPane and add the label to it
        StackPane pane = new StackPane(label);

        // Get the primary screen and its visual bounds
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // Create a scene with the StackPane as its root, using the screen bounds
        Scene scene = new Scene(pane, bounds.getWidth(), bounds.getHeight());

        // Set the scene to the stage and display the stage
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch();
    }
}
