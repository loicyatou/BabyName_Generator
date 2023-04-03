package fsd.week1.todolist1;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    public Button startGenerate;
    public Button chartGender;

    @FXML
    public GridPane mainGridPane;
    String randomName;
    @FXML
    public ChoiceBox ethnicityOptions;
    @FXML
    public ChoiceBox genderOptions;
    public Text setRandomName;
    @FXML
    generateDetails accessDB = new generateDetails();

    @FXML
    generateDetails ethnicitiesHM;
    @FXML
    ChartsController chartsController = new ChartsController();

    public void initialize() {
        ethnicityOptions.setItems(FXCollections.observableArrayList(accessDB.loadEthnicities()));
        genderOptions.setItems(FXCollections.observableArrayList(accessDB.getGendersHM()));
        startGenerate.setDisable(true);
    }

    public void onChoiceClick(ActionEvent e) {
        if (genderOptions.getValue() != null && !genderOptions.getValue().toString().isEmpty()
                || ethnicityOptions.getValue() != null && !ethnicityOptions.getValue().toString().isEmpty()) {
            startGenerate.setDisable(false);
        }

    }

    public void onButtonClick(ActionEvent e) {

        System.out.println("i got here");

        if (e.getSource().equals(startGenerate)) {

            //if theyve selected both options
            if (genderOptions.getValue() != null && !genderOptions.getValue().toString().isEmpty() && ethnicityOptions.getValue() != null && !ethnicityOptions.getValue().toString().isEmpty()) {
                randomName = accessDB.getRandomNameBasedOfGenderAndEthnicity(genderOptions.getValue().toString(), ethnicityOptions.getValue().toString());
                setRandomName.setText(randomName);
            }

            if (genderOptions.getValue() != null && ethnicityOptions.getValue() == null) {
                randomName = accessDB.getRandomNameBasedOf("gender", genderOptions.getValue().toString());
                setRandomName.setText(randomName);
            }

            if (genderOptions.getValue() == null && ethnicityOptions.getValue() != null) {
                randomName = accessDB.getRandomNameBasedOf("ethnicity", ethnicityOptions.getValue().toString());
                setRandomName.setText(randomName);
            }

            //if they selected gender

            //if they selected ethncity
        }
    }

    @FXML
    public void showChartDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainGridPane.getScene().getWindow()); //Method to display an exta window on top of the mian window when the file button is clicked on the gui window.
        dialog.setTitle("The most popular names by category");
        dialog.setHeaderText("In 2016 the most popular male names were");
        FXMLLoader fxmlLoader = new FXMLLoader(); //loads a new fxml file in the popup window.
        fxmlLoader.setLocation(getClass().getResource("chartsPane.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK); //two buttons. the result of clicking each button is below.
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) { //if there is a value present an optional instance and that result is an ok button then do the following
            System.out.println("Ok button pressed");
        } else {
            System.out.println("Cancel pressed");
        }
    }

    @FXML
    public void showChartDialogFemale() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainGridPane.getScene().getWindow()); //Method to display an exta window on top of the mian window when the file button is clicked on the gui window.
        dialog.setTitle("The most popular names by category");
        dialog.setHeaderText("In 2016 the most popular female names were");
        FXMLLoader fxmlLoader = new FXMLLoader(); //loads a new fxml file in the popup window.
        fxmlLoader.setLocation(getClass().getResource("chartsPaneFemale.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK); //two buttons. the reshowChartDialogFemalesult of clicking each button is below.
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) { //if there is a value present an optional instance and that result is an ok button then do the following
            System.out.println("Ok button pressed");
        } else {
            System.out.println("Cancel pressed");
        }
    }
}