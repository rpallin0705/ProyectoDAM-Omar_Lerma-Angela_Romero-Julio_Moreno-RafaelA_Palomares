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
        ClientDTO updatedClient = new ClientDTO(selectedClient);
        updatedClient.setNombre_apellidos(clientName.getText());
        updatedClient.setEmail(clientEmail.getText());
        updatedClient.setDireccion(clientAddress.getText());
        ClientDB clientDB = new ClientDB();

        System.out.println(updatedClient.getNombre_apellidos());
        System.out.println(selectedClient.getNombre_apellidos());
        if (selectedClient.equals(updatedClient)) {
            System.out.println("No has cambiado la información del usuario");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación de Actualización");
            alert.setHeaderText("Confirmar actualización de cliente");
            alert.setContentText("¿Deseas actualizar la información del cliente?");

            ButtonType buttonTypeYes = new ButtonType("Sí");
            ButtonType buttonTypeCancel = new ButtonType("Cancelar");

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

            alert.showAndWait().ifPresent(response -> {
                if (response == buttonTypeYes) {
                    // Realiza la operación de actualización
                   /* try {
                        clientDB.updateClient(updatedClient);
                        setClientList();
                        clientDataTable.getItems().setAll(clientList);
                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }*/
                    System.out.println(updatedClient.toString());
                } else {
                    // No se realiza la operación de actualización
                    System.out.println("Operación de actualización cancelada");
                }
            });
        }
    }
    @FXML
    public void deleteClient(ActionEvent actionEvent) {
    }
}
