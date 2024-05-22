package org.example.proyecto.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuComponent {
    @FXML
    public Button createButton;
    @FXML
    public Button updateButton;
    @FXML
    public Button deleteButton;
    @FXML
    public Button getButton;

    public void initialize(String buttonPressed) {

        createButton.setText("Crear " + buttonPressed);
        updateButton.setText("Modificar " + buttonPressed);
        deleteButton.setText("Borrar " + buttonPressed);
        getButton.setText("Buscar " + buttonPressed);

        switch (buttonPressed){
            case "Clientes" -> {
                /*createButton.setText("Crear Clientes");
                updateButton.setText("Modificar Clientes");
                deleteButton.setText("Borrar Clientes");
                getButton.setText("Buscar Clientes");*/

                createButton.setOnAction(event -> createClient());
                updateButton.setOnAction(event -> updateClient());
                deleteButton.setOnAction(event -> deleteClient());
                getButton.setOnAction(event -> getClients());

            }
            case "Reservas" -> {
                /*createButton.setText("Crear Reservas");
                updateButton.setText("Modificar Reservas");
                deleteButton.setText("Borrar Reservas");
                getButton.setText("Buscar Reservas");*/

                createButton.setOnAction(event -> createBooking());
                updateButton.setOnAction(event -> updateBooking());
                deleteButton.setOnAction(event -> deleteBooking());
                getButton.setOnAction(event -> getBooking());
            }

            case "Hoteles" -> {
                /*createButton.setText("Crear Hoteles");
                updateButton.setText("Modificar Hoteles");
                deleteButton.setText("Borrar Hoteles");
                getButton.setText("Buscar Hoteles");*/

                createButton.setOnAction(event -> createHotel());
                updateButton.setOnAction(event -> updateHotel());
                deleteButton.setOnAction(event -> deleteHotel());
                getButton.setOnAction(event -> getHotels());
            }

            case "Apartamentos" -> {
                /*createButton.setText("Crear Hoteles");
                updateButton.setText("Modificar Hoteles");
                deleteButton.setText("Borrar Hoteles");
                getButton.setText("Buscar Hoteles");*/

                createButton.setOnAction(event -> createApartment());
                updateButton.setOnAction(event -> updateApartment());
                deleteButton.setOnAction(event -> deleteApartment());
                getButton.setOnAction(event -> getApartments());
            }

            default -> { throw new IllegalStateException("El botón presionado no funciona correctamente" + buttonPressed); }
        }
    }

    private void createApartment() {
    }

    private void updateApartment() {
    }

    private void getApartments() {
    }

    private void deleteApartment() {
    }

    private void getHotels() {
    }

    private void deleteHotel() {
    }

    private void updateHotel() {
    }

    private void createHotel() {
    }

    private void getBooking() {
    }

    private void deleteBooking() {
    }

    private void updateBooking() {
    }

    private void createBooking() {
    }

    private void getClients() {
    }

    private void deleteClient() {
    }

    private void updateClient() {
    }

    private void createClient() {
    }
}
