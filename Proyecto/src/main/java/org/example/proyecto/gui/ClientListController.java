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
import java.util.Optional;

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
            // Assign every client attribute to each Column of the table
            clientIdColumn.setCellValueFactory(new PropertyValueFactory<>("id_cuenta"));
            clientNameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre_apellidos"));
            clientEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            clientAddressColumn.setCellValueFactory(new PropertyValueFactory<>("direccion"));

            //Gets users from the database
            setClientList();

            //Displays users in the table
            clientDataTable.getItems().setAll(clientList);

            //When a user is selected in the table, the details of the user are displayed in the text fields.
            clientDataTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    selectClientDetails(newValue);
                }
                System.out.println("hola");
            });
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets client data from the database
     * @throws SQLException if an error occurs during query execution
     */
    private void setClientList() throws SQLException, IOException {
        ClientDB clientDB = new ClientDB();
        this.clientList = clientDB.getClients();
    }

    /**
     * When clicking a row on clientDataTable it displays the details of the user in the TextFields
     * @param selectedClient data of the selected client in the table
     */
    private void selectClientDetails(ClientDTO selectedClient){
        this.selectedClient = selectedClient;
        clientId.setText(String.valueOf(selectedClient.getId_cuenta()));
        clientEmail.setText(selectedClient.getEmail());
        clientName.setText(selectedClient.getNombre_apellidos());
        clientAddress.setText(selectedClient.getDireccion());
    }

    @FXML
    public void getClientId(ActionEvent actionEvent) {
    }

    @FXML
    public void updateClientId(ActionEvent actionEvent) throws SQLException, IOException {
        if (selectedClient == null) {
            showNoUserSelectedAlert();
            return;
        }

        ClientDTO updatedClient = new ClientDTO(selectedClient);

        updatedClient.setNombre_apellidos(clientName.getText());
        updatedClient.setEmail(clientEmail.getText());
        updatedClient.setDireccion(clientAddress.getText());
        ClientDB clientDB = new ClientDB();

        if (updatedClient.equals(selectedClient)) {
            showNoChangesAlert();
            return;
        }

        if (showConfirmationDialog()) {
            try {
                clientDB.updateClient(updatedClient);
                setClientList();
                clientDataTable.getItems().setAll(clientList);
                selectedClient = updatedClient;
                showUpdatedUserAlert();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void deleteClient(ActionEvent actionEvent) {

        if (selectedClient == null){
            showNoUserSelectedAlert();
        }
        ClientDTO clientToDelete = new ClientDTO(selectedClient);

        try {
            ClientDB clientDB = new ClientDB();
            clientDB.deleteClient(clientToDelete);
            setClientList();
            clientDataTable.getItems().setAll(clientList);
            if (showConfirmationDialog())
                showDeletedUserAlert();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showDeletedUserAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText("El usuario ha sido borrado correctamente.");
        alert.showAndWait();
    }

    private boolean showConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de Actualización");
        alert.setHeaderText("Confirmar actualización de cliente");
        alert.setContentText("¿Deseas actualizar la información del cliente?");

        ButtonType buttonTypeYes = new ButtonType("Sí");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == buttonTypeYes;
    }



    private static void showNoUserSelectedAlert() {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("No hay ningún usuario seleccionado.");
            alert.showAndWait();
        }

    private static void showNoChangesAlert() {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("No se ha cambiado la información del usuario.");
            alert.showAndWait();
    }

    private static void showUpdatedUserAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText("El cliente ha sido actualizado.");
        alert.showAndWait();
    }

}
