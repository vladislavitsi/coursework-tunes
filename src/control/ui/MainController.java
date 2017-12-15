package control.ui;

import control.DBHandler;
import control.FileHandler;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Artist;
import model.Composition;
import model.Genre;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class MainController{
    private enum Mode{
        TRACKS {
            @Override
            public void load(final MainController controller) throws SQLException, ClassNotFoundException {
                load(controller, FileHandler.getTextFromFile("sql_requests\\selectCompositions.sql"));
            }

            public void load(final MainController controller, String sqlRequest) throws SQLException, ClassNotFoundException {
                TableView<Composition> table = (TableView<Composition>) controller.tableView;

                table.setItems(FXCollections.observableList(DBHandler.getCompositions(sqlRequest)));

                TableColumn<Composition, String> nameColumn = new TableColumn<>("Name");
                TableColumn<Composition, String> artistColumn = new TableColumn<>("Artist");
                TableColumn<Composition, String> albumColumn = new TableColumn<>("Album");
                TableColumn<Composition, String> lengthColumn = new TableColumn<>("Length");
                TableColumn<Composition, String> yearColumn = new TableColumn<>("Year");
                TableColumn<Composition, String> genreColumn = new TableColumn<>("Genre");
                TableColumn<Composition, String> addDateColumn = new TableColumn<>("Add Date");

                controller.tableView.getColumns().addAll(nameColumn, artistColumn, albumColumn, lengthColumn, yearColumn, genreColumn, addDateColumn);

                nameColumn.setCellValueFactory(cellDate -> cellDate.getValue().nameProperty());
                artistColumn.setCellValueFactory(cellDate -> cellDate.getValue().artistProperty());
                albumColumn.setCellValueFactory(cellDate -> cellDate.getValue().albumProperty());
                lengthColumn.setCellValueFactory(cellDate -> cellDate.getValue().lengthProperty().asString());
                yearColumn.setCellValueFactory(cellDate -> cellDate.getValue().yearProperty());
                genreColumn.setCellValueFactory(cellDate -> cellDate.getValue().genreProperty());
                addDateColumn.setCellValueFactory(cellDate -> cellDate.getValue().addDateProperty());

                controller.tracksButton.setDisable(true);
                controller.actionButton.setText("Delete");
                controller.actionButton.setOnAction(event -> controller.deleteButton());
            }

            @Override
            protected void add(MainController controller, Stage stage, AddComponentController componentController) throws IOException, SQLException, ClassNotFoundException {
                TextField nameField = new TextField();
                ChoiceBox<Artist> artistChoiceBox = new ChoiceBox<>(FXCollections.observableList(DBHandler.getArtists(FileHandler.getTextFromFile("sql_requests\\selectArtists.sql"))));
                TextField albumField = new TextField();
                TextField lengthField = new TextField();
                TextField yearField = new TextField();
                ChoiceBox<Genre> genreFiled = new ChoiceBox<>(FXCollections.observableList(DBHandler.getGenres(FileHandler.getTextFromFile("sql_requests\\selectGenres.sql"))));

                componentController.getAddGrid().addRow(0, new Label("Name: "), nameField);
                componentController.getAddGrid().addRow(1, new Label("Artist: "), artistChoiceBox);
                componentController.getAddGrid().addRow(2, new Label("Album: "), albumField);
                componentController.getAddGrid().addRow(3, new Label("Length (in sec): "), lengthField);
                componentController.getAddGrid().addRow(4, new Label("Year: "), yearField);
                componentController.getAddGrid().addRow(5, new Label("Genre: "), genreFiled);

                componentController.getCancelButton().setOnAction(event -> {
                    stage.close();
                });

                componentController.getAddButton().setOnAction(event -> {
                    try {
                        String name = nameField.getText();
                        int artist = artistChoiceBox.getValue().getId();
                        String album = albumField.getText();
                        int length = Integer.parseInt(lengthField.getText());
                        String year = yearField.getText();
                        int genre = genreFiled.getValue().getId();
                        Date addDate = new Date();
                        DBHandler.insertComposition(name, artist, album, length, year, genre, addDate);
                        stage.close();
                        controller.updateTable();
                    } catch (NullPointerException | SQLException | ClassNotFoundException | NumberFormatException e){
                        new ErrorMessage("Something went wrong.");
                    }
                });
                stage.showAndWait();
            }
        },
        ARTISTS {
            @Override
            public void load(final MainController controller) throws SQLException, ClassNotFoundException {
                load(controller, FileHandler.getTextFromFile("sql_requests\\selectArtists.sql"));
            }

            @Override
            protected void load(MainController controller, String sqlRequest) throws SQLException, ClassNotFoundException {
                TableView<Artist> table = (TableView<Artist>) controller.tableView;

                table.setItems(FXCollections.observableList(DBHandler.getArtists(sqlRequest)));

                TableColumn<Artist, String> nameColumn = new TableColumn<>("Artist Name");
                TableColumn<Artist, String> numberColumn = new TableColumn<>("Number of Tracks");

                controller.tableView.getColumns().addAll(nameColumn, numberColumn);

                nameColumn.setCellValueFactory(cellDate -> cellDate.getValue().nameProperty());
                numberColumn.setCellValueFactory(cellDate -> cellDate.getValue().counterProperty().asString());

                controller.artistsButton.setDisable(true);
                controller.actionButton.setText("Show tracks");
                controller.actionButton.setOnAction(event -> controller.showTracksButton());
            }

            @Override
            protected void add(MainController controller, Stage stage, AddComponentController componentController) throws IOException, SQLException, ClassNotFoundException {
                TextField nameField = new TextField();

                componentController.getAddGrid().addRow(0, new Label("Name: "), nameField);

                componentController.getCancelButton().setOnAction(event -> {
                    stage.close();
                });

                componentController.getAddButton().setOnAction(event -> {
                    try {
                        String name = nameField.getText();
                        DBHandler.insertArtist(name);
                        stage.close();
                        controller.updateTable();
                    } catch (SQLException | ClassNotFoundException | NumberFormatException e){
                        new ErrorMessage("Something went wrong.");
                    }
                });
                stage.showAndWait();
            }


        },
        GENRES {
            @Override
            public void load(final MainController controller) throws SQLException, ClassNotFoundException {
                load(controller, FileHandler.getTextFromFile("sql_requests\\selectGenres.sql"));
            }

            @Override
            protected void load(MainController controller, String sqlRequest) throws SQLException, ClassNotFoundException {
                TableView<Genre> table = (TableView<Genre>) controller.tableView;

                table.setItems(FXCollections.observableList(DBHandler.getGenres(sqlRequest)));

                TableColumn<Genre, String> nameColumn = new TableColumn<>("Genre");
                TableColumn<Genre, String> numberColumn = new TableColumn<>("Number of Tracks");

                controller.tableView.getColumns().addAll(nameColumn, numberColumn);

                nameColumn.setCellValueFactory(cellDate -> cellDate.getValue().nameProperty());
                numberColumn.setCellValueFactory(cellDate -> cellDate.getValue().counterProperty().asString());

                controller.genresButton.setDisable(true);
                controller.actionButton.setText("Show tracks");
                controller.actionButton.setOnAction(event -> controller.showTracksButton());
            }

            @Override
            protected void add(MainController controller, Stage stage, AddComponentController componentController) throws IOException, SQLException, ClassNotFoundException {
                TextField nameField = new TextField();

                componentController.getAddGrid().addRow(0, new Label("Name: "), nameField);

                componentController.getCancelButton().setOnAction(event -> {
                    stage.close();
                });

                componentController.getAddButton().setOnAction(event -> {
                    try {
                        String name = nameField.getText();
                        DBHandler.insertGenre(name);
                        stage.close();
                        controller.updateTable();
                    } catch (SQLException | ClassNotFoundException | NumberFormatException e){
                        new ErrorMessage("Something went wrong.");
                    }
                });
                stage.showAndWait();
            }


        };

        public void loadTableView(final MainController controller) throws SQLException, ClassNotFoundException{
            controller.tableView.getItems().clear();
            controller.tableView.getColumns().clear();
            controller.uncheckButtons();
            load(controller);
        }

        public void loadTableView(final MainController controller, final String sqlRequest) throws SQLException, ClassNotFoundException{
            controller.tableView.getItems().clear();
            controller.tableView.getColumns().clear();
            controller.uncheckButtons();
            load(controller, sqlRequest);
        }

        protected abstract void load(final MainController controller) throws SQLException, ClassNotFoundException;
        protected abstract void load(final MainController controller, final String sqlRequest) throws SQLException, ClassNotFoundException;

        void addNewElement(final MainController controller) throws SQLException, IOException, ClassNotFoundException {
            Stage stage = new Stage();
            stage.setTitle("Add new");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/addComponent.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(controller.tableView.getScene().getWindow());
            AddComponentController componentController = loader.getController();
            add(controller, stage, componentController);
        }

        protected abstract void add(final MainController controller, final Stage addStage, final  AddComponentController componentController) throws IOException, SQLException, ClassNotFoundException;
    }

    @FXML
    private TableView tableView;
    @FXML
    private Button actionButton;
    @FXML
    private Button tracksButton;
    @FXML
    private Button artistsButton;
    @FXML
    private Button genresButton;
    @FXML
    private Label itemsCounter;

    private Object selectedObject;
    private Mode mode;

    private void deleteButton() {
        if(selectedObject!=null && selectedObject instanceof Composition){
            try {
                Composition compositionToDelete = (Composition) selectedObject;

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Are you sure with deletion");
                alert.setContentText("Composition: "+compositionToDelete.getName()+"-"+compositionToDelete.getArtist()+"?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    DBHandler.deleteComposition(compositionToDelete.getId());
                    tableView.getItems().remove(compositionToDelete);
                    recountItems();
                    selectedObject = null;
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void uncheckButtons(){
        tracksButton.setDisable(false);
        genresButton.setDisable(false);
        artistsButton.setDisable(false);
    }

    private void showTracksButton(){
        mode = Mode.TRACKS;
        String table = "";
        int id = 0;
        if(selectedObject instanceof Genre){
            table = "genres";
            id = ((Genre) selectedObject).getId();
        }else if (selectedObject instanceof Artist){
            table = "artists";
            id = ((Artist) selectedObject).getId();
        }
        updateTable(FileHandler.getTextFromFile("sql_requests\\selectCompositions.sql").replaceAll(";","")+" WHERE "+table+".id = "+id+";");
        uncheckButtons();
    }

    @FXML
    void addButtonAction() {
        try {
            mode.addNewElement(this);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void artistsButtonAction() {
        mode = Mode.ARTISTS;
        updateTable();
    }
    @FXML
    void genresButtonAction() {
        mode = Mode.GENRES;
        updateTable();
    }
    @FXML
    void tracksButtonAction() {
        mode = Mode.TRACKS;
        updateTable();
    }
    @FXML
    public void initialize(){
        mode = Mode.TRACKS;
        updateTable();
        tableView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    selectedObject = newValue;
                });
    }

    private void recountItems(){
        itemsCounter.setText(String.valueOf(tableView.getItems().size()));
    }

    private void updateTable(String sqlRequest) {
        try {
            mode.loadTableView(this, sqlRequest);
            recountItems();
        } catch (SQLException | ClassNotFoundException e) {
            new ErrorMessage("Error while update data");
        }
    }
    private void updateTable() {
        try {
            mode.loadTableView(this);
            recountItems();
        } catch (SQLException | ClassNotFoundException e) {
            new ErrorMessage("Error while update data");
        }
    }

}
