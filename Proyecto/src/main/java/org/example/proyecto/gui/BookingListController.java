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
    public Label clientEmailLabel;
    @FXML
    public Label bookingIdLabel;
    @FXML
    public Label housingNameLabel;
    @FXML
    public TableColumn bookingNameColumn;
    @FXML
    public Button deleteBookingUserButton;
    @FXML
    public Button selectBookingClientButton;

    private List<BookingDataHelper> bookingDataList = null;
    private BookingDataHelper selectedBooking = null;
    private ClientDTO clientForBooking = null;

    AnchorPane templateComponent = null;


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

        BookingDTO bookingToDelete = new BookingDTO(selectedBooking.getCheckInDate(), selectedBooking.getCheckOutDate(), selectedBooking.getBookingId(), 1, 1);
        if (AlertHelper.showConfirmationDialog("Confirmacion de eliminación", "¿Desea borrar la reserva de la base de datos?")) {
            try {
                BookingDB bookingDB = new BookingDB();
                bookingDB.deleteBookingByID(bookingToDelete);
                setBookingList();
                bookingDataTable.getItems().setAll(bookingDataList);
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //todo see sqlite foreign key constrainst failed
    @FXML
    public void insertBooking(ActionEvent actionEvent) {
        if (selectedBooking != null) {
            clearTextFields();
            return;
        }

        if (clientForBooking == null || checkInDate.getValue() == null || checkOutDate.getValue() == null) {
            AlertHelper.showMissingDataAlert();
            return;
        }

        BookingDTO bookingToRegister = new BookingDTO(checkInDate.getValue(), checkOutDate.getValue(), clientForBooking.getId_cuenta(), 1, 2);
        System.out.println(bookingToRegister.toString());
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

    @FXML
    public void initialize(){
        try {

            bookingIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
            checkInDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
            checkOutDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
            clientEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

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
