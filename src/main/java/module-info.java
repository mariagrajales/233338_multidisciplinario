module com.example.natura_proyect {
    requires javafx.controls;
    requires javafx.fxml;

    opens clases to javafx.base;
    opens controladores to javafx.fxml;

    exports com.example.natura_proyect;
}