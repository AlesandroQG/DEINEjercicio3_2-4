module com.alesandro.ejercicio3_24 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.alesandro.ejercicio3_24 to javafx.fxml;
    exports com.alesandro.ejercicio3_24;
    exports com.alesandro.ejercicio3_24.controller;
    opens com.alesandro.ejercicio3_24.controller to javafx.fxml;
}