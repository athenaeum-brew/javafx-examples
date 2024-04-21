package com.cthiebaud.javafx;

import static com.cthiebaud.mythos.model.Model.MODEL;

import com.cthiebaud.mythos.model.Model.Actor;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Callback;

public class StayOnTheScene extends Application {

    private SplitPane splitPane;
    private VBox listViewPane;
    private VBox imageViewPane;

    // private Map<String, String> data = new LinkedHashMap<>();
    Callback<Actor, Observable[]> extractor = p -> new Observable[] {
            new SimpleStringProperty(p, "name", p.getName()),
            new SimpleStringProperty(p, "description", p.getDescription())
    };
    private final ObservableList<Actor> actorList = FXCollections
            .observableArrayList(extractor);

    /*
     * private final ObservableList<Actor>
     * actorList = FXCollections
     * .observableArrayList(p -> new Observable[] { p.getName(), p.getDescription()
     * });
     */
    @Override
    public void start(Stage primaryStage) {

        // Set the title of the main window
        primaryStage.setTitle(this.getClass().getSimpleName());

        actorList.addAll(MODEL.actorList());

        // Initialize UI components
        ListView<Actor> listView = new ListView<>();
        listView.setItems(actorList);
        listView.setPrefWidth(800);
        listView.setCellFactory(param -> new ListCell<Actor>() {
            @Override
            protected void updateItem(Actor item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    Text nameText = new Text(item.getName());
                    nameText.setStyle("-fx-font-weight: bold;");
                    String description = item.getDescription().trim();
                    if (description.length() > 140) {
                        description = description.substring(0, 140) + "…";
                    }
                    Text descriptionText = new Text(" - " + description);

                    TextFlow textFlow = new TextFlow(nameText, descriptionText);
                    setGraphic(textFlow);
                }
            }
        });
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            if (newValue != null) {
                showImage(newValue.getName(), newValue.getDescription());
            }
        });

        ImageView imageView = new ImageView();
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        // Initialize panes
        listViewPane = new VBox(listView);
        imageViewPane = new VBox(imageView);

        // Create split pane and set orientation
        splitPane = new SplitPane();
        splitPane.getItems().addAll(listViewPane, imageViewPane);
        splitPane.setOrientation(Orientation.VERTICAL); // Default orientation

        // Radio button for toggling split orientation
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton verticalRadio = new RadioButton("Vertical");
        RadioButton horizontalRadio = new RadioButton("Horizontal");
        verticalRadio.setToggleGroup(toggleGroup);
        horizontalRadio.setToggleGroup(toggleGroup);
        verticalRadio.setSelected(true);
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == verticalRadio) {
                splitPane.setOrientation(Orientation.VERTICAL);
            } else if (newValue == horizontalRadio) {
                splitPane.setOrientation(Orientation.HORIZONTAL);
            }
        });

        // Layout for radio buttons
        HBox radioBox = new HBox(verticalRadio, horizontalRadio);

        // Layout for the entire scene
        VBox root = new VBox(radioBox, splitPane);

        // Set up the stage
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simple JavaFX App");
        primaryStage.show();
    }

    private void showImage(String name, String description) {
        // Here, we just use some placeholder images
        Image image = ImageReaderHttp.get(description);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        imageViewPane.getChildren().setAll(imageView);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
