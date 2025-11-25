module com.teamproject.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.teamproject.project to javafx.fxml;
    exports com.teamproject.project;
}