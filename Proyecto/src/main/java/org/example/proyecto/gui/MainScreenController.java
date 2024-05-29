package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainScreenController {

    @FXML
    public AnchorPane templateComponent;
    @FXML
    public Button hotelMenuBTN;
    @FXML
    public Button clientMenuBTN;
    @FXML
    public Button bookingMenuBTN;
    @FXML
    public Button apartmentMenuBTN;
    @FXML
    public Button logOutButton;
    @FXML
    public Button userMenuButton;


    @FXML
    public void loadClientMenu() {
       loadComponentInTemplateComponent("client-list.fxml");
    }

    @FXML
    public void loadBookingMenu() {
        loadComponentInTemplateComponent("booking-list.fxml");
    }

    @FXML
    public void loadHotelMenu() {
        loadComponentInTemplateComponent("hotel-list.fxml");
    }

    @FXML
    public void loadApartmentMenu() {
        loadComponentInTemplateComponent("apartment-list.fxml");
    }
    @FXML
    public void loadUserMenu() { loadComponentInTemplateComponent("user-list.fxml"); }

    @FXML
    public void initialize(){
        List<Button> buttonsOnHover = new ArrayList<>();
        buttonsOnHover.add(clientMenuBTN); buttonsOnHover.add(hotelMenuBTN);
        buttonsOnHover.add(bookingMenuBTN); buttonsOnHover.add(apartmentMenuBTN);
        buttonsOnHover.add(userMenuButton); buttonsOnHover.add(logOutButton);

        GuiEffectsHelper.addHoverEffectOnButtons(buttonsOnHover);

    }

    private void loadComponentInTemplateComponent(String fxmlName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
            AnchorPane menu = loader.load();

            // Obtener el controlador asociado
            Object controller = loader.getController();
            if (controller instanceof BookingListController) {
                ((BookingListController) controller).setTemplateComponent(templateComponent);
            }

            templateComponent.getChildren().setAll(menu);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}
