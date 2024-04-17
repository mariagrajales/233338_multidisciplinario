package controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class vistaProductosNaturaController {


    @FXML
    void agregarProducto(ActionEvent event) {
        try {
            Parent vistaAgregarProducto = FXMLLoader.load(getClass().getResource("/vistas/vistaAgregarProducto.fxml"));
            Scene vistaAgregarProductoScene = new Scene(vistaAgregarProducto);

            // Obtener el Stage desde el evento
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(vistaAgregarProductoScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
void btnRegresar(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/selectorMenu.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));

    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    void verProductos(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaVerProductos.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
