package controladores;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import clases.Consultor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class vistaEditarController {

    @FXML
    private TableView<Consultor> tablaRegistro;
    @FXML
    private TableColumn<Consultor, String> colId;
    @FXML
    private TableColumn<Consultor, String> colNombre;
    @FXML
    private TableColumn<Consultor, String> colApellidoPaterno;
    @FXML
    private TableColumn<Consultor, String> colApellidoMaterno;
    @FXML
    private TableColumn<Consultor, String> colTelefono;
    @FXML
    private TableColumn<Consultor, String> colDireccion;
    @FXML
    private TableColumn<Consultor, String> colEmail;
    @FXML
    private Button btnRegresar;
    @FXML
    private TableColumn<Consultor, Consultor> colEliminar;


    @FXML
    public void initialize() { // esto inicializa todas los atributos para que se muestren en la tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        ArrayList<Consultor> listaConsultores = Consultor.getConsultores();
        ObservableList<Consultor> observableList = FXCollections.observableArrayList(listaConsultores);
        tablaRegistro.setItems(observableList);

        tablaRegistro.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                onEdit();
            }
        });

        btnRegresar.setOnAction(event -> {
            try {
                regresar(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        colEliminar.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        colEliminar.setCellFactory(param -> new TableCell<Consultor, Consultor>() {
            private final Button deleteButton = new Button("Eliminar");

            @Override
            protected void updateItem(Consultor consultor, boolean empty) {
                super.updateItem(consultor, empty);

                if (consultor == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmación de eliminación");
                    alert.setHeaderText("Estás a punto de eliminar un consultor");
                    alert.setContentText("¿Estás seguro de que quieres continuar?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        Consultor.eliminarConsultor(consultor);
                        recargarTabla();
                    }
                });
            }
        });
    }



    //esta funcion se encarga de abrir la ventana de edicion de consultort
private void onEdit() {
    if (tablaRegistro.getSelectionModel().getSelectedItem() != null) {
        Consultor selectedConsultor = tablaRegistro.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/vistaEditarConsultor.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            vistaEditarConsultorController controller = fxmlLoader.getController();
            controller.setConsultor(selectedConsultor);
            stage.showAndWait();
            recargarTabla();  // Recarga los datos de la tabla
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//esta funcion se encarga de recargar la tabla, para que se muestren las modificaciones
    public void recargarTabla() {
    ArrayList<Consultor> listaConsultores = Consultor.getConsultores();
    ObservableList<Consultor> observableList = FXCollections.observableArrayList(listaConsultores);
    tablaRegistro.setItems(observableList);
    tablaRegistro.refresh();  // Forzar un refresco de la tabla
}

    public void regresar(ActionEvent event) throws IOException {
        Parent vistaPrincipalParent = FXMLLoader.load(getClass().getResource("/vistas/vistaPrincipal.fxml"));
        Scene vistaPrincipalScene = new Scene(vistaPrincipalParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(vistaPrincipalScene);
        window.show();
    }
}