package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.proyecto.model.booking.BookingDataHelper;
import org.example.proyecto.model.client.ClientDB;
import org.example.proyecto.model.client.ClientDTO;
import org.example.proyecto.model.hotel.HotelDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for managing the client list view in the GUI.
 * It handles the display, update, deletion, registration, and search of client data.
 */
public class ClientListController {
    @FXML
    public TextField clientEmail;
    @FXML
    public TextField clientName;
    @FXML
    public TextField clientAddress;
    @FXML
    public Label clientId;
    @FXML
    public TableView<ClientDTO> clientDataTable;
    @FXML
    public TableColumn<ClientDTO, Integer> clientIdColumn;
    @FXML
    public TableColumn<ClientDTO, String> clientNameColumn;
    @FXML
    public TableColumn<ClientDTO, String> clientEmailColumn;
    @FXML
    public TableColumn<ClientDTO, String> clientAddressColumn;
    @FXML
    public Button selectClientButton;
    @FXML
    public Button searchClientButton;
    @FXML
    AnchorPane templateComponent = null;

    private List<ClientDTO> clientList = null;
    private ClientDTO selectedClient = null;
    BookingDataHelper dataForBooking = null;

    /**
     * Initializes the controller class. Sets up the table columns and loads the client data.
     * Adds a listener to handle selection changes in the client table.
     */
    @FXML
    public void initialize() {
        try {
            searchClientButton.setGraphic(GuiEffectsHelper.getSearchIcon());

            clientIdColumn.setCellValueFactory(new PropertyValueFactory<>("id_cuenta"));
            clientNameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre_apellidos"));
            clientEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            clientAddressColumn.setCellValueFactory(new PropertyValueFactory<>("direccion"));

            setClientList();
            clientDataTable.getItems().setAll(clientList);

            clientDataTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    selectClientDetails(newValue);
                }
            });
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Registers a new client with the data provided in the text fields.
     * Validates the input data and shows alerts in case of missing information.
     */
    @FXML
    public void registerClient() {
        if (selectedClient != null) {
            clearTextFields();
            return;
        }

        if (clientName.getText().isBlank() || clientEmail.getText().isBlank() || clientAddress.getText().isBlank()) {
            AlertHelper.showMissingDataAlert();
            return;
        }

        ClientDTO clientToRegister = new ClientDTO(clientEmail.getText(), clientName.getText(), clientAddress.getText());
        if (AlertHelper.showConfirmationDialog("Confirmación de registro", "¿Desea registrar al cliente en la base de datos?")) {
            try {
                ClientDB clientDB = new ClientDB();
                clientDB.insertClient(clientToRegister);
                setClientList();
                clientDataTable.getItems().setAll(clientList);
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Updates the selected client with the data provided in the text fields.
     * Validates the input data and shows alerts in case of missing information or no changes.
     *
     * @param actionEvent the event triggered by the button click.
     */
    @FXML
    public void updateClient(ActionEvent actionEvent) {
        if (selectedClient == null) {
            AlertHelper.showNoObjectSelected("No hay ningún cliente seleccionado.");
            return;
        } else if (clientName.getText().isBlank() || clientEmail.getText().isBlank() || clientAddress.getText().isBlank()) {
            AlertHelper.showMissingDataAlert();
            return;
        }

        ClientDTO updatedClient = new ClientDTO(selectedClient.getId_cuenta(), clientEmail.getText(), clientName.getText(), clientAddress.getText());

        if (updatedClient.equals(selectedClient)) {
            AlertHelper.showNoChangesAlert();
            return;
        }

        if (AlertHelper.showConfirmationDialog("Confirmación de actualización", "¿Desea realizar la actualización de datos?")) {
            try {
                ClientDB clientDB = new ClientDB();
                clientDB.updateClient(updatedClient);
                setClientList();
                clientDataTable.getItems().setAll(clientList);
                clearTextFields();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Deletes the selected client from the database.
     * Shows an alert if no client is selected.
     *
     * @param actionEvent the event triggered by the button click.
     */
    @FXML
    public void deleteClient(ActionEvent actionEvent) {
        if (selectedClient == null) {
            AlertHelper.showNoObjectSelected("No hay ningún cliente seleccionado.");
            return;
        }

        ClientDTO clientToDelete = new ClientDTO(selectedClient);

        if (AlertHelper.showConfirmationDialog("Confirmación de eliminación", "¿Desea eliminar al cliente de la base de datos?")) {
            try {
                ClientDB clientDB = new ClientDB();
                clientDB.deleteClient(clientToDelete);
                setClientList();
                clientDataTable.getItems().setAll(clientList);
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Searches for clients based on the data provided in the text fields.
     * Displays the matching results in the client table.
     *
     * @param actionEvent the event triggered by the button click.
     */
    @FXML
    public void searchClient(ActionEvent actionEvent) throws SQLException, IOException {
        List<ClientDTO> resultList = new ArrayList<>();

        if (selectedClient != null) {
            clearTextFields();
            setClientList();
            clientDataTable.getItems().setAll(clientList);
            return;
        }

        String nameText = clientName.getText() != null ? clientName.getText().trim().toLowerCase() : "";
        String emailText = clientEmail.getText() != null ? clientEmail.getText().trim().toLowerCase() : "";
        String addressText = clientAddress.getText() != null ? clientAddress.getText().trim().toLowerCase() : "";

        for (ClientDTO client : clientList) {
            boolean matches = true;

            if (!nameText.isEmpty()) {
                matches &= client.getNombre_apellidos() != null && client.getNombre_apellidos().toLowerCase().contains(nameText);
            }
            if (!emailText.isEmpty()) {
                matches &= client.getEmail() != null && client.getEmail().toLowerCase().contains(emailText);
            }
            if (!addressText.isEmpty()) {
                matches &= client.getDireccion() != null && client.getDireccion().toLowerCase().contains(addressText);
            }

            if (matches) {
                resultList.add(client);
            }
        }

        clientDataTable.getItems().setAll(resultList);
    }

    /**
     * Loads the client list data from the database and sets it to the client table.
     *
     * @throws SQLException if a database access error occurs.
     * @throws IOException if an input/output error occurs.
     */
    private void setClientList() throws SQLException, IOException {
        ClientDB clientDB = new ClientDB();
        this.clientList = clientDB.getClients();
    }

    /**
     * Sets the client details in the text fields when a client is selected from the table.
     *
     * @param selectedClient the selected client data.
     */
    private void selectClientDetails(ClientDTO selectedClient) {
        this.selectedClient = selectedClient;
        clientId.setText(String.valueOf(selectedClient.getId_cuenta()));
        clientEmail.setText(selectedClient.getEmail());
        clientName.setText(selectedClient.getNombre_apellidos());
        clientAddress.setText(selectedClient.getDireccion());
    }

    /**
     * Selects the client for booking.
     * Opens the booking list view and sets the necessary data for booking.
     *
     * @param actionEvent the event triggered by the button click.
     */
    @FXML
    public void selectClientForBooking(ActionEvent actionEvent) {
        if (selectedClient == null) {
            AlertHelper.showNoObjectSelected("No hay ningún cliente seleccionado.");
            return;
        }

        if (dataForBooking == null)
            dataForBooking = new BookingDataHelper(selectedClient, (HotelDTO) null);
        else
            dataForBooking.setClientForBooking(selectedClient);

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
     * Sets the template component to be used in the view.
     *
     * @param templateComponent the template component.
     */
    public void setTemplateComponent(AnchorPane templateComponent) {
        this.templateComponent = templateComponent;
    }

    /**
     * Sets the data for booking and updates the corresponding fields.
     *
     * @param dataForBooking the data for booking.
     */
    public void setDataForBooking(BookingDataHelper dataForBooking) {
        this.dataForBooking = dataForBooking;
    }

    /**
     * Sets whether the client selection button is visible.
     *
     * @param isSelectingClient flag indicating if client selection is in progress.
     */
    public void setIsSelectingClient(boolean isSelectingClient) {
        selectClientButton.setVisible(isSelectingClient);
    }

    /**
     * Clears the text fields and resets the selected client.
     */
    private void clearTextFields() {
        selectedClient = null;
        clientId.setText("");
        clientEmail.setText("");
        clientName.setText("");
        clientAddress.setText("");
    }
}
