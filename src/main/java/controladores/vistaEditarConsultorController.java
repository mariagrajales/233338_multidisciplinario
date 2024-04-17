package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import clases.Consultor;

public class vistaEditarConsultorController {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidoPaterno;
    @FXML
    private TextField txtApellidoMaterno;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnGuardar;

    private Consultor consultor;

    // Método para asignar el consultor a editar
    public void setConsultor(Consultor consultor) {
        this.consultor = consultor;
        txtId.setText(consultor.getId());
        txtNombre.setText(consultor.getNombre());
        txtApellidoPaterno.setText(consultor.getApellidoPaterno());
        txtApellidoMaterno.setText(consultor.getApellidoMaterno());
        txtTelefono.setText(consultor.getTelefono());
        txtDireccion.setText(consultor.getDireccion());
        txtEmail.setText(consultor.getEmail());
    }

@FXML
// Método para guardar los cambios en el consultor
public void initialize() {
    txtId.setEditable(false); // esto es para que no se pueda editar el id, es un dato que no se debe de tocar :v



    btnGuardar.setOnAction(event -> {
        consultor.setId(txtId.getText());
        consultor.setNombre(txtNombre.getText());
        consultor.setApellidoPaterno(txtApellidoPaterno.getText());
        consultor.setApellidoMaterno(txtApellidoMaterno.getText());
        consultor.setTelefono(txtTelefono.getText());
        consultor.setDireccion(txtDireccion.getText());
        consultor.setEmail(txtEmail.getText());

        Consultor.actualizarConsultor(consultor);  // Actualiza el consultor en la lista de consultores

        Stage stage = (Stage) btnGuardar.getScene().getWindow();
        stage.close();
    });
}


}

