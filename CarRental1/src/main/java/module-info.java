module com.example.carrental1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens com.example.carrental1 to javafx.fxml;
    exports com.example.carrental1;
}