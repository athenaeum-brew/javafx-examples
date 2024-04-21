package com.cthiebaud.javafx;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * The main application class.
 */
public class HelloJavaFXWebViewWorld extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Set the title of the stage
        primaryStage.setTitle(this.getClass().getSimpleName());

        // Create a WebView
        WebView webView = new WebView();

        webView.getEngine().getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.FAILED) {
                // Handle loading error
                webView.getEngine()
                        .loadContent("<pre style='color:red; font-size:48px; font-weight: bold;'>Failed to load page: "
                                + webView.getEngine().getLocation()
                                + "</pre>");
            }
        });

        webView.getEngine().load("https://google.com");

        // Retrieve primary screen information
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // Create a Scene with WebView as the root node
        Scene scene = new Scene(webView, bounds.getWidth(), bounds.getHeight());

        // Set the scene to the stage
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
