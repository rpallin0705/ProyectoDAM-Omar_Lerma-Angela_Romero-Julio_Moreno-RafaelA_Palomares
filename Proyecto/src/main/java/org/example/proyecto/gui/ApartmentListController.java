package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
    AnchorPane templateComponent = null;

    private List<TouristApartmentDTO> apartmentList = null;
    private TouristApartmentDTO selectedApartment = null;

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

        try {
            TouristApartmentDTO updatedApartment = new TouristApartmentDTO(
                    selectedApartment.getHousingId(),
                    apartmentName.getText(),
                    apartmentAddress.getText(),
                    Integer.parseInt(apartmentDistanceToDowntown.getText())
            );

            if (updatedApartment.equals(selectedApartment)) {
                AlertHelper.showNoChangesAlert();
                return;
            }

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

    @FXML
    public void searchApartment(ActionEvent actionEvent) throws SQLException, IOException {
        // Implementación para buscar apartamentos...
    }

    private void clearTextFields() {
        selectedApartment = null;
        apartmentIdLabel.setText("Id Cliente");
        apartmentName.setText("");
        apartmentAddress.setText("");
        apartmentDistanceToDowntown.setText("");
    }
}
