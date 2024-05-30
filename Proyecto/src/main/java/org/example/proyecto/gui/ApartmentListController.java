package org.example.proyecto.gui;

import javafx.event.ActionEvent;
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

public class ApartmentListController {
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
    AnchorPane templateComponent = null;

    private List<TouristApartmentDTO> apartmentList = null;
    private TouristApartmentDTO selectedApartment = null;
    private BookingDataHelper dataForBooking = null;

    @FXML
    public void initialize() throws SQLException, IOException {
        try {
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

    private void setApartmentList() throws SQLException, IOException {
        TouristApartmentDB apartmentDB = new TouristApartmentDB();
        this.apartmentList = apartmentDB.getTouristApartments();
    }

    private void selectApartmentDetails(TouristApartmentDTO selectedApartment) {
        this.selectedApartment = selectedApartment;
        apartmentIdLabel.setText(String.valueOf(selectedApartment.getHousingId()));
        apartmentName.setText(selectedApartment.getNombre());
        apartmentAddress.setText(selectedApartment.getCalle());
        apartmentDistanceToDowntown.setText(String.valueOf(selectedApartment.getDowntownDistance()));
    }

    //TODO revisar pareint a parsefloat
    @FXML
    public void registerApartment() {

        if (selectedApartment != null) {
            clearTextFields();
            return;
        }

        if ( apartmentAddress.getText().isBlank() || apartmentName.getText().isBlank() || apartmentDistanceToDowntown.getText().isBlank()){
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
            // Manejar la excepción apropiadamente, podría ser mostrando un mensaje de error al usuario.
            e.printStackTrace();
        }
    }

    @FXML
    public void updateApartment() {
        if (selectedApartment == null) {
            AlertHelper.showNoUserSelectedAlert();
            return;
        }

        if ( apartmentAddress.getText().isBlank() || apartmentName.getText().isBlank() || apartmentDistanceToDowntown.getText().isBlank()){
            AlertHelper.showMissingDataAlert();
            return;
        }

        TouristApartmentDTO updatedApartment = new TouristApartmentDTO( selectedApartment.getHousingId(), apartmentName.getText(), apartmentAddress.getText(), Float.parseFloat(apartmentDistanceToDowntown.getText()) );

        if (updatedApartment.equals(selectedApartment)){
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
            // Manejar la excepción apropiadamente, podría ser mostrando un mensaje de error al usuario.
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteApartment() {
        if (selectedApartment == null) {
            AlertHelper.showNoUserSelectedAlert();
            return;
        }

        try {
            TouristApartmentDB apartmentDB = new TouristApartmentDB();
            apartmentDB.deleteTouristApartment(selectedApartment);
            setApartmentList();
            apartmentDataTable.getItems().setAll(apartmentList);
            clearTextFields();
        } catch (SQLException | IOException e) {
            // Manejar la excepción apropiadamente, podría ser mostrando un mensaje de error al usuario.
            e.printStackTrace();
        }
    }

    private void clearTextFields() {
        selectedApartment = null;
        apartmentIdLabel.setText("Id Cliente");
        apartmentName.setText("");
        apartmentAddress.setText("");
        apartmentDistanceToDowntown.setText("");
    }

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

    @FXML
    public void selectApartmentForBooking() {
        if (selectedApartment == null) {
            AlertHelper.showNoUserSelectedAlert();
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
            //controller.setSelectBookingCLientButton();
            templateComponent.getChildren().setAll(menu);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTemplateComponent(AnchorPane templateComponent) {
        this.templateComponent = templateComponent;
    }

    public void setIsSelectingHousing(boolean isSelectingApartment) {
        isSelectingHousingButton.setVisible(isSelectingApartment);
    }

    public void setDataForBooking(BookingDataHelper dataForBooking) {
        this.dataForBooking = dataForBooking;
    }
}
