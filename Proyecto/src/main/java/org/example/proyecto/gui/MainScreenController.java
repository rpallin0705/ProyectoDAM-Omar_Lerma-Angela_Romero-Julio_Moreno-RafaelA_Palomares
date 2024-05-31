package org.example.proyecto.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.proyecto.model.user.UserDTO;

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
    @FXML
    public Label userNameLabel;

    private UserDTO logedUser = null;

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
            if (controller instanceof BookingListController bookingListController) {
                bookingListController.setTemplateComponent(templateComponent);
            }else  if (controller instanceof ApartmentListController apartmentListController && logedUser.isAdmin()){
                apartmentListController.updateApartmentButton.setVisible(true);
                apartmentListController.deleteApartmentButton.setVisible(true);
                apartmentListController.insertApartmentButton.setVisible(true);
            }else if (controller instanceof HotelListController hotelListController && logedUser.isAdmin()){
                hotelListController.updateHotelButton.setVisible(true);
                hotelListController.deleteHotelButton.setVisible(true);
                hotelListController.insertHotelButton.setVisible(true);
            }

            templateComponent.getChildren().setAll(menu);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

    public void setUserLogedIn(UserDTO userLogedIn) {
        this.logedUser = userLogedIn;
        userNameLabel.setText(userLogedIn.getNombre_apellidos());
        if (userLogedIn.isAdmin())
            userMenuButton.setVisible(true);
    }

    public void logOut() {
        // Establecer el usuario como nulo
        logedUser = null;
        // Limpiar el texto del nombre de usuario
        userNameLabel.setText("");

        // Opcional: Ocultar el botón de menú de usuario
        userMenuButton.setVisible(false);

        // Obtener la ventana actual
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        // Cerrar la ventana actual
        stage.close();

        // Abrir la vista de inicio de sesión
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));

            Scene scene = new Scene(loader.load(), 420,360);

            // Obtener la ventana de la nueva vista de inicio de sesión
            Stage loginStage = new Stage();
            loginStage.setScene(scene);
            loginStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
