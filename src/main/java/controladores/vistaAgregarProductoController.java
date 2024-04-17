package controladores;

import clases.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class vistaAgregarProductoController {

    @FXML
    private ComboBox<String> tipoProducto;

    @FXML
    private TextField nombreProducto;

    @FXML
    private TextField idProducto;

    @FXML
    private TextField cantidadProducto;

    private Pedido pedido;

    @FXML
    private ComboBox<String> consultorProducto;

    public vistaAgregarProductoController() {
        this.pedido = Pedido.getInstance();
    }

    @FXML
    public void initialize() {

        for (Consultor consultor : Consultor.getConsultores()) {
            consultorProducto.getItems().add(consultor.getNombre());
        }

        idProducto.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                idProducto.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        cantidadProducto.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                cantidadProducto.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    //esto sirve para mostrar una alerta en caso de que no se hayan llenado todos los campos
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

@FXML
void agregarProducto(ActionEvent event) {
    String tipo = tipoProducto.getValue();
    String nombre = nombreProducto.getText();
    String idStr = idProducto.getText();
    String cantidadStr = cantidadProducto.getText();

    if (tipo == null || nombre.isEmpty() || idStr.isEmpty() || cantidadStr.isEmpty()) {
        showAlert("Campos vacíos", "Por favor, llena todos los campos", "Todos los campos deben estar llenos para continuar.");
        return;
    }

    int id = Integer.parseInt(idStr);
    int cantidad = Integer.parseInt(cantidadStr);

    // Verificar si el ID del producto ya existe
    for (Producto producto : this.pedido.getProductos()) {
        if (producto.getId() == id) {
            showAlert("ID ya existe", "Por favor, ingresa un ID diferente", "El ID ingresado ya existe en la base de datos.");
            return;
        }
    }

    Producto producto;
    switch (tipo) {
        case "Perfume":
            producto = new Perfume(id, tipo, nombre, cantidad);
            break;
        case "Maquillaje":
            producto = new Maquillaje(id, tipo, nombre, cantidad);
            break;
        case "Joyeria":
            producto = new Joyeria(id, tipo, nombre, cantidad);
            break;
        case "Perfumeria":
            producto = new Perfume(id, tipo, nombre, cantidad);
            break;
        default:
            producto = new Producto(id, tipo, nombre);
    }

    String consultorNombre = consultorProducto.getValue();
    if (consultorNombre == null) {
        showAlert("Consultor no seleccionado", "Por favor, selecciona un consultor", "Debes seleccionar un consultor para agregar un producto.");
        return;
    }

    Consultor consultor = Consultor.getConsultorPorNombre(consultorNombre);
    if (consultor == null) {
        showAlert("Consultor no encontrado", "Por favor, selecciona un consultor válido", "El consultor seleccionado no se encuentra en la base de datos.");
        return;
    }

    producto.setConsultor(consultor);

    this.pedido.agregarProducto(producto);

    showAlert("Producto agregado", "El producto se ha agregado con éxito", "Producto: " + producto.toString());

    System.out.println("Producto agregado: " + producto.toString());
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