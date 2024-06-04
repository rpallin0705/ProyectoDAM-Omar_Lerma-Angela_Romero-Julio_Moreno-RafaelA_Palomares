package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.proyecto.gui.helpers.AlertHelper;
import org.example.proyecto.gui.helpers.GuiEffectsHelper;
import org.example.proyecto.model.booking.BookingDataHelper;
import org.example.proyecto.model.client.ClientDTO;
import org.example.proyecto.model.hotel.HotelDB;
import org.example.proyecto.model.hotel.HotelDTO;
import org.example.proyecto.model.hotel.RoomType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for managing hotel list view.
 */
public class HotelListController {

    public static final String NO_HOTEL_SELECTED = "No hay ningún hotel seleccionado.";
    @FXML
    public Label hotelIdLabel;
    @FXML
    public TextField hotelName;
    @FXML
    public TextField hotelAddress;
    @FXML
    public TextField hotelClassification;
    @FXML
    public ChoiceBox<RoomType> hotelRoomType;
    @FXML
    public TextField hotelHostNumber;
    @FXML
    public TableView<HotelDTO> hotelDataTable;
    @FXML
    public TableColumn<HotelDTO, Integer> hotelIdColumn;
    @FXML
    public TableColumn<HotelDTO, String> hotelNameColumn;
    @FXML
    public TableColumn<HotelDTO, String> hotelAddressColumn;
    @FXML
    public TableColumn<HotelDTO, Integer> hotelClassificationColumn;
    @FXML
    public TableColumn<HotelDTO, RoomType> hotelRoomtypeColumn;
    @FXML
    public TableColumn<HotelDTO, Integer> hotelHostNumberColumn;
    @FXML
    public Button isSelectingHousingButton;
    @FXML
    public Button updateHotelButton;
    @FXML
    public Button deleteHotelButton;
    @FXML
    public Button insertHotelButton;
    @FXML
    public Button searchHotelButton;
    @FXML
    AnchorPane templateComponent = null;

    private List<HotelDTO> hotelList = null;
    private HotelDTO selectedHotel = null;
    BookingDataHelper dataForBooking = null;

    /**
     * Initializes the hotel list view.
     */
    @FXML
    public void initialize() throws SQLException, IOException {
        try {
            searchHotelButton.setGraphic(GuiEffectsHelper.getSearchIcon());

            hotelIdColumn.setCellValueFactory(new PropertyValueFactory<>("housingId"));
            hotelNameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            hotelAddressColumn.setCellValueFactory(new PropertyValueFactory<>("calle"));
            hotelClassificationColumn.setCellValueFactory(new PropertyValueFactory<>("hotelClassification"));
            hotelRoomtypeColumn.setCellValueFactory(new PropertyValueFactory<>("roomType"));
            hotelHostNumberColumn.setCellValueFactory(new PropertyValueFactory<>("hostNumber"));

            hotelRoomType.getItems().addAll(RoomType.values());

            setHotelList();
            hotelDataTable.getItems().setAll(hotelList);

            hotelDataTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    selectClientDetails(newValue);
                }
            });

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Registers a new hotel.
     */
    @FXML
    public void registerHotel() {
        if (selectedHotel != null) {
            clearTextFields();
            return;
        }

        if (hotelAddress.getText().isBlank() || hotelName.getText().isBlank() ||
                hotelClassification.getText().isBlank() || hotelRoomType.getValue() == null ||
                hotelHostNumber.getText().isBlank()) {
            AlertHelper.showMissingDataAlert();
            return;
        }

        HotelDTO hotelToRegister = new HotelDTO(hotelName.getText(), hotelAddress.getText(),
                Integer.parseInt(hotelClassification.getText()), hotelRoomType.getValue(),
                Integer.parseInt(hotelHostNumber.getText()));

        if (AlertHelper.showConfirmationDialog("Confirmacion de registro de hotel", "¿Desea registrar el hotel en la base de datos?")) {
            try {
                HotelDB hotelDB = new HotelDB();
                hotelDB.insertHotel(hotelToRegister);
                setHotelList();
                hotelDataTable.getItems().setAll(hotelList);
                clearTextFields();

            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Updates the selected hotel.
     */
    @FXML
    public void updateHotel() {
        if (selectedHotel == null) {
            AlertHelper.showNoObjectSelected(NO_HOTEL_SELECTED);
            return;
        } else if (hotelName.getText().isBlank() || hotelAddress.getText().isBlank() || hotelClassification.getText().isBlank() ||
                (hotelRoomType.getValue() == null) || hotelHostNumber.getText().isBlank()) {
            AlertHelper.showMissingDataAlert();
            return;
        }
        HotelDTO updatedHotel = new HotelDTO(hotelName.getText(), hotelAddress.getText(),
                Integer.parseInt(hotelClassification.getText()), hotelRoomType.getValue(),
                Integer.parseInt(hotelHostNumber.getText()));

        if (updatedHotel.equals(selectedHotel)) {
            AlertHelper.showNoChangesAlert();
            return;
        }

        if (AlertHelper.showConfirmationDialog("Confirmación de actualización", "¿Desea realizar la actualización de datos?")) {
            try {
                HotelDB hotelDB = new HotelDB();
                hotelDB.updateHotel(updatedHotel);
                setHotelList();
                hotelDataTable.getItems().setAll(hotelList);
                clearTextFields();
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Deletes the selected hotel.
     */
    @FXML
    public void deleteHotel() {
        if (selectedHotel == null) {
            AlertHelper.showNoObjectSelected(NO_HOTEL_SELECTED);
            return;
        }

        HotelDTO hotelToDelete = new HotelDTO(selectedHotel);

        if (AlertHelper.showConfirmationDialog("Confirmación de eliminación", "¿Desea eliminar el hotel de la base de datos?")) {
            try {
                HotelDB hotelDB = new HotelDB();
                hotelDB.deleteHotel(hotelToDelete);
                setHotelList();
                hotelDataTable.getItems().setAll(hotelList);
                clearTextFields();
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Searches for hotels based on the provided criteria.
     *
     * @param actionEvent The action event triggering the search.
     */
    @FXML
    public void searchHotel(ActionEvent actionEvent) throws SQLException, IOException {
        if (selectedHotel != null) {
            handleSelectedHotel();
            return;
        }

        List<HotelDTO> resultList = filterHotels(
                hotelList,
                getTrimmedText(hotelName),
                getTrimmedText(hotelAddress),
                getTrimmedText(hotelClassification),
                hotelRoomType.getValue(),
                getTrimmedText(hotelHostNumber)
        );

        hotelDataTable.getItems().setAll(resultList);
    }

    private void handleSelectedHotel() throws SQLException, IOException {
        clearTextFields();
        setHotelList();
    }

    private List<HotelDTO> filterHotels(List<HotelDTO> hotels, String nameText, String addressText, String classificationText, RoomType roomType, String hostNumberText) {
        List<HotelDTO> resultList = new ArrayList<>();
        for (HotelDTO hotel : hotels) {
            if (matches(hotel, nameText, addressText, classificationText, roomType, hostNumberText)) {
                resultList.add(hotel);
            }
        }
        return resultList;
    }

    private boolean matches(HotelDTO hotel, String nameText, String addressText, String classificationText, RoomType roomType, String hostNumberText) {
        return matchesName(hotel, nameText) &&
                matchesAddress(hotel, addressText) &&
                matchesClassification(hotel, classificationText) &&
                matchesRoomType(hotel, roomType) &&
                matchesHostNumber(hotel, hostNumberText);
    }

    private boolean matchesName(HotelDTO hotel, String nameText) {
        return nameText.isEmpty() || (hotel.getNombre() != null && hotel.getNombre().toLowerCase().contains(nameText));
    }

    private boolean matchesAddress(HotelDTO hotel, String addressText) {
        return addressText.isEmpty() || (hotel.getCalle() != null && hotel.getCalle().toLowerCase().contains(addressText));
    }

    private boolean matchesClassification(HotelDTO hotel, String classificationText) {
        return classificationText.isEmpty() || String.valueOf(hotel.getHotelClassification()).contains(classificationText);
    }

    private boolean matchesRoomType(HotelDTO hotel, RoomType roomType) {
        return roomType == null || (hotel.getRoomType() != null && hotel.getRoomType().equals(roomType));
    }

    private boolean matchesHostNumber(HotelDTO hotel, String hostNumberText) {
        return hostNumberText.isEmpty() || String.valueOf(hotel.getHostNumber()).contains(hostNumberText);
    }

    private String getTrimmedText(TextField textField) {
        return textField.getText() != null ? textField.getText().trim().toLowerCase() : "";
    }


    /**
     * Selects a hotel for booking and navigates to the booking list view.
     */
    @FXML
    public void selectHotelForBooking() {
        if (selectedHotel == null) {
            AlertHelper.showNoObjectSelected(NO_HOTEL_SELECTED);
            return;
        }
        if (dataForBooking == null)
            dataForBooking = new BookingDataHelper((ClientDTO) null, selectedHotel);
        else
            dataForBooking.setHotelForBooking(selectedHotel);

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
     * Sets the template component for navigation.
     *
     * @param templateComponent The template component for navigation.
     */
    public void setTemplateComponent(AnchorPane templateComponent) {
        this.templateComponent = templateComponent;
    }

    /**
     * Sets the data for booking.
     *
     * @param dataForBooking The data for booking.
     */
    public void setDataForBooking(BookingDataHelper dataForBooking) {
        this.dataForBooking = dataForBooking;
    }

    /**
     * Sets the flag to control whether housing is being selected.
     *
     * @param isSelectingHotel A boolean flag indicating whether housing is being selected.
     */
    public void setIsSelectingHousing(boolean isSelectingHotel) {
        isSelectingHousingButton.setVisible(isSelectingHotel);
    }

    /**
     * Clears the text fields.
     */
    @FXML
    private void clearTextFields() {
        selectedHotel = null;
        hotelIdLabel.setText("Id Alojamiento");
        hotelName.setText("");
        hotelAddress.setText("");
        hotelClassification.setText("");
        hotelRoomType.setValue(null);
        hotelHostNumber.setText("");
    }

    /**
     * Selects hotel details for display.
     *
     * @param selectedHotel The selected hotel.
     */
    private void selectClientDetails(HotelDTO selectedHotel) {
        this.selectedHotel = selectedHotel;
        hotelIdLabel.setText(String.valueOf(selectedHotel.getHousingId()));
        hotelName.setText(selectedHotel.getNombre());
        hotelAddress.setText(selectedHotel.getCalle());
        hotelClassification.setText(String.valueOf(selectedHotel.getHotelClassification()));
        hotelRoomType.setValue(selectedHotel.getRoomType());
        hotelHostNumber.setText(String.valueOf(selectedHotel.getHostNumber()));
    }

    /**
     * Sets the list of hotels.
     *
     * @throws SQLException If a SQL exception occurs.
     * @throws IOException  If an IO exception occurs.
     */
    private void setHotelList() throws SQLException, IOException {
        HotelDB hotelDB = new HotelDB();
        this.hotelList = hotelDB.getHotels();
    }
}
