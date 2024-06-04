package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.proyecto.gui.helpers.AlertHelper;
import org.example.proyecto.gui.helpers.GuiEffectsHelper;
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
    public static final String NO_CLIENT_SELECTED = "No hay ningún cliente seleccionado.";
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
                clearTextFields();
            } catch (SQLException | IOException e) {
                System.err.println("Usuario ya existente");
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
            AlertHelper.showNoObjectSelected(NO_CLIENT_SELECTED);
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
            AlertHelper.showNoObjectSelected(NO_CLIENT_SELECTED);
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
        if (selectedClient != null) {
            handleSelectedClient();
            return;
        }

        List<ClientDTO> resultList = filterClients(clientList, getTrimmedText(clientName), getTrimmedText(clientEmail), getTrimmedText(clientAddress));
        clientDataTable.getItems().setAll(resultList);
    }

    /**
     * Handles the selection of a client by clearing text fields, updating the client list,
     * and setting the client data table with the updated list of clients.
     *
     * @throws SQLException if a database access error occurs
     * @throws IOException if an input or output exception occurred
     */
    private void handleSelectedClient() throws SQLException, IOException {
        clearTextFields();
        setClientList();
        clientDataTable.getItems().setAll(clientList);
    }

    /**
     * Filters a list of clients based on the provided name, email, and address.
     *
     * @param clients the list of clients to filter
     * @param nameText the name text to filter by
     * @param emailText the email text to filter by
     * @param addressText the address text to filter by
     * @return a list of clients that match the provided name, email, and address
     */
    private List<ClientDTO> filterClients(List<ClientDTO> clients, String nameText, String emailText, String addressText) {
        List<ClientDTO> resultList = new ArrayList<>();
        for (ClientDTO client : clients) {
            if (matches(client, nameText, emailText, addressText)) {
                resultList.add(client);
            }
        }
        return resultList;
    }

    /**
     * Checks if a client matches the provided name, email, and address.
     *
     * @param client the client to check
     * @param nameText the name text to match
     * @param emailText the email text to match
     * @param addressText the address text to match
     * @return true if the client matches the name, email, and address; false otherwise
     */
    private boolean matches(ClientDTO client, String nameText, String emailText, String addressText) {
        return matchesName(client, nameText) && matchesEmail(client, emailText) && matchesAddress(client, addressText);
    }

    /**
     * Checks if a client's name matches the provided name text.
     *
     * @param client the client to check
     * @param nameText the name text to match
     * @return true if the client's name matches the name text; false otherwise
     */
    private boolean matchesName(ClientDTO client, String nameText) {
        return nameText.isEmpty() || (client.getNombre_apellidos() != null && client.getNombre_apellidos().toLowerCase().contains(nameText));
    }

    /**
     * Checks if a client's email matches the provided email text.
     *
     * @param client the client to check
     * @param emailText the email text to match
     * @return true if the client's email matches the email text; false otherwise
     */
    private boolean matchesEmail(ClientDTO client, String emailText) {
        return emailText.isEmpty() || (client.getEmail() != null && client.getEmail().toLowerCase().contains(emailText));
    }

    /**
     * Checks if a client's address matches the provided address text.
     *
     * @param client the client to check
     * @param addressText the address text to match
     * @return true if the client's address matches the address text; false otherwise
     */
    private boolean matchesAddress(ClientDTO client, String addressText) {
        return addressText.isEmpty() || (client.getDireccion() != null && client.getDireccion().toLowerCase().contains(addressText));
    }

    /**
     * Gets the trimmed and lowercased text from a TextField.
     *
     * @param textField the TextField to get the text from
     * @return the trimmed and lowercased text from the TextField
     */
    private String getTrimmedText(TextField textField) {
        return textField.getText() != null ? textField.getText().trim().toLowerCase() : "";
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
            AlertHelper.showNoObjectSelected(NO_CLIENT_SELECTED);
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
    @FXML
    private void clearTextFields() {
        selectedClient = null;
        clientId.setText("");
        clientEmail.setText("");
        clientName.setText("");
        clientAddress.setText("");
    }
}
