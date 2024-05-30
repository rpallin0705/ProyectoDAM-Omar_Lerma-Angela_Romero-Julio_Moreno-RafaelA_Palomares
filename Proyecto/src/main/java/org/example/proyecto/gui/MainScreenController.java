package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for the main screen of the application.
 */
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

    /**
     * Loads the client menu into the template component.
     */
    @FXML
    public void loadClientMenu() {
        loadComponentInTemplateComponent("client-list.fxml");
    }

    /**
     * Loads the booking menu into the template component.
     */
    @FXML
    public void loadBookingMenu() {
        loadComponentInTemplateComponent("booking-list.fxml");
    }

    /**
     * Loads the hotel menu into the template component.
     */
    @FXML
    public void loadHotelMenu() {
        loadComponentInTemplateComponent("hotel-list.fxml");
    }

    /**
     * Loads the apartment menu into the template component.
     */
    @FXML
    public void loadApartmentMenu() {
        loadComponentInTemplateComponent("apartment-list.fxml");
    }

    /**
     * Loads the user menu into the template component.
     */
    @FXML
    public void loadUserMenu() { loadComponentInTemplateComponent("user-list.fxml"); }

    /**
     * Initializes the controller, adding hover effects to buttons.
     */
    @FXML
    public void initialize(){
        List<Button> buttonsOnHover = new ArrayList<>();
        buttonsOnHover.add(clientMenuBTN); buttonsOnHover.add(hotelMenuBTN);
        buttonsOnHover.add(bookingMenuBTN); buttonsOnHover.add(apartmentMenuBTN);
        buttonsOnHover.add(userMenuButton); buttonsOnHover.add(logOutButton);

        GuiEffectsHelper.addHoverEffectOnButtons(buttonsOnHover);

    }

    /**
     * Loads a specified FXML component into the template component.
     * @param fxmlName The name of the FXML file to load.
     */
    private void loadComponentInTemplateComponent(String fxmlName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
            AnchorPane menu = loader.load();

            // Get the associated controller
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
