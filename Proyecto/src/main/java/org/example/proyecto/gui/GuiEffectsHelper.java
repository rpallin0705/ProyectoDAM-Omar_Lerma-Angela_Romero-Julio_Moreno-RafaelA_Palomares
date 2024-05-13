package org.example.proyecto.gui;

import javafx.scene.control.Button;

import java.util.List;

public class GuiEffectsHelper {
    public static void addHoverEffectOnButtons(List<Button> buttons) {
        for (Button button : buttons) {
            button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: orange; -fx-font-size: 1.1em; -fx-background-radius: 30"));

            button.setOnMouseExited(event -> button.setStyle("-fx-background-color: #ddd; -fx-font-size: 1.0em; -fx-background-radius: 30;"));
        }
    }
}
