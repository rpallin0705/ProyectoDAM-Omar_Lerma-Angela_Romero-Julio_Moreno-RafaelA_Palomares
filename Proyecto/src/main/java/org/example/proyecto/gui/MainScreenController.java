package org.example.proyecto.gui;

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
    public void loadClientMenu() {
       loadMenu("Clientes", clientMenuBTN);
    }

    @FXML
    public void loadBookingMenu() {
        loadMenu("Reservas", bookingMenuBTN);
    }

    @FXML
    public void loadHotelMenu() {
        loadMenu("Hoteles", hotelMenuBTN);
    }

    @FXML
    public void loadApartmentMenu() {
        loadMenu("Apartamentos", apartmentMenuBTN);
    }


    /**
     * This function loads the buttonData menu component for CRUD operations in the tables of the database
     * @param buttonPressed String with the table name of the buttonData pressed
     * @param buttonData data of the buttonData the user pressed for future operations
     */
    @FXML
    public void loadMenu(String buttonPressed, Button buttonData){
        try {

            // Load FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-component.fxml"));
            AnchorPane menu = loader.load();


            //Gets controller component and send a string as parameter to identify pressed buttonData
            MenuComponentController controller = loader.getController();
            controller.initialize(buttonPressed);

            //Le pasa al menuComponent una referencia del controlador Main para que este pueda cambiar sus componentes
            controller.setTemplateComponent(templateComponent);

            templateComponent.getChildren().setAll(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize(){
        List<Button> buttonsOnHover = new ArrayList<>();
        buttonsOnHover.add(clientMenuBTN); buttonsOnHover.add(hotelMenuBTN);
        buttonsOnHover.add(bookingMenuBTN); buttonsOnHover.add(apartmentMenuBTN);

        GuiEffectsHelper.addHoverEffectOnButtons(buttonsOnHover);

    }

    public void loadBookingList() {



        //Gets controller component and send a string as parameter to identify pressed buttonData


    }



}
