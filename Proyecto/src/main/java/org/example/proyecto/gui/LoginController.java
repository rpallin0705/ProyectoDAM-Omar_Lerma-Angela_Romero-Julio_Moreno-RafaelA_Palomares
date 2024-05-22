package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyecto.model.user.UserDB;
import org.example.proyecto.model.user.UserDTO;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField userName;
    @FXML
    private PasswordField userPasswd;
    @FXML
    private Button login;

    private UserDTO userLogedIn = null;

    @FXML
    public void loginUser(ActionEvent actionEvent) throws SQLException, IOException {
        UserDB userDB = new UserDB();
        userLogedIn = userDB.userLogin(userName.getText(), userPasswd.getText());

        if (userLogedIn != null || true) {
            // Cargar el archivo FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-screen.fxml"));
            Parent root = loader.<Parent>load();

            // Crear una nueva escena con el contenido cargado desde el archivo FXML
            Scene scene = new Scene(root);

            // Obtener la ventana actual
            Stage stage = (Stage) login.getScene().getWindow();

            // Cambiar la escena de la ventana actual a la nueva escena
            stage.setScene(scene);
            stage.show();
        } else {
            showAlert();
        }


    }

    //todo crear una clase externa para otras alertas
    @FXML
    public void showAlert(){
        Alert badLoginAlert = new Alert(Alert.AlertType.ERROR);
        badLoginAlert.setTitle("Usuario no encontrado");
        badLoginAlert.setHeaderText("Los datos del usuario son incorrectos");


        badLoginAlert.showAndWait();
    }
}
