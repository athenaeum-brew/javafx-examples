package com.cthiebaud.javafx;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * The main application class.
 */
public class HelloJavaFXWorld extends Application {

    /**
     * The entry point for the JavaFX application.
     *
     * @param stage The primary stage for this application, onto which the
     *              application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        // Retrieve system information
        String javaVersion = SystemInfo.javaVersion();
        String javafxVersion = SystemInfo.javafxVersion();

        // Create a label displaying JavaFX and Java version information
        Label label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        StackPane pane = new StackPane(label);

        // Retrieve primary screen information
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // Create a scene and set it to the stage
        Scene scene = new Scene(pane, bounds.getWidth(), bounds.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method, launching the JavaFX application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }

    class SystemInfo {

        public static String javaVersion() {
            return System.getProperty("java.version");
        }

        public static String javafxVersion() {
            return System.getProperty("javafx.version");
        }

    }
}
