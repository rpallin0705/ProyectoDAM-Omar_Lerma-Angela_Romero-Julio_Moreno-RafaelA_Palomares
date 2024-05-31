package org.example.proyecto.gui.helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertHelper {

    private AlertHelper() {}

    public static boolean showConfirmationDialog(String alertTitle, String alertContentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(alertTitle);
        alert.setHeaderText(null);
        alert.setContentText(alertContentText);

        ButtonType buttonTypeYes = new ButtonType("Sí");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == buttonTypeYes;
    }

    public static boolean showHousingSelectionAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Seleccione el tipo de alojamiento");
        alert.setHeaderText(null);
        alert.setContentText("Elija el tipo de alojamiento:");

        ButtonType hotelButton = new ButtonType("Hotel");
        ButtonType apartmentButton = new ButtonType("Apartamento");

        alert.getButtonTypes().setAll(hotelButton, apartmentButton);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == hotelButton;
    }

    public static void showNoObjectSelected(String contentText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static void showNoChangesAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText("No se ha cambiado la información");
        alert.showAndWait();
    }

    public static void showMissingDataAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Información incompleta");
        alert.setHeaderText(null);
        alert.setContentText("Asegúrese de que rellena todos los campos.");
        alert.showAndWait();
    }
}
