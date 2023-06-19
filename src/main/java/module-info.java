module com.example.carrental1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    requires java.sql;

    requires org.kordamp.ikonli.javafx;
    requires java.desktop;

    opens com.example.carrental1 to javafx.fxml;
    exports com.example.carrental1;
}