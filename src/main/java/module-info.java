module fsd.week1.todolist1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens fsd.week1.todolist1 to javafx.fxml;
    exports fsd.week1.todolist1;
}