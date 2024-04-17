package controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class vistaPrincipalController {

    @FXML
    void btnAgregar(ActionEvent event) {
        try {
            Parent formulario = FXMLLoader.load(getClass().getResource("/vistas/vistaFormulario.fxml"));

            Scene sceneFormulario = new Scene(formulario);

            Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActual.setScene(sceneFormulario);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
void btnBuscar(ActionEvent event) {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/vistaBuscar.fxml"));
        Parent buscarViewRoot = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(buscarViewRoot, 600, 400));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    void btnEditar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/vistaEditar.fxml"));
            Parent editarViewRoot = fxmlLoader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(editarViewRoot, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
    }
}

    public void btnRegresar(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vistas/selectorMenu.fxml"));

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}