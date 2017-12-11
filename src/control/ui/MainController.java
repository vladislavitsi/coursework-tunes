package control.ui;

import control.DBHandler;
import control.FileHandler;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Composition;

import java.sql.SQLException;
import java.util.Date;

public class MainController{

    @FXML
    private TableView<Composition> compositionTable;

    @FXML
    private TableColumn<Composition, String> nameColumn;

    @FXML
    private TableColumn<Composition, String> artistColumn;

    @FXML
    private TableColumn<Composition, String> albumColumn;

    @FXML
    private TableColumn<Composition, String> lengthColumn;

    @FXML
    private TableColumn<Composition, String> yearColumn;

    @FXML
    private TableColumn<Composition, String> genreColumn;

    @FXML
    private TableColumn<Composition, Date> addDataColumn;

    @FXML
    public void initialize(){
        try {
            compositionTable.setItems(FXCollections.observableList(DBHandler.getCompositions(FileHandler.getTextFromFile("sql_requests\\selectAll.sql"))));
            nameColumn.setCellValueFactory(cellDate -> cellDate.getValue().nameProperty());
            artistColumn.setCellValueFactory(cellDate -> cellDate.getValue().artistProperty());
            albumColumn.setCellValueFactory(cellDate -> cellDate.getValue().albumProperty());
//            lengthColumn.setCellValueFactory(cellDate -> cellDate.getValue().lengthProperty());
            yearColumn.setCellValueFactory(cellDate -> cellDate.getValue().yearProperty());
            genreColumn.setCellValueFactory(cellDate -> cellDate.getValue().genreProperty());
            addDataColumn.setCellValueFactory(cellDate -> cellDate.getValue().addDateProperty());
        } catch (SQLException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SWW");
//            alert.setHeaderText(header);
//            alert.setContentText(content);
            alert.showAndWait();
        }
    }

}
