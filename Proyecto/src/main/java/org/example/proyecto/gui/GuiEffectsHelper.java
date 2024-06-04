package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiEffectsHelper {

    // Variable for tracking selected button
    private static Button selectedButton = null;

    // Map to save the original state of buttons
    private static final Map<Button, EventHandler<ActionEvent>> originalEventHandlers = new HashMap<>();

    // Private constructor to hide the implicit public one
    private GuiEffectsHelper() {}

    /**
     * Adds hover effect to a list of buttons and manages button states.
     * @param buttons The list of buttons to which hover effect will be added and button states will be managed.
     */
    public static void addHoverEffectOnButtons(List<Button> buttons) {
        for (Button button : buttons) {
            // Save original event handlers
            saveOriginalEventHandlers(button);

            // Set hover effect
            setHoverEffect(button);
        }
    }

    // Saves the original event handlers of a button
    private static void saveOriginalEventHandlers(Button button) {
        originalEventHandlers.put(button, button.getOnAction());
    }

    // Sets hover effect on a button
    private static void setHoverEffect(Button button) {
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
            // Calls the original event handler
            EventHandler<ActionEvent> originalHandler = originalEventHandlers.get(button);
            if (originalHandler != null) {
                originalHandler.handle(event);
            }
        });
    }

    // Resets the style of the selected button to its default state
    private static void resetButtonStyle(Button button) {
        button.setStyle("-fx-background-color: #ddd; -fx-font-size: 1.0em; -fx-background-radius: 30;");
    }
}
