package fsd.week1.todolist1;

import fsd.week1.todolist1.datamodel.generateDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    public Button startGenerate;
    public Button chartGender;
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

    public void initialize() {
        ethnicityOptions.setItems(FXCollections.observableArrayList(accessDB.loadEthnicities()));
        genderOptions.setItems(FXCollections.observableArrayList(accessDB.getGendersHM()));
        startGenerate.setDisable(true);
    }

    public void onChoiceClick(ActionEvent e){
        if(genderOptions.getValue() != null && !genderOptions.getValue().toString().isEmpty()
                || ethnicityOptions.getValue() != null && !ethnicityOptions.getValue().toString().isEmpty()){
            startGenerate.setDisable(false);
        }

    }

    public void onButtonClick(ActionEvent e){

        System.out.println("i got here");

        if(e.getSource().equals(startGenerate)){

            //if theyve selected both options
            if(genderOptions.getValue() != null && !genderOptions.getValue().toString().isEmpty() && ethnicityOptions.getValue() != null && !ethnicityOptions.getValue().toString().isEmpty() ){
                randomName = accessDB.getRandomNameBasedOfGenderAndEthnicity(genderOptions.getValue().toString(), ethnicityOptions.getValue().toString());
                setRandomName.setText(randomName);
            }

            if(genderOptions.getValue() != null && ethnicityOptions.getValue() == null){
                randomName = accessDB.getRandomNameBasedOf("gender", genderOptions.getValue().toString());
                setRandomName.setText(randomName);
            }

            if(genderOptions.getValue() == null && ethnicityOptions.getValue() != null){
                randomName = accessDB.getRandomNameBasedOf("ethnicity", ethnicityOptions.getValue().toString());
                setRandomName.setText(randomName);
            }


            //if they selected gender

            //if they selected ethncity
        }
    }


}