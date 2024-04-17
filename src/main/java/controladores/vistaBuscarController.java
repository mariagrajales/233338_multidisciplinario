package controladores;

import clases.Consultor;
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

public class vistaBuscarController {

    @FXML
    private Button backButton;

    @FXML
    private TextArea resultsArea;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Consultor> resultsTable;

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
    void btnRegresar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/vistaPrincipal.fxml"));
            Parent mainViewRoot = fxmlLoader.load();

            // Obtener el escenario actual y establecer la nueva escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(mainViewRoot, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

@FXML
void buscarConsultor(ActionEvent event) {
    String searchTerm = searchField.getText().trim().toLowerCase();
    if (searchTerm.isEmpty()) {
        return;
    }

    ObservableList<Consultor> results = FXCollections.observableArrayList();
    for (Consultor consultor : Consultor.getConsultores()) {
        String fullName = (consultor.getNombre() + " " + consultor.getApellidoPaterno() + " " + consultor.getApellidoMaterno()).toLowerCase();
        if (fullName.contains(searchTerm) || consultor.getId().equals(searchTerm)) {
            results.add(consultor);
        } else {
            // essto sirve para buscar cada palabra del término de búsqueda en el nombre completo del consultor, o busca un aproximado
            String[] searchWords = searchTerm.split(" ");
            for (String word : searchWords) {
                if (fullName.contains(word)) {
                    results.add(consultor);
                    break;
                }
            }
        }
    }

    if (!results.isEmpty()) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        resultsTable.setItems(results);
    }
}

    }
