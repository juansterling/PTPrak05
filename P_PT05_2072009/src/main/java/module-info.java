module com.example.p_pt04_2072009 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.p_pt05_2072009 to javafx.fxml;
    exports com.example.p_pt05_2072009;

    opens com.example.p_pt05_2072009.Dao to javafx.fxml;
    exports com.example.p_pt05_2072009.Dao;

    opens com.example.p_pt05_2072009.Model to javafx.fxml;
    exports com.example.p_pt05_2072009.Model;

    opens com.example.p_pt05_2072009.Util to javafx.fxml;
    exports com.example.p_pt05_2072009.Util;
}