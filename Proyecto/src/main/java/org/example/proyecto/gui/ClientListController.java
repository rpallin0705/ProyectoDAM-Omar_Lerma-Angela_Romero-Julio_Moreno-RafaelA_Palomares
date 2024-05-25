package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.proyecto.model.client.ClientDB;
import org.example.proyecto.model.client.ClientDTO;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ClientListController {
    @FXML
    public TextField clientEmail;
    @FXML
    public TextField clientName;
    @FXML
    public TextField clientAddress;
    @FXML
    public TextField clientId;
    @FXML
    public TableView<ClientDTO> clientDataTable;
    @FXML
    public TableColumn<ClientDTO, Integer> clientIdColumn;
    @FXML
    public TableColumn<ClientDTO, String> clientNameColumn;
    @FXML
    public TableColumn<ClientDTO, String> clientEmailColumn;
    @FXML
    public TableColumn<ClientDTO,String> clientAddressColumn;
    private List<ClientDTO> clientList = null;
    private ClientDTO selectedClient = null;

    @FXML
    public void initialize(){
        try {
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

    private void setClientList() throws SQLException, IOException {
        ClientDB clientDB = new ClientDB();
        this.clientList = clientDB.getClients();
    }

    private void selectClientDetails(ClientDTO selectedClient){
        this.selectedClient = selectedClient;
        clientId.setText(String.valueOf(selectedClient.getId_cuenta()));
        clientEmail.setText(selectedClient.getEmail());
        clientName.setText(selectedClient.getNombre_apellidos());
        clientAddress.setText(selectedClient.getDireccion());
    }

    @FXML
    public void updateClientId(ActionEvent actionEvent) throws SQLException, IOException {
        if (selectedClient == null) {
            AlertHelper.showNoUserSelectedAlert();
            return;
        } else if (clientName.getText().isBlank() || clientEmail.getText().isBlank() || clientAddress.getText().isBlank()) {
            AlertHelper.showMissingDataAlert();
            return;
        }

        ClientDTO updatedClient = new ClientDTO(selectedClient);

        updatedClient.setNombre_apellidos(clientName.getText());
        updatedClient.setEmail(clientEmail.getText());
        updatedClient.setDireccion(clientAddress.getText());
        ClientDB clientDB = new ClientDB();

        if (updatedClient.equals(selectedClient)) {
            AlertHelper.showNoChangesAlert();
            return;
        }

        if (AlertHelper.showConfirmationDialog("Confirmación de actualización", "¿Desea realizar la actualización de datos?")) {
            try {
                clientDB.updateClient(updatedClient);
                setClientList();
                clientDataTable.getItems().setAll(clientList);
                selectedClient = updatedClient;
                AlertHelper.showUpdatedUserAlert();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void deleteClient(ActionEvent actionEvent) {
        if (selectedClient == null) {
            AlertHelper.showNoUserSelectedAlert();
            return;
        }

        ClientDTO clientToDelete = new ClientDTO(selectedClient);

        if (AlertHelper.showConfirmationDialog("Confirmación de eliminación", "¿Desea insertar al cliente en la base de datos?")) {
            try {
                ClientDB clientDB = new ClientDB();
                clientDB.deleteClient(clientToDelete);
                setClientList();
                clientDataTable.getItems().setAll(clientList);
                AlertHelper.showDeletedUserAlert();
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void registerClient() {
        if (selectedClient != null) {
            selectedClient = null;
            clientId.setText("");
            clientEmail.setText("");
            clientName.setText("");
            clientAddress.setText("");
            return;
        }

        if (clientName.getText().isBlank() || clientEmail.getText().isBlank() || clientAddress.getText().isBlank()) {
            AlertHelper.showMissingDataAlert();
            return;
        }

        ClientDTO clientToRegister = new ClientDTO(clientEmail.getText(), "", clientName.getText(), clientAddress.getText());
        if (AlertHelper.showConfirmationDialog("Confirmación de registro", "¿Desea eliminar al cliente de la base de datos?")) {
            try {
                ClientDB clientDB = new ClientDB();
                clientDB.insertClient(clientToRegister);
                setClientList();
                clientDataTable.getItems().setAll(clientList);
                AlertHelper.showInsertedUserAlert();
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void getClientId(ActionEvent actionEvent) {
    }
}
