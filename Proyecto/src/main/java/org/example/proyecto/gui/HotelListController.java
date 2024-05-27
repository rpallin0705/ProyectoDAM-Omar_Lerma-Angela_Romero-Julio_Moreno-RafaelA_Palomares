package org.example.proyecto.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.proyecto.model.hotel.HotelDB;
import org.example.proyecto.model.hotel.HotelDTO;
import org.example.proyecto.model.hotel.RoomType;

import java.io.IOException;
import java.sql.SQLException;
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
    AnchorPane templateComponent = null;

    private List<HotelDTO> hotelList = null;
    private HotelDTO selectedHotel = null;
    private boolean isSelectingHotel = false;

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
                AlertHelper.showInsertedUserAlert();
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void clearTextFields() {
        hotelIdLabel.setText("Id Alojamiento");
        hotelName.setText("");
        hotelAddress.setText("");
        hotelClassification.setText("");
        hotelRoomType.setValue(null);
        hotelHostNumber.setText("");
    }
}
