package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import org.example.proyecto.model.user.UserDB;
import org.example.proyecto.model.user.UserDTO;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField userEmail;
    @FXML
    private PasswordField userPasswd;
    @FXML
    private Button login;

    private UserDTO userLogedIn;

    @FXML
    public void loginUser(ActionEvent actionEvent) {
        try {
            UserDB userDB = new UserDB();
            userLogedIn = userDB.userLogin(userEmail.getText(), userPasswd.getText());

            if (userLogedIn != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("main-screen.fxml"));
                Parent root = loader.load();

                MainScreenController controller = loader.getController();
                controller.setUserLogedIn(userLogedIn);

                Scene scene = new Scene(root);

                Stage stage = (Stage) login.getScene().getWindow();

                stage.setScene(scene);
                stage.show();
            } else {
                showPopup("Los datos del usuario son incorrectos.");
            }
        } catch (SQLException | IOException e) {
            showPopup(e.getMessage());
        }
    }

    // Método para mostrar un popup al lado del botón de login
    private void showPopup(String message) {
        Tooltip tooltip = new Tooltip(message);
        tooltip.setAutoHide(true); // El tooltip se oculta automáticamente después de un tiempo
        tooltip.show(login, login.getScene().getWindow().getX() + login.getLayoutX(),
                login.getScene().getWindow().getY() + login.getLayoutY() + login.getHeight());
    }
}
