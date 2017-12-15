package control.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class AddComponentController {
    @FXML
    private GridPane addGrid;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    public GridPane getAddGrid() {
        return addGrid;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
}
