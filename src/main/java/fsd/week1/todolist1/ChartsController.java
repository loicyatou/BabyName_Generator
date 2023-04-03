package fsd.week1.todolist1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChartsController{

    @FXML
    public PieChart pieChart;

    @FXML
    dataBaseConnection dataBaseConnection = new dataBaseConnection();
    private Statement statement = null;
    private ResultSet resultSet = null;

    @FXML
    private String genderOfButton;

    @FXML int yearOfButton;

    @FXML
    List<String> names = new ArrayList<>();
    List<Integer> number = new ArrayList<>();

    public void initialize() {
        getPercetangeValuesMale();
        setValues();
        showPercentageValues();
    }

    //This will be a pie chart that displats the distribution of the top 10 most popular names in a certain year or decade
    public void setValues(){
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();
        for (int i = 0; i < names.size(); i++) {
            pieChartData.add(new PieChart.Data(names.get(i), number.get(i)));
        }
        pieChart.setData(pieChartData);
    }

    //grabs the gender of the button to display relevant data

    @FXML
    public void setGenderOfButton(String genderOfButton) {
        this.genderOfButton = genderOfButton;
    }

    @FXML
    public void setYearOfButton(int yearOfButton) {
        this.yearOfButton = yearOfButton;
    }


    @FXML
    public ObservableList getPercetangeValuesMale() {

        try{
            //query through the database to find the lowest value so that you can create a new range of random values. it would have been easier with the basic random class but I wanted to coninue using the secure random method
            dataBaseConnection.establishConnection();

            //grab the lowest number and store it
            statement = dataBaseConnection.getConnection().createStatement();

            String query = "SELECT childsfirstname, count FROM babynames WHERE yearofbirth = 2016 AND gender = 'MALE' ORDER BY count DESC  LIMIT 10;";
            resultSet = statement.executeQuery(query);




            while(resultSet.next()){
                names.add(resultSet.getString("childsfirstname"));
                number.add(resultSet.getInt("count"));
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(resultSet != null){
                    resultSet.close();
                }

                if(statement != null){
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return null;
    }

    @FXML
    public void showPercentageValues(){
        pieChart.getData().forEach(data -> {
            String percentage = String.format("%.2f",(data.getPieValue()/100));
            Tooltip tooltip = new Tooltip(percentage + "%");
            Tooltip.install(data.getNode(), tooltip);
        });
    }

}


