package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainScreenController {

    @FXML
    public AnchorPane templateComponent;
    @FXML
    public Button hotelMenuBTN;
    @FXML
    public Button clientMenuBTN;
    @FXML
    public Button bookingMenuBTN;

    private Button lastButtonPressed;

    @FXML
    public void loadClientMenu() {
       loadMenu("Clientes", clientMenuBTN);
    }

    public void loadBookingMenu() {
        loadMenu("Reservas", bookingMenuBTN);
    }

    public void loadHotelMenu() {
        loadMenu("Hoteles", hotelMenuBTN);
    }

    public void loadMenu(String buttonPressed, Button button){
        try {
            if (lastButtonPressed != null)
                lastButtonPressed.setStyle("-fx-background-color:  #ddd; -fx-background-radius: 30px;");

            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-component.fxml"));
            AnchorPane menu = loader.load();

            button.setStyle("-fx-background-color:  #f5a623; -fx-background-radius: 30px;");

            lastButtonPressed = button;

            //Obtiene el controlador del componente cargado y le pasa el String que identifica el botón pulsado
            MenuComponent controller = loader.getController();
            controller.initialize(buttonPressed);


            // Reemplazar el contenido actual del componenteMultiusos con el contenido del archivo FXML cargado
            templateComponent.getChildren().setAll(menu);
        } catch (IOException e) {
            e.printStackTrace(); // Manejar la excepción de carga de FXML
        }
    }
}
