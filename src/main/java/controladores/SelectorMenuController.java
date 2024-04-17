package controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectorMenuController {

    @FXML
    private Button btnGestionConsultores;

    @FXML
    private Button btnGestionProductosNatura;

    @FXML
void gestionConsultores(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/vistaPrincipal.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) btnGestionConsultores.getScene().getWindow();
        stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    void gestionProductosNatura(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/vistaProductosNatura.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) btnGestionConsultores.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}