package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    private List<ClientDTO> clientList = null;

    @FXML
    public void updateClientId(ActionEvent actionEvent) {
    }
    @FXML
    public void deleteClient(ActionEvent actionEvent) {
    }
    @FXML
    public void getClientId(ActionEvent actionEvent) {
    }

    @FXML
    public void initialize(){
        try {
            System.out.println("hola ");
            setClientList();
            for (ClientDTO clientDTO : clientList)
                System.out.println(clientDTO);
            clientDataTable.getItems().setAll(clientList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setClientList() throws SQLException, IOException {
        ClientDB clientDB = new ClientDB();
        this.clientList = clientDB.getClients();
    }
}
