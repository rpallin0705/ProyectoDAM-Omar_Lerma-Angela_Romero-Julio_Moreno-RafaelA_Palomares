package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainScreenController {
    @FXML
    public Button buscarClientesBTN;
    @FXML
    public AnchorPane componenteMultiusos;

    @FXML
    public void menuClientes(ActionEvent actionEvent) {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search-client.fxml"));
            AnchorPane newPane = loader.load();
            buscarClientesBTN.setStyle("-fx-background-color:  #f5a623; -fx-background-radius: 30px;");

            // Reemplazar el contenido actual del componenteMultiusos con el contenido del archivo FXML cargado
            componenteMultiusos.getChildren().setAll(newPane);
        } catch (IOException e) {
            e.printStackTrace(); // Manejar la excepción de carga de FXML
        }
    }
}
