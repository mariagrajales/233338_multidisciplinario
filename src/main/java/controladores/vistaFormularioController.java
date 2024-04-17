package controladores;

import clases.Consultor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class vistaFormularioController {

    @FXML
    private TextField apellidoMaterno;

    @FXML
    private TextField apellidoPaterno;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField telefonoField;

    @FXML
    private TextField direccionField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField id;

    @FXML
    private TextField nombre;

    @FXML
    private PasswordField passwordField;

    @FXML
    //esto sirve para que los campos de texto solo acepten letras y no números, para que no haya errores al ingresar datos
    // el initialize es un método que sirve para inicializar los componentes de la vista, osea que los datos que ingrese el usuario se guarden
    // tambien aqui va la mayoria de la lógica de la vista, tambien las restricciones de los campos de texto
    public void initialize() {
        id.textProperty().addListener((observable, oldValue, newValue) -> {
            // Solo aceptar números en el campo de ID
        if (!newValue.matches("\\d*")) {
            id.setText(newValue.replaceAll("[^\\d]", ""));
        }
            });


        nombre.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\D*")) {
                nombre.setText(newValue.replaceAll("[^\\D]", ""));
            }
        });

        apellidoMaterno.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\D*")) {
                apellidoMaterno.setText(newValue.replaceAll("[^\\D]", ""));
            }
        });

        apellidoPaterno.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\D*")) {
                apellidoPaterno.setText(newValue.replaceAll("[^\\D]", ""));
            }
        });

        telefonoField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                telefonoField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 12) { // Limitar a solo 12  la contraseña
                passwordField.setText(oldValue);
            }
        });

        confirmPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 12) { // Limitar a solo 12  la contraseña
                confirmPasswordField.setText(oldValue);
            }
        });

        id.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                id.setText(newValue.replaceAll("[^\\d]", ""));
            }
            if (newValue.length() > 8) { // Limitar a solo 8 caracteres el ID
                id.setText(oldValue);
            }
        });


        emailField.focusedProperty().addListener((observable, oldValue, newValue) -> {
    if (!newValue) { // esto no permitira que se avance si no ingresamos un correo vario
        String regex = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)*(\\.[a-zA-Z]{2,})$";
        if (!emailField.getText().matches(regex)) {
            showAlert("Formato de correo electrónico inválido", "Por favor, ingresa un correo electrónico válido", "El correo electrónico ingresado no tiene un formato válido.");
            emailField.requestFocus();
        }
    }
});
}
    // esto sirve para mostrar una alerta en caso de que haya campos vacíos
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

 @FXML
void btnAgregar(ActionEvent event) {
    String idConsultor = id.getText();
    String nombreConsultor = nombre.getText();
    String apellidoPaternoConsultor = apellidoPaterno.getText();
    String apellidoMaternoConsultor = apellidoMaterno.getText();
    String emailConsultor = emailField.getText();
    String passwordConsultor = passwordField.getText();
    String telefonoConsultor = telefonoField.getText();
    String direccionConsultor = direccionField.getText();

    if (idConsultor.isEmpty() || nombreConsultor.isEmpty() || apellidoPaternoConsultor.isEmpty() || apellidoMaternoConsultor.isEmpty() || emailConsultor.isEmpty() || passwordConsultor.isEmpty() || telefonoConsultor.isEmpty() || direccionConsultor.isEmpty()) {
        showAlert("Campos vacíos", "Por favor, llena todos los campos", "Todos los campos deben estar llenos para continuar.");
        return;
    }

    if (!passwordConsultor.equals(confirmPasswordField.getText())) {
        showAlert("Contraseñas no coinciden", "Por favor, verifica las contraseñas", "Las contraseñas ingresadas no coinciden.");
        return;
    }

    // Verificar si el ID ya existe
    for (Consultor consultor : Consultor.getConsultores()) {
        if (consultor.getId().equals(idConsultor)) {
            showAlert("ID ya existe", "Por favor, ingresa un ID diferente", "El ID ingresado ya existe en la base de datos.");
            return;
        }
        if (consultor.getTelefono().equals(telefonoConsultor)) {
            showAlert("Teléfono ya existe", "Por favor, ingresa un teléfono diferente", "El teléfono ingresado ya existe en la base de datos.");
            return;
        }
        if (consultor.getEmail().equals(emailConsultor)) {
            showAlert("Correo electrónico ya existe", "Por favor, ingresa un correo electrónico diferente", "El correo electrónico ingresado ya existe en la base de datos.");
            return;
        }
    }

    Consultor nuevoConsultor = new Consultor(idConsultor, nombreConsultor, apellidoPaternoConsultor, apellidoMaternoConsultor, telefonoConsultor, direccionConsultor, emailConsultor, passwordConsultor); // Pasar la dirección al constructor

    System.out.println(Consultor.getConsultores());

    showAlert("Consultor agregado", "El consultor se ha agregado con éxito", "Consultor: " + nuevoConsultor.toString());

    System.out.println("Consultor agregado");
}


    @FXML
    void btnRegresar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vistas/vistaPrincipal.fxml"));
            Parent mainViewRoot = fxmlLoader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(mainViewRoot, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}