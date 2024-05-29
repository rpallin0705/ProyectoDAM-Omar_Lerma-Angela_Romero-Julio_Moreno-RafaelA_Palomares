package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.example.proyecto.model.client.ClientDB;
import org.example.proyecto.model.client.ClientDTO;


import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public TableColumn<ClientDTO,String> clientAddressColumn;
    @FXML
    public Button selectClientButton;
    @FXML
    public Button searchImageButton;
    @FXML
    AnchorPane templateComponent = null;

    private List<ClientDTO> clientList = null;
    private ClientDTO selectedClient = null;
    private boolean isSelectingClient = false;

    @FXML
    public void initialize(){
        try {


            Image image = new Image("https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200");
            ImageView imageView = new ImageView(image);
            searchImageButton.setGraphic(imageView);


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

    public void setTemplateComponent(AnchorPane templateComponent){
        this.templateComponent = templateComponent;
    }

    public void setIsSelectingClient(boolean isSelectingClient) {
        this.isSelectingClient = isSelectingClient;
        selectClientButton.setVisible(isSelectingClient);
    }

    @FXML
    public void updateClient(ActionEvent actionEvent) throws SQLException, IOException {
        if (selectedClient == null) {
            AlertHelper.showNoUserSelectedAlert();
            return;
        } else if (clientName.getText().isBlank() || clientEmail.getText().isBlank() || clientAddress.getText().isBlank()) {
            AlertHelper.showMissingDataAlert();
            return;
        }

        ClientDTO updatedClient = new ClientDTO(selectedClient.getId_cuenta(), selectedClient.getEmail(), selectedClient.getNombre_apellidos(), selectedClient.getDireccion());

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
        if (AlertHelper.showConfirmationDialog("Confirmación de registro", "¿Desea registrar al cliente de la base de datos?")) {
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



    private void clearTextFields() {
        selectedClient = null;
        clientId.setText("");
        clientEmail.setText("");
        clientName.setText("");
        clientAddress.setText("");
    }

   @FXML
   public void searchClient(ActionEvent actionEvent) throws SQLException, IOException {
       List<ClientDTO> resultList = new ArrayList<>();

       if (selectedClient != null){
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

    @FXML
    public void selectClientForBooking(ActionEvent actionEvent) {
        if (selectedClient == null) {
            AlertHelper.showNoUserSelectedAlert();
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("booking-list.fxml"));
            AnchorPane menu = loader.load();

            BookingListController controller = loader.getController();
            controller.setTemplateComponent(templateComponent);
            controller.setClientForBooking(selectedClient);
            controller.setSelectBookingCLientButton();
            templateComponent.getChildren().setAll(menu);
        } catch (IOException e) {
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
}
