package controladores;

import clases.Maquillaje;
import clases.Pedido;
import clases.Producto;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class vistaVerProductosController {

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, String> columnaNombre;

    @FXML
    private TableColumn<Producto, Integer> columnaId;

    @FXML
    private TableColumn<Producto, Integer> columnaCantidad;

    @FXML
    private TableColumn<Producto, String> columnaTipo;

    @FXML
    private TableColumn<Producto, Producto> columnaEliminar;

    @FXML
    private TableColumn<Producto, String> columnaConsultor;


    public void initialize() {
        columnaConsultor.setCellValueFactory(new PropertyValueFactory<>("consultor"));
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columnaConsultor.setCellValueFactory(new PropertyValueFactory<>("consultorNombre"));

        columnaEliminar.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        columnaEliminar.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("X"); //puse la x poruqe si le ponia eliminar , no se veia en la tabla

            @Override
            //lo de abajo sirve para `poder eliminar un producto de la tabla
        protected void updateItem(Producto producto, boolean empty) {
            super.updateItem(producto, empty);

            if (producto == null) {
                setGraphic(null);
                return;
            }

            setGraphic(deleteButton);
            deleteButton.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmación de eliminación");
                alert.setHeaderText("Estás a punto de eliminar un producto");
                alert.setContentText("¿Estás seguro de que quieres eliminar el producto " + producto.getNombre() + "?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Pedido pedido = Pedido.getInstance();
                    pedido.eliminarProducto(producto);
                    tablaProductos.getItems().remove(producto);
                }
            });
}
        });

        Pedido pedido = Pedido.getInstance();
        ObservableList<Producto> productos = FXCollections.observableArrayList(pedido.getProductos());
        tablaProductos.setItems(productos);
    }

    public void regresar(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vistas/vistaProductosNatura.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}