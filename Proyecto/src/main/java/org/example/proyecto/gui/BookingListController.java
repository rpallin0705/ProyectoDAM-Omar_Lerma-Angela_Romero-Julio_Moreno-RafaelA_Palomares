package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.proyecto.model.booking.BookingDB;
import org.example.proyecto.model.booking.BookingDTO;
import org.example.proyecto.model.booking.BookingDataHelper;
import org.example.proyecto.model.client.ClientDB;
import org.example.proyecto.model.client.ClientDTO;

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
    public TextField bookingId;
    @FXML
    public DatePicker checkInDate;
    @FXML
    public DatePicker checkOutDate;
    @FXML
    public TextField countId;
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

    private List<BookingDataHelper> bookingDataList = null;
    private BookingDataHelper selectedBooking = null;
    private ClientDTO clientForBooking = null;


    @FXML
    public void updateBooking(ActionEvent actionEvent) {
    }

    @FXML
    public void deleteBooking(ActionEvent actionEvent) {
    }

    @FXML
    public void insertBooking(ActionEvent actionEvent) {
    }

    @FXML
    public void searchBooking(ActionEvent actionEvent) {
    }

    @FXML
    public void initialize(){
        try {


            bookingIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
            checkInDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
            checkOutDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
            clientEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

            setBookingList();
            bookingDataTable.getItems().setAll(bookingDataList);

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setBookingList() throws SQLException, IOException {
        ClientDB clientDB = new ClientDB();
        BookingDB bookingDB = new BookingDB();

        List<BookingDTO> bookings = bookingDB.getBookings();
        List<ClientDTO> clients = clientDB.getClients();

        Map<Integer, String> accountEmailMap = new HashMap<>();
        for (ClientDTO client : clients)
            accountEmailMap.put(client.getId_cuenta(), client.getEmail());

        List<BookingDataHelper> bookingsData = new ArrayList<>();
        for (BookingDTO book : bookings)
            bookingsData.add(new BookingDataHelper(book, accountEmailMap.get(book.getCountId())));

        this.bookingDataList = bookingsData;
    }
}
