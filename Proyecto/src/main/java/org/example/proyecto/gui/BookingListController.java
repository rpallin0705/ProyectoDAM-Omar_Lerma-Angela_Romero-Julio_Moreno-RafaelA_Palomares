package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.proyecto.model.booking.BookingDB;
import org.example.proyecto.model.booking.BookingDTO;
import org.example.proyecto.model.booking.BookingDataHelper;
import org.example.proyecto.model.client.ClientDB;
import org.example.proyecto.model.client.ClientDTO;
import org.example.proyecto.model.hotel.HotelDB;
import org.example.proyecto.model.hotel.HotelDTO;
import org.example.proyecto.model.touristApartment.TouristApartmentDB;
import org.example.proyecto.model.touristApartment.TouristApartmentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Controller class for managing the booking list view in the GUI.
 * It handles the display, update, deletion, registration, and search of booking data.
 */
public class BookingListController {

    @FXML
    public DatePicker checkInDate;
    @FXML
    public DatePicker checkOutDate;
    @FXML
    public TableView<BookingDataHelper> bookingDataTable;
    @FXML
    public TableColumn<BookingDataHelper, Integer> bookingIdColumn;
    @FXML
    public TableColumn<BookingDataHelper, LocalDate> checkInDateColumn;
    @FXML
    public TableColumn<BookingDataHelper, LocalDate> checkOutDateColumn;
    @FXML
    public TableColumn<BookingDataHelper, String> clientEmailColumn;
    @FXML
    public TableColumn<BookingDataHelper, String> bookingNameColumn;
    @FXML
    public TextField clientEmail;
    @FXML
    public Label bookingIdLabel;
    @FXML
    public TextField housingName;
    @FXML
    public Button deleteBookingUserButton;
    @FXML
    public Button selectClientForBookingButton;
    @FXML
    public Button selectHousingForBookingButton;

    private List<BookingDataHelper> bookingDataList = null;
    private List<ClientDTO> clientList = null;
    private BookingDataHelper selectedBooking = null;
    private BookingDataHelper dataForBooking = null;

    AnchorPane templateComponent = null;

    @FXML
    public void initialize(){
        try {

            bookingIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
            checkInDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
            checkOutDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
            clientEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            bookingNameColumn.setCellValueFactory(new PropertyValueFactory<>("housingName"));

            setBookingList();
            bookingDataTable.getItems().setAll(bookingDataList);

            bookingDataTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    selectBookingDetails(newValue);
                }
            });

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }




    @FXML
    public void updateBooking(ActionEvent actionEvent) {
        if (selectedBooking == null) {
            AlertHelper.showNoUserSelectedAlert();
            return;
        } else if (checkInDate.getValue() == null || checkOutDate.getValue() == null) {
            AlertHelper.showMissingDataAlert();
            return;
        }

        BookingDTO updatedBooking = new BookingDTO(selectedBooking.getCheckInDate(), selectedBooking.getCheckOutDate(), 0 ,0 );

        if (updatedBooking.equals(selectedBooking)){
            AlertHelper.showNoChangesAlert();
            return;
        }

        if (AlertHelper.showConfirmationDialog("Confirmación de registro", "¿Desea registrar esta reserva?")){
            try{
                BookingDB bookingDB = new BookingDB();
                bookingDB.updateBooking(updatedBooking);
                setBookingList();
                bookingDataTable.getItems().setAll(bookingDataList);
                clearTextFields();
            } catch (SQLException |IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void deleteBooking(ActionEvent actionEvent) {
        if (selectedBooking == null) {
            AlertHelper.showUpdatedUserAlert();
            return;
        }

        BookingDTO bookingToDelete = new BookingDTO(selectedBooking.getBooking());
        if (AlertHelper.showConfirmationDialog("Confirmacion de eliminación", "¿Desea borrar la reserva de la base de datos?")) {
            try {
                BookingDB bookingDB = new BookingDB();
                bookingDB.deleteBooking(bookingToDelete);
                setBookingList();
                bookingDataTable.getItems().setAll(bookingDataList);
                clearTextFields();
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void insertBooking(ActionEvent actionEvent) {
        if (selectedBooking != null) {
            clearTextFields();
            return;
        }

        if (dataForBooking.getClientForBooking() == null || checkInDate.getValue() == null || checkOutDate.getValue() == null || (dataForBooking.getHotelForBooking() == null && dataForBooking.getApartmentForBooking() == null)) {
            AlertHelper.showMissingDataAlert();
            return;
        }

        BookingDTO bookingToRegister = null;
        if (dataForBooking.getHotelForBooking() != null)
            bookingToRegister = new BookingDTO(checkInDate.getValue(), checkOutDate.getValue(), dataForBooking.getClientForBooking().getId_cuenta(), dataForBooking.getHotelForBooking().getHousingId());
        else if (dataForBooking.getApartmentForBooking() != null)
            bookingToRegister = new BookingDTO(checkInDate.getValue(), checkOutDate.getValue(), dataForBooking.getClientForBooking().getId_cuenta(), dataForBooking.getApartmentForBooking().getHousingId());



        if (AlertHelper.showConfirmationDialog("Confirmacion de reserva", "¿Desea registrar esta reserva en la base de datos?")){
            try{
                BookingDB bookingDB = new BookingDB();
                bookingDB.insertBooking(bookingToRegister);
                setBookingList();
                bookingDataTable.getItems().setAll(bookingDataList);
                clearTextFields();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void clearTextFields() {
        selectedBooking = null;
        dataForBooking = null;
        bookingIdLabel.setText("Id Reserva");
        checkInDate.setValue(null);
        checkOutDate.setValue(null);
        clientEmail.setText("");
        housingName.setText("");
        selectHousingForBookingButton.setDisable(false);
        selectClientForBookingButton.setDisable(false);
    }

    @FXML
    public void searchBooking(ActionEvent actionEvent) throws SQLException, IOException {
        List<BookingDataHelper> resultList = new ArrayList<>();

        if(selectedBooking != null) {
            clearTextFields();
            setBookingList();
            bookingDataTable.getItems().setAll(bookingDataList);
            return;
        }

        String emailText = clientEmail.getText() != null ? clientEmail.getText().trim().toLowerCase() : "";
        String housingText = housingName.getText() != null ? housingName.getText().trim().toLowerCase() : "";

        for (BookingDataHelper booking : bookingDataList) {
            boolean matches = true;

            if (!emailText.isEmpty()) {
                matches &= booking.getEmail() != null && booking.getEmail().toLowerCase().contains(emailText);
            }
            if (!housingText.isEmpty()) {
                matches &= booking.getHousingName() != null && booking.getHousingName().toLowerCase().contains(housingText);
            }

            if (matches) {
                resultList.add(booking);
            }
        }

        bookingDataTable.getItems().setAll(resultList);
    }



    private void selectBookingDetails(BookingDataHelper selectedBooking) {
        this.selectedBooking = selectedBooking;
        clientEmail.setText(selectedBooking.getEmail());
        housingName.setText(selectedBooking.getHousingName());
        checkInDate.setValue(selectedBooking.getCheckInDate());
        checkOutDate.setValue(selectedBooking.getCheckOutDate());
        bookingIdLabel.setText(String.valueOf(selectedBooking.getBookingId()));
        selectClientForBookingButton.setDisable(true);
        selectHousingForBookingButton.setDisable(true);
    }

    private void setBookingList() throws SQLException, IOException {
        ClientDB clientDB = new ClientDB();
        BookingDB bookingDB = new BookingDB();
        HotelDB hotelDB = new HotelDB();
        TouristApartmentDB touristDB = new TouristApartmentDB();

        List<BookingDTO> bookings = bookingDB.getBookings();
        List<ClientDTO> clients = clientDB.getClients();
        List<HotelDTO> hotels = hotelDB.getHotels();
        List<TouristApartmentDTO> apartments = touristDB.getTouristApartments();

        Map<Integer, String> accountEmailMap = new HashMap<>();
        for (ClientDTO client : clients)
            accountEmailMap.put(client.getId_cuenta(), client.getEmail());

        Map<Integer, String> housingNameMap = new HashMap<>();
        for (HotelDTO hotel : hotels)
            housingNameMap.put(hotel.getHousingId(), hotel.getNombre());
        for (TouristApartmentDTO apartment : apartments)
            housingNameMap.put(apartment.getHousingId(), apartment.getNombre());

        List<BookingDataHelper> bookingsData = new ArrayList<>();
        for (BookingDTO book : bookings) {
            String email = accountEmailMap.get(book.getCountId());
            String housingName = housingNameMap.get(book.getHousingId());
            bookingsData.add(new BookingDataHelper(book, email, housingName));
        }

        this.bookingDataList = bookingsData;
    }

    public void deleteBookingUser(ActionEvent actionEvent) {
        dataForBooking.setClientForBooking(null);
        deleteBookingUserButton.setVisible(false);
        selectClientForBookingButton.setVisible(true);
        clientEmail.setText("Email Cliente");
    }

    /*public void setSelectBookingCLientButton(){
        if (dataForBooking.getClientForBooking() == null){
            deleteBookingUserButton.setVisible(false);
            selectBookingClientButton.setVisible(true);
        } else {
            deleteBookingUserButton.setVisible(true);
            selectBookingClientButton.setVisible(false);
        }
    }*/

    public void setApartmentForBooking(TouristApartmentDTO apartmentForBooking) {
        this.dataForBooking.setApartmentForBooking(apartmentForBooking);
        housingName.setText(apartmentForBooking.getNombre());
    }



    public void setHotelForBooking(HotelDTO selectedHotel) {
        this.dataForBooking.setHotelForBooking(selectedHotel);
        housingName.setText(selectedHotel.getNombre());
    }

    @FXML
    public void selectClientForBooking(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("client-list.fxml"));
            AnchorPane menu = loader.load();

            ClientListController controller = loader.getController();
            controller.setIsSelectingClient(true);
            controller.setTemplateComponent(templateComponent);
            controller.setDataForBooking(dataForBooking);

            templateComponent.getChildren().setAll(menu);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void selectHousingForBooking(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = null;
            Object controller = null;
            if (AlertHelper.showHousingSelectionAlert())
                loader = new FXMLLoader(getClass().getResource("hotel-list.fxml"));
            else
                loader = new FXMLLoader(getClass().getResource("apartment-list.fxml"));

            AnchorPane menu = loader.load();
            controller = loader.getController();

            if (controller instanceof HotelListController hotelListController) {
                hotelListController.setIsSelectingHousing(true);
                hotelListController.setTemplateComponent(templateComponent);
                if (dataForBooking != null) {
                    dataForBooking.setApartmentForBooking(null);
                    dataForBooking.setHotelForBooking(null);
                }
                hotelListController.setDataForBooking(dataForBooking);
            } else if (controller instanceof ApartmentListController apartmentListController) {
                apartmentListController.setIsSelectingHousing(true);
                apartmentListController.setTemplateComponent(templateComponent);
                if (dataForBooking != null) {
                    dataForBooking.setHotelForBooking(null);
                    dataForBooking.setApartmentForBooking(null);
                }
                apartmentListController.setDataForBooking(dataForBooking);
            }
            templateComponent.getChildren().setAll(menu);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTemplateComponent(AnchorPane templateComponent){
        this.templateComponent = templateComponent;
    }

    public void setDataForBooking(BookingDataHelper dataForBooking){
        this.dataForBooking = dataForBooking;
        if (dataForBooking.getClientForBooking()!= null)
            clientEmail.setText(dataForBooking.getClientForBooking().getEmail());
        if (dataForBooking.getHotelForBooking()!= null)
            housingName.setText(dataForBooking.getHotelForBooking().getNombre());
        if (dataForBooking.getApartmentForBooking()!= null)
            housingName.setText(dataForBooking.getApartmentForBooking().getNombre());
    }
}
