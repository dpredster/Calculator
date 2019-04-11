package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Calculator.fxml"));
            Scene scene = new Scene(root, 500, 600);
            scene.getStylesheets().add(getClass().getResource("Calculator.css").toExternalForm());
            // Sets the stage icon
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("ico/calculator.png")));
            // Set the stage title
            primaryStage.setTitle("Calculator");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
