package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.proyecto.model.account.AccountDTO;
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
    public Label clientEmailLabel;
    @FXML
    public Label bookingIdLabel;
    @FXML
    public Label housingNameLabel;
    @FXML
    public Button deleteBookingUserButton;
    @FXML
    public Button selectBookingClientButton;

    private List<BookingDataHelper> bookingDataList = null;
    private List<ClientDTO> clientList = null;
    private BookingDataHelper selectedBooking = null;
    private ClientDTO clientForBooking = null;
    private HotelDTO hotelForBooking = null;
    private TouristApartmentDTO touristApartmentForBooking = null;

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

    public void setTemplateComponent(AnchorPane templateComponent){
        this.templateComponent = templateComponent;
    }

    @FXML
    public void selectClientForBooking(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("client-list.fxml"));
            AnchorPane menu = loader.load();

            ClientListController controller = loader.getController();
            controller.setIsSelectingClient(true);
            controller.setTemplateComponent(templateComponent);

            templateComponent.getChildren().setAll(menu);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void updateBooking(ActionEvent actionEvent) {
        if (selectedBooking == null) {
            AlertHelper.showNoUserSelectedAlert();
            return;
        }
        //BookingDataHelper updatedBooking = new BookingDataHelper(selectedBooking);
    }

    @FXML
    public void deleteBooking(ActionEvent actionEvent) {
        if (selectedBooking == null) {
            AlertHelper.showUpdatedUserAlert();
            return;
        }

        BookingDTO bookingToDelete = new BookingDTO(selectedBooking.getCheckInDate(), selectedBooking.getCheckOutDate(), selectedBooking.getBookingId(), 0, 0);
        if (AlertHelper.showConfirmationDialog("Confirmacion de eliminación", "¿Desea borrar la reserva de la base de datos?")) {
            try {
                BookingDB bookingDB = new BookingDB();
                bookingDB.deleteBooking(bookingToDelete);
                setBookingList();
                bookingDataTable.getItems().setAll(bookingDataList);
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

        if (clientForBooking == null || checkInDate.getValue() == null || checkOutDate.getValue() == null || (hotelForBooking == null && touristApartmentForBooking == null)) {
            AlertHelper.showMissingDataAlert();
            return;
        }

        BookingDTO bookingToRegister = null;
        if (hotelForBooking != null)
            bookingToRegister = new BookingDTO(checkInDate.getValue(), checkOutDate.getValue(), clientForBooking.getId_cuenta(), clientForBooking.getId_cuenta(), touristApartmentForBooking.getHousingId());
        else if (touristApartmentForBooking != null)
            bookingToRegister = new BookingDTO(checkInDate.getValue(), checkOutDate.getValue(), clientForBooking.getId_cuenta(), clientForBooking.getId_cuenta(), hotelForBooking.getHousingId());



        if (AlertHelper.showConfirmationDialog("Confirmacion de reserva", "¿Desea registrar esta reserva en la base de datos?")){
            try{
                BookingDB bookingDB = new BookingDB();
                bookingDB.insertBooking(bookingToRegister);
                setBookingList();
                bookingDataTable.getItems().setAll(bookingDataList);
                AlertHelper.showInsertedUserAlert();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void clearTextFields() {
        selectedBooking = null;
        bookingIdLabel.setText("Id Reserva");
        checkInDate.setValue(null);
        checkOutDate.setValue(null);
        clientEmailLabel.setText("Email Cliente");
    }

    @FXML
    public void searchBooking(ActionEvent actionEvent) {
    }



    private void selectBookingDetails(BookingDataHelper selectedBooking) {
        this.selectedBooking = selectedBooking;
        clientEmailLabel.setText(selectedBooking.getEmail());
        checkInDate.setValue(selectedBooking.getCheckInDate());
        checkOutDate.setValue(selectedBooking.getCheckOutDate());
        bookingIdLabel.setText(String.valueOf(selectedBooking.getBookingId()));
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



    public void setClientForBooking(ClientDTO clientForBooking) {
        this.clientForBooking = clientForBooking;
        clientEmailLabel.setText(clientForBooking.getEmail());
    }

    @FXML
    public void selectHousingForBooking(ActionEvent actionEvent) {
    }

    public void deleteBookingUser(ActionEvent actionEvent) {
        clientForBooking = null;
        deleteBookingUserButton.setVisible(false);
        selectBookingClientButton.setVisible(true);
        clientEmailLabel.setText("Email Cliente");
    }

    public void setSelectBookingCLientButton(){
        if (clientForBooking == null){
            deleteBookingUserButton.setVisible(false);
            selectBookingClientButton.setVisible(true);
        } else {
            deleteBookingUserButton.setVisible(true);
            selectBookingClientButton.setVisible(false);
        }
    }

}
