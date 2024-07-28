module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires fontawesomefx;


    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
}