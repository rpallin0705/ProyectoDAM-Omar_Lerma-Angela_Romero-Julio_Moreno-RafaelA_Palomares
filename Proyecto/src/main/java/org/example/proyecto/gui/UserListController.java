package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.proyecto.model.user.UserDB;
import org.example.proyecto.model.user.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserListController {
    @FXML
    public TableView<UserDTO> userDataTable;
    @FXML
    public TableColumn<UserDTO, Integer> userIdColumn;
    @FXML
    public TableColumn<UserDTO, String> userEmailColumn;
    @FXML
    public TableColumn<UserDTO, String> userNameColumn;
    @FXML
    public TableColumn<UserDTO, Boolean> isAdminColumn;
    @FXML
    public TableColumn<UserDTO, String> userPasswordColumn;
    @FXML
    public TextField userEmail;
    @FXML
    public TextField userName;
    @FXML
    public Label userIdLabel;
    @FXML
    public CheckBox isAdmin;
    @FXML
    public PasswordField userPassword;



    private List<UserDTO> userList = null;
    private UserDTO selectedUser = null;


    @FXML
    public void initialize() {
        try {
            userIdColumn.setCellValueFactory(new PropertyValueFactory<>("id_cuenta"));
            userEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            userNameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre_apellidos"));
            isAdminColumn.setCellValueFactory(new PropertyValueFactory<>("admin"));
            userPasswordColumn.setCellValueFactory(new PropertyValueFactory<>("contrasena"));

            setUserList();
            userDataTable.getItems().setAll(userList);

            userDataTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue!= null) {
                    selectUserDetails(newValue);
                }
            });
        }catch (SQLException | IOException E){
            System.out.println(E.getMessage());
        }

    }

    @FXML
    public void updateUser(ActionEvent actionEvent) {
        if ( selectedUser == null ) {
            AlertHelper.showNoUserSelectedAlert();
            return;
        }else if ( userName.getText().isBlank() || userEmail.getText().isBlank() || userPassword.getText().isBlank() ){
            AlertHelper.showMissingDataAlert();
            return;
        }

        UserDTO updatedUser = new UserDTO( selectedUser.getId_cuenta(), userEmail.getText(), userName.getText(), isAdmin.isSelected(), userPassword.getText() );

        if ( updatedUser.equals(selectedUser) ){
            AlertHelper.showNoChangesAlert();
            return;
        }

        if ( AlertHelper.showConfirmationDialog("Confirmación de Actualización", "¿Desea realizar la actualización de datos?")) {
            try {
                UserDB userDB = new UserDB();
                userDB.updateUser(updatedUser);
                setUserList();
                userDataTable.getItems().setAll(userList);
                clearTextFields();
            }catch ( SQLException | IOException E){
                System.out.println(E.getMessage());
            }
        }
    }

    @FXML
    public void registerUser(ActionEvent actionEvent) {
        if (selectedUser != null) {
            clearTextFields();
            return;
        }

        if ( userName.getText().isBlank() || userEmail.getText().isBlank() || userPassword.getText().isBlank() ){
            AlertHelper.showMissingDataAlert();
            return;
        }

        UserDTO newUser = new UserDTO( userEmail.getText(), userName.getText(), isAdmin.isSelected(), userPassword.getText() );

        if ( AlertHelper.showConfirmationDialog("Confirmación de Registro", "¿Desea realizar el registro?")) {
            try {
                UserDB userDB = new UserDB();
                userDB.insertUser(newUser);
                setUserList();
                userDataTable.getItems().setAll(userList);
                clearTextFields();
            }catch ( SQLException | IOException E){
                System.out.println(E.getMessage());
            }
        }
    }

    @FXML
    public void deleteUser(ActionEvent actionEvent) {
        if (selectedUser == null) {
            AlertHelper.showNoUserSelectedAlert();
            return;
        }

        UserDTO userToDelete = new UserDTO(selectedUser);

        if (AlertHelper.showConfirmationDialog("Confirmación de eliminación", "¿Desea eliminar al usuario de la base de datos?")) {
            try{
                UserDB userDB = new UserDB();
                userDB.deleteUser(userToDelete);
                setUserList();
                userDataTable.getItems().setAll(userList);
                clearTextFields();
            }catch ( SQLException | IOException E){
                System.out.println(E.getMessage());
            }
        }
    }

    @FXML
    public void searchClient() throws SQLException, IOException {
        List<UserDTO> resultList = new ArrayList<>();

        if (selectedUser != null) {
            clearTextFields();
            setUserList();
            userDataTable.getItems().setAll(userList);
            return;
        }

        String nameText = userName.getText() != null ? userName.getText().trim().toLowerCase() : "";
        String emailText = userEmail.getText() != null ? userEmail.getText().trim().toLowerCase() : "";

        for (UserDTO user : userList) {
            boolean matches = true;

            if (!nameText.isEmpty()) {
                matches &= user.getNombre_apellidos() != null && user.getNombre_apellidos().toLowerCase().contains(nameText);
            }
            if (!emailText.isEmpty()) {
                matches &= user.getEmail() != null && user.getEmail().toLowerCase().contains(emailText);
            }
            if (isAdmin.isSelected()) {
                matches &= user.isAdmin();
            }

            if (matches) {
                resultList.add(user);
            }
        }

        userDataTable.getItems().setAll(resultList);
    }


    private void selectUserDetails(UserDTO selectedUser) {
        this.selectedUser = selectedUser;
        userIdLabel.setText(String.valueOf(selectedUser.getId_cuenta()));
        userEmail.setText(selectedUser.getEmail());
        userName.setText(selectedUser.getNombre_apellidos());
        isAdmin.setSelected(selectedUser.isAdmin());
        userPassword.setText(selectedUser.getContrasena());
    }

    private void setUserList() throws SQLException, IOException {
        UserDB userDB = new UserDB();
        this.userList = userDB.getUsers();
    }

    private void clearTextFields() {
        selectedUser = null;
        userIdLabel.setText("Id Usuario");
        userEmail.setText("");
        userName.setText("");
        isAdmin.setSelected(false);
        userPassword.setText("");
    }
}
