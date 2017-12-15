package control.ui;

import javafx.scene.control.Alert;

public class ErrorMessage extends Alert {

    public ErrorMessage(String message) {
        super(AlertType.ERROR);
        setTitle("Error");
        setContentText(message);
        showAndWait();
    }
}
