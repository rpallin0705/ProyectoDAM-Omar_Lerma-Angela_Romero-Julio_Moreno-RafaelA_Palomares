package org.example.proyecto.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for adding hover effects and managing button states in a JavaFX application.
 * This class tracks the state of buttons and ensures only one button is highlighted at a time.
 */
public class GuiEffectsHelper {

    // Variable for tracking the currently selected button
    private static Button selectedButton = null;

    // Map to save the original event handlers of buttons
    private static final Map<Button, EventHandler<ActionEvent>> originalEventHandlers = new HashMap<>();

    private static Image searchImage = new Image("https://img.icons8.com/?size=100&id=132&format=png&color=000000");
    private static Image userImage = new Image("https://img.icons8.com/?size=100&id=23264&format=png&color=000000");
    private static Image housingImage = new Image("https://img.icons8.com/?size=100&id=73&format=png&color=000000");
    private static ImageView searchIcon = new ImageView(searchImage);
    private static ImageView userIcon = new ImageView(userImage);
    private static ImageView housingIcon = new ImageView(housingImage);

    // Private constructor to prevent instantiation
    private GuiEffectsHelper() {}

    /**
     * Adds hover effect to a list of buttons and manages button states.
     *
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

    /**
     * Saves the original event handler of a button.
     *
     * @param button The button whose original event handler is to be saved.
     */
    private static void saveOriginalEventHandlers(Button button) {
        originalEventHandlers.put(button, button.getOnAction());
    }

    /**
     * Sets hover effects on a button.
     *
     * @param button The button to which hover effects will be applied.
     */
    private static void setHoverEffect(Button button) {
        button.setOnMouseEntered(event -> {
            if (button != selectedButton) {
                button.setStyle("-fx-background-color: #f5a623; -fx-font-size: 23px;");
            }
        });

        button.setOnMouseExited(event -> {
            if (button != selectedButton) {
                button.setStyle("-fx-background-color: #ddd; -fx-font-size: 20px;");
            }
        });

        button.setOnAction(event -> {
            if (button != selectedButton) {
                if (selectedButton != null) {
                    resetButtonStyle(selectedButton);
                }
                selectedButton = button;
                button.setStyle("-fx-background-color: #f5a623; -fx-font-size: 23px;");
            }
            // Calls the original event handler
            EventHandler<ActionEvent> originalHandler = originalEventHandlers.get(button);
            if (originalHandler != null) {
                originalHandler.handle(event);
            }
        });
    }

    public static ImageView getSearchIcon() {
        searchIcon.setFitWidth(20);
        searchIcon.setFitHeight(20);
        return searchIcon;
    }

    public static ImageView getHousingIcon() {
        housingIcon.setFitHeight(17);
        housingIcon.setFitWidth(17);
        return housingIcon;
    }

    public static ImageView getUserIcon() {
        userIcon.setFitHeight(17);
        userIcon.setFitWidth(17);
        return userIcon;
    }

    /**
     * Resets the style of the given button to its default state.
     *
     * @param button The button whose style is to be reset.
     */
    private static void resetButtonStyle(Button button) {
        button.setStyle("-fx-background-color: #ddd; -fx-font-size: 20px;");
    }
}
