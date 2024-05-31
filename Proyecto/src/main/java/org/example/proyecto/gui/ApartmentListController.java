package org.example.proyecto.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.proyecto.model.booking.BookingDataHelper;
import org.example.proyecto.model.client.ClientDTO;
import org.example.proyecto.model.touristApartment.TouristApartmentDTO;
import org.example.proyecto.model.touristApartment.TouristApartmentDB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for managing the tourist apartment list in the GUI.
 * Provides functionalities to register, update, delete, and search for apartments.
 * Handles selection of apartments for booking.
 */
public class ApartmentListController {
    public static final String NO_APARTMENT_SELECTED = "No hay ningún apartamento seleccionado.";
    @FXML
    public Label apartmentIdLabel;
    @FXML
    public TextField apartmentName;
    @FXML
    public TextField apartmentAddress;
    @FXML
    public TextField apartmentDistanceToDowntown;
    @FXML
    public TableView<TouristApartmentDTO> apartmentDataTable;
    @FXML
    public TableColumn<TouristApartmentDTO, Integer> apartmentIdColumn;
    @FXML
    public TableColumn<TouristApartmentDTO, String> apartmentNameColumn;
    @FXML
    public TableColumn<TouristApartmentDTO, String> apartmentAddressColumn;
    @FXML
    public TableColumn<TouristApartmentDTO, Float> apartmentDistanceColumn;
    @FXML
    public Button isSelectingHousingButton;
    @FXML
    public Button updateApartmentButton;
    @FXML
    public Button deleteApartmentButton;
    @FXML
    public Button insertApartmentButton;
    @FXML
    public Button searchApartmentButton;
    @FXML
    AnchorPane templateComponent = null;

    private List<TouristApartmentDTO> apartmentList = null;
    private TouristApartmentDTO selectedApartment = null;
    private BookingDataHelper dataForBooking = null;

    /**
     * Initializes the controller class. Sets up the table columns and loads the apartment data.
     * Adds a listener to handle selection changes in the apartment table.
     *
     * @throws SQLException if a database access error occurs.
     * @throws IOException if an input/output error occurs.
     */
    @FXML
    public void initialize() throws SQLException, IOException {
        try {
            searchApartmentButton.setGraphic(GuiEffectsHelper.getSearchIcon());

            apartmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("housingId"));
            apartmentNameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            apartmentAddressColumn.setCellValueFactory(new PropertyValueFactory<>("calle"));
            apartmentDistanceColumn.setCellValueFactory(new PropertyValueFactory<>("downtownDistance"));

            setApartmentList();
            apartmentDataTable.getItems().setAll(apartmentList);

            apartmentDataTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    selectApartmentDetails(newValue);
                }
            });

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Registers a new apartment with the data provided in the text fields.
     * Validates the input data and shows alerts in case of missing information.
     */
    @FXML
    public void registerApartment() {
        if (selectedApartment != null) {
            clearTextFields();
            return;
        }

        if (apartmentAddress.getText().isBlank() || apartmentName.getText().isBlank() || apartmentDistanceToDowntown.getText().isBlank()) {
            AlertHelper.showMissingDataAlert();
            return;
        }

        try {
            TouristApartmentDTO apartmentToRegister = new TouristApartmentDTO(apartmentName.getText(), apartmentAddress.getText(), Float.parseFloat(apartmentDistanceToDowntown.getText()));
            TouristApartmentDB apartmentDB = new TouristApartmentDB();
            apartmentDB.insertTouristApartment(apartmentToRegister);
            setApartmentList();
            apartmentDataTable.getItems().setAll(apartmentList);
            clearTextFields();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the selected apartment with the data provided in the text fields.
     * Validates the input data and shows alerts in case of missing information or no changes.
     */
    @FXML
    public void updateApartment() {
        if (selectedApartment == null) {
            AlertHelper.showNoObjectSelected(NO_APARTMENT_SELECTED);
            return;
        }

        if (apartmentAddress.getText().isBlank() || apartmentName.getText().isBlank() || apartmentDistanceToDowntown.getText().isBlank()) {
            AlertHelper.showMissingDataAlert();
            return;
        }

        TouristApartmentDTO updatedApartment = new TouristApartmentDTO(selectedApartment.getHousingId(), apartmentName.getText(), apartmentAddress.getText(), Float.parseFloat(apartmentDistanceToDowntown.getText()));

        if (updatedApartment.equals(selectedApartment)) {
            AlertHelper.showNoChangesAlert();
            return;
        }
        try {
            TouristApartmentDB apartmentDB = new TouristApartmentDB();
            apartmentDB.updateTourisApartment(updatedApartment);
            setApartmentList();
            apartmentDataTable.getItems().setAll(apartmentList);
            clearTextFields();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes the selected apartment from the database.
     * Shows an alert if no apartment is selected.
     */
    @FXML
    public void deleteApartment() {
        if (selectedApartment == null) {
            AlertHelper.showNoObjectSelected(NO_APARTMENT_SELECTED);
            return;
        }

        try {
            TouristApartmentDB apartmentDB = new TouristApartmentDB();
            apartmentDB.deleteTouristApartment(selectedApartment);
            setApartmentList();
            apartmentDataTable.getItems().setAll(apartmentList);
            clearTextFields();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches for apartments based on the data provided in the text fields.
     * Displays the matching results in the apartment table.
     *
     * @throws SQLException if a database access error occurs.
     * @throws IOException if an input/output error occurs.
     */
    @FXML
    public void searchApartment() throws SQLException, IOException {
        List<TouristApartmentDTO> resultList = new ArrayList<>();
        if (selectedApartment != null) {
            clearTextFields();
            setApartmentList();
            return;
        }

        String nameText = apartmentName.getText() != null ? apartmentName.getText().trim().toLowerCase() : "";
        String addressText = apartmentAddress.getText() != null ? apartmentAddress.getText().trim().toLowerCase() : "";
        String distanceText = apartmentDistanceToDowntown.getText() != null ? apartmentDistanceToDowntown.getText().trim() : "";

        for (TouristApartmentDTO apartment : apartmentList) {
            boolean matches = true;

            if (!nameText.isEmpty()) {
                matches &= apartment.getNombre() != null && apartment.getNombre().toLowerCase().contains(nameText);
            }
            if (!addressText.isEmpty()) {
                matches &= apartment.getCalle() != null && apartment.getCalle().toLowerCase().contains(addressText);
            }
            if (!distanceText.isEmpty()) {
                matches &= String.valueOf(apartment.getDowntownDistance()).contains(distanceText);
            }

            if (matches) {
                resultList.add(apartment);
            }
        }

        apartmentDataTable.getItems().setAll(resultList);
    }

    /**
     * Selects the currently selected apartment for booking.
     * Initializes the booking data and loads the booking list view.
     * Shows an alert if no apartment is selected.
     */
    @FXML
    public void selectApartmentForBooking() {
        if (selectedApartment == null) {
            AlertHelper.showNoObjectSelected(NO_APARTMENT_SELECTED);
            return;
        }

        if (dataForBooking == null)
            dataForBooking = new BookingDataHelper((ClientDTO) null, selectedApartment);
        else
            dataForBooking.setApartmentForBooking(selectedApartment);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("booking-list.fxml"));
            AnchorPane menu = loader.load();

            BookingListController controller = loader.getController();
            controller.setTemplateComponent(templateComponent);
            controller.setDataForBooking(dataForBooking);
            templateComponent.getChildren().setAll(menu);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the template component.
     *
     * @param templateComponent the template component to set.
     */
    public void setTemplateComponent(AnchorPane templateComponent) {
        this.templateComponent = templateComponent;
    }

    /**
     * Sets the visibility of the housing selection button.
     *
     * @param isSelectingApartment true to make the button visible, false to hide it.
     */
    public void setIsSelectingHousing(boolean isSelectingApartment) {
        isSelectingHousingButton.setVisible(isSelectingApartment);
    }

    /**
     * Sets the booking data helper.
     *
     * @param dataForBooking the booking data helper to set.
     */
    public void setDataForBooking(BookingDataHelper dataForBooking) {
        this.dataForBooking = dataForBooking;
    }

    /**
     * Retrieves and sets the list of apartments from the database.
     *
     * @throws SQLException if a database access error occurs.
     * @throws IOException if an input/output error occurs.
     */
    private void setApartmentList() throws SQLException, IOException {
        TouristApartmentDB apartmentDB = new TouristApartmentDB();
        this.apartmentList = apartmentDB.getTouristApartments();
    }

    /**
     * Selects and displays the details of the specified apartment.
     *
     * @param selectedApartment the apartment to display.
     */
    private void selectApartmentDetails(TouristApartmentDTO selectedApartment) {
        this.selectedApartment = selectedApartment;
        apartmentIdLabel.setText(String.valueOf(selectedApartment.getHousingId()));
        apartmentName.setText(selectedApartment.getNombre());
        apartmentAddress.setText(selectedApartment.getCalle());
        apartmentDistanceToDowntown.setText(String.valueOf(selectedApartment.getDowntownDistance()));
    }

    /**
     * Clears the text fields and resets the selected apartment.
     */
    @FXML
    private void clearTextFields() {
        selectedApartment = null;
        apartmentIdLabel.setText("Id Cliente");
        apartmentName.setText("");
        apartmentAddress.setText("");
        apartmentDistanceToDowntown.setText("");
    }
}
