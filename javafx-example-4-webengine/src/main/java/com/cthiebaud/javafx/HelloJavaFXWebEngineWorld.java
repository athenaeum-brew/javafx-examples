package com.cthiebaud.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

/*
 *  https://riptutorial.com/javafx/example/19313/communication-between-java-app-and-javascript-in-the-web-page
 *  https://riptutorial.com/javafx
 */
public class HelloJavaFXWebEngineWorld extends Application {

    private JavaBridge javaBridge = new JavaBridge();
    private WebEngine webEngine;

    @Override
    public void start(Stage primaryStage) {
        // Set the title of the stage
        primaryStage.setTitle(HelloJavaFXWebEngineWorld.class.getSimpleName());

        // Create a WebView to display HTML content
        WebView webView = new WebView();
        webEngine = webView.getEngine();

        // Load HTML content from file using HTMLContentGenerator
        // String htmlContent =
        // ContentLoader.loadHTMLContentFromClasspath("/ui/content.html");
        String htmlContent = ContentLoader.loadHTMLContentFromFile("webroot/template.html");

        // Inject Java object into JavaScript context
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("javaBridge", javaBridge);
            }
        });

        webEngine.loadContent(htmlContent);

        // Create a Scene with the WebView
        Scene scene = new Scene(webView, 800, 600);

        // Set the scene to the stage and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Java class to bridge between Java and JavaScript
    public class JavaBridge {
        public void callJavaFunctionFromWebView(String message) throws InterruptedException {
            System.out.println("Message from JavaScript: " + message);
            int x = Integer.parseInt(message);

            // waitThenAnswer(x);

            Thread messageThread = new Thread(new MessageSender(x));
            messageThread.start();

            return;
        }
    }

    class MessageSender implements Runnable {
        int i;

        MessageSender(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println("Thread started");
            try {
                waitThenAnswer(i);
                System.out.println("Thread done!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void waitThenAnswer(int i) throws InterruptedException {
        Thread.sleep(2000); // Sleep for 2 seconds
        Platform.runLater(() -> {
            webEngine.executeScript("document.getElementById('answer').innerHTML = " + (i * i) + ";");
            webEngine.executeScript("document.getElementById('spinning').style.visibility = 'hidden';");
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
