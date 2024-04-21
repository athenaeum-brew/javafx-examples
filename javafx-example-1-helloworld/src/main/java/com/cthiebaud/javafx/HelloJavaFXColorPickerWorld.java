package com.cthiebaud.javafx;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class HelloJavaFXColorPickerWorld extends Application {

    private Stage primaryStage;

    // Method to show a color picker dialog
    private void showColorPickerDialog() {
        // Retrieve the background color of the main window
        String backgroundColor = primaryStage.getScene().getRoot().getStyle().replace("-fx-background-color:", "")
                .trim();
        // Parse the background color string into a Color object
        Color initialColor = parseColor(backgroundColor);

        // Create a ColorPicker with the initial color
        ColorPicker colorPicker = new ColorPicker(initialColor);

        // Create a new stage for the color picker dialog
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Choose a Color");
        dialogStage.setScene(new Scene(colorPicker, 300, 200));

        // Handle color selection
        colorPicker.setOnAction(event -> {
            Color selectedColor = colorPicker.getValue();
            setMainWindowBackground(selectedColor);
            dialogStage.close();
        });

        // Display the color picker dialog
        dialogStage.showAndWait();
    }

    // Method to parse a color string into a Color object
    private Color parseColor(String colorString) {
        try {
            return Color.web(colorString);
        } catch (IllegalArgumentException e) {
            // If the color string is invalid, return a default color (white)
            return Color.WHITE;
        }
    }

    // Method to set the background color of the main window
    private void setMainWindowBackground(Color color) {
        primaryStage.getScene().getRoot().setStyle("-fx-background-color: #" + color.toString().substring(2));
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;

        // Set the title of the stage to the simple name of the class
        stage.setTitle(this.getClass().getSimpleName());

        // Create a label with a message containing JavaFX and Java versions
        Label label = new Label(String.format("Hello, JavaFX %s, running on Java %s.",
                System.getProperty("javafx.version"), // Retrieve JavaFX version
                System.getProperty("java.version") // Retrieve Java version
        ));

        // Create a button to pick a color
        Button button = new Button("Pick a color...");
        button.setOnAction(value -> showColorPickerDialog());

        // Increase font size of the label
        label.setFont(new Font(48)); // Set font size to 48

        // Create a VBox to contain the button and label
        VBox pane = new VBox(button, label);

        // Get the primary screen and its visual bounds
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // Create a scene with the VBox as its root, using the screen bounds
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
