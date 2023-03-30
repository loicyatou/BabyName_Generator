package fsd.week1.todolist1;

import fsd.week1.todolist1.datamodel.generateDetails;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @FXML
    static
    generateDetails test = new generateDetails();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-window-view.fxml")); //create and FXML loader class and passes the fxml file through it
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Baby Name Generator");
        stage.setScene(scene); //grabs the fxml file and loads it onto the window stage.
        stage.show(); //makes the scene visible
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
    }

    public static void main(String[] args) {
        launch();
    }
}