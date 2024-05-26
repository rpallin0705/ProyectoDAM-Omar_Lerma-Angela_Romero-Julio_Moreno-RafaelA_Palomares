package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.proyecto.model.booking.BookingDB;
import org.example.proyecto.model.booking.BookingDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller class for managing the booking list view in the GUI.
 * It handles the display, update, deletion, registration, and search of booking data.
 */
public class BookingListController {

    @FXML
    public TableColumn bookingIdColumn;
    @FXML
    public TableColumn checkInDateColumn;
    @FXML
    public TableColumn checkOutDateColumn;
    @FXML
    public TableColumn countIdColumn;
    @FXML
    public TextField bookingId;
    @FXML
    public DatePicker checkInDate;
    @FXML
    public DatePicker checkOutDate;
    @FXML
    public TextField countId;

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
}
