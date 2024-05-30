package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.proyecto.model.booking.BookingDataHelper;
import org.example.proyecto.model.client.ClientDTO;
import org.example.proyecto.model.hotel.HotelDB;
import org.example.proyecto.model.hotel.HotelDTO;
import org.example.proyecto.model.hotel.RoomType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelListController {
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
    public TableColumn<HotelDTO,Integer> hotelHostNumberColumn;
    @FXML
    public Button isSelectingHousingButton;

    @FXML
    AnchorPane templateComponent = null;

    private List<HotelDTO> hotelList = null;
    private HotelDTO selectedHotel = null;
    BookingDataHelper dataForBooking = null;


    @FXML
    public void initialize() throws SQLException, IOException {
        try{
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

    private void setHotelList() throws SQLException, IOException {
        HotelDB hotelDB = new HotelDB();
        this.hotelList = hotelDB.getHotels();
    }

    private void selectClientDetails(HotelDTO selectedHotel) {
        this.selectedHotel = selectedHotel;
        hotelIdLabel.setText(String.valueOf(selectedHotel.getHousingId()));
        hotelName.setText(selectedHotel.getNombre());
        hotelAddress.setText(selectedHotel.getCalle());
        hotelClassification.setText(String.valueOf(selectedHotel.getHotelClassification()));
        hotelRoomType.setValue(selectedHotel.getRoomType());
        hotelHostNumber.setText(String.valueOf(selectedHotel.getHostNumber()));
    }

    @FXML
    public void registerHotel() {
        if (selectedHotel != null){
            clearTextFields();
            return;
        }

        if(hotelAddress.getText().isBlank() || hotelName.getText().isBlank() ||
           hotelClassification.getText().isBlank() || hotelRoomType.getValue() == null ||
           hotelHostNumber.getText().isBlank() ){
            AlertHelper.showMissingDataAlert();
            return;
        }

        HotelDTO hotelToRegister = new HotelDTO(hotelName.getText(), hotelAddress.getText(),
                                                Integer.parseInt(hotelClassification.getText()), hotelRoomType.getValue(),
                                                Integer.parseInt(hotelHostNumber.getText()));

        if (AlertHelper.showConfirmationDialog("Confirmacion de registro de hotel", "¿Desea registrar el hotel en la base de datos?")){
            try{
                HotelDB hotelDB = new HotelDB();
                hotelDB.insertHotel(hotelToRegister);
                setHotelList();
                hotelDataTable.getItems().setAll(hotelList);
                clearTextFields();

            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
                //todo agregar alertas de error
            }
        }
    }

    private void clearTextFields() {
        selectedHotel = null;
        hotelIdLabel.setText("Id Alojamiento");
        hotelName.setText("");
        hotelAddress.setText("");
        hotelClassification.setText("");
        hotelRoomType.setValue(null);
        hotelHostNumber.setText("");
    }

    public void updateHotel() {
        if (selectedHotel == null){
            AlertHelper.showNoUserSelectedAlert();
            return;
        } else if (hotelName.getText().isBlank() || hotelAddress.getText().isBlank() || hotelClassification.getText().isBlank() ||
                (hotelRoomType.getValue() == null) || hotelHostNumber.getText().isBlank()) {
            AlertHelper.showMissingDataAlert();
            return;
        }
        HotelDTO updatedHotel = new HotelDTO(selectedHotel.getHousingId(), hotelName.getText(), hotelAddress.getText(),
                                                Integer.parseInt(hotelClassification.getText()), hotelRoomType.getValue(),
                                                Integer.parseInt(hotelHostNumber.getText()));

        if (updatedHotel.equals(selectedHotel)){
            AlertHelper.showNoChangesAlert();
            return;
        }

        if (AlertHelper.showConfirmationDialog("Confirmación de actualización", "¿Desea realizar la actualización de datos?")) {
            try{
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

    public void deleteHotel() {
        if (selectedHotel == null) {
            AlertHelper.showNoUserSelectedAlert();
            return;
        }

        HotelDTO hotelToDelete = new HotelDTO(selectedHotel);

        if (AlertHelper.showConfirmationDialog("Confirmación de eliminación", "¿Desea eliminar el hotel de la base de datos?")) {
            try{
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

    @FXML
    public void searchHotel(ActionEvent actionEvent) throws SQLException, IOException {
        List<HotelDTO> resultList = new ArrayList<>();

        if (selectedHotel != null) {
            clearTextFields();
            setHotelList();
            return;
        }

        String nameText = hotelName.getText() != null ? hotelName.getText().trim().toLowerCase() : "";
        String addressText = hotelAddress.getText() != null ? hotelAddress.getText().trim().toLowerCase() : "";
        String classificationText = hotelClassification.getText() != null ? hotelClassification.getText().trim() : "";
        RoomType roomTypeValue = hotelRoomType.getValue();
        String hostNumberText = hotelHostNumber.getText() != null ? hotelHostNumber.getText().trim() : "";

        for (HotelDTO hotel : hotelList) {
            boolean matches = true;

            if (!nameText.isEmpty()) {
                matches &= hotel.getNombre() != null && hotel.getNombre().toLowerCase().contains(nameText);
            }
            if (!addressText.isEmpty()) {
                matches &= hotel.getCalle() != null && hotel.getCalle().toLowerCase().contains(addressText);
            }
            if (!classificationText.isEmpty()) {
                matches &= String.valueOf(hotel.getHotelClassification()).contains(classificationText);
            }
            if (roomTypeValue != null) {
                matches &= hotel.getRoomType() != null && hotel.getRoomType().equals(roomTypeValue);
            }
            if (!hostNumberText.isEmpty()) {
                matches &= String.valueOf(hotel.getHostNumber()).contains(hostNumberText);
            }

            if (matches) {
                resultList.add(hotel);
            }
        }

        hotelDataTable.getItems().setAll(resultList);
    }

    public void setIsSelectingHousing(boolean isSelectingHotel) {
        System.out.println(isSelectingHotel);
        isSelectingHousingButton.setVisible(isSelectingHotel);
        System.out.println(isSelectingHousingButton.isVisible());
    }

    @FXML
    public void selectHotelForBooking() {
        if (selectedHotel == null) {
            AlertHelper.showNoUserSelectedAlert();
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
            //controller.setSelectBookingCLientButton();
            templateComponent.getChildren().setAll(menu);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTemplateComponent(AnchorPane templateComponent) {
        this.templateComponent = templateComponent;
    }

    public void setDataForBooking(BookingDataHelper dataForBooking){
        this.dataForBooking = dataForBooking;
    }
}
