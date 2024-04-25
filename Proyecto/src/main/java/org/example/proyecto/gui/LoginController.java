package org.example.proyecto.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML
    private Button login;

    //@todo logica para gestionar login
    @FXML
    private void login() {
        try {
            // Cargar el archivo FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = loader.<Parent>load();

            // Crear una nueva escena con el contenido cargado desde el archivo FXML
            Scene scene = new Scene(root);

            // Obtener la ventana actual
            Stage stage = (Stage) login.getScene().getWindow();

            // Cambiar la escena de la ventana actual a la nueva escena
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
