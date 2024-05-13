package org.example.proyecto.gui;

import javafx.scene.control.Button;

import java.util.List;

public class GuiEffectsHelper {
    // Variable to track the selected button
    private static Button selectedButton = null;

    // Private Constructor to hide the implicit public one
    private GuiEffectsHelper() {}


    /**
     * This method adds an event listener on the selected buttons to implement a hover effect on them.
     * Buttons will change its color to orange and increase its size lightly when the mouse is over them and
     * get back to the default state when the mouse is existed. Also, if the button is selected it will be permanently
     * highlighted until other button is selected.
     * @param buttons List of buttons to add the hover effects
     */
    public static void addHoverEffectOnButtons(List<Button> buttons) {
        for (Button button : buttons) {
            button.setOnMouseEntered(event -> {
                if (button != selectedButton) {
                    button.setStyle("-fx-background-color:  #f5a623; -fx-font-size: 1.1em; -fx-background-radius: 30;");
                }
            });

            button.setOnMouseExited(event -> {
                if (button != selectedButton) {
                    button.setStyle("-fx-background-color: #ddd; -fx-font-size: 1.0em; -fx-background-radius: 30;");
                }
            });

            button.setOnAction(event -> {
                if (button != selectedButton) {
                    if (selectedButton != null) {
                        resetButtonStyle(selectedButton);
                    }
                    selectedButton = button;
                    button.setStyle("-fx-background-color:  #f5a623; -fx-font-size: 1.1em; -fx-background-radius: 30;");
                }
            });
        }
    }

    /**
     * Resets the style of the selected button to its default state
     * @param button Button which style will be reset to default
     */
    private static void resetButtonStyle(Button button) {
        button.setStyle("-fx-background-color: #ddd; -fx-font-size: 1.0em; -fx-background-radius: 30;");
    }
}
