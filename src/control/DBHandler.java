package control;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.Artist;
import model.Composition;
import model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public abstract class DBHandler {

    private static final String DB_CONFIG = ResourceBundle.getBundle("configurations\\config").getString("db.url");

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        return DriverManager.getConnection(DB_CONFIG);
    }

    public static List<Composition> getCompositions(final String query) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        List<Composition> compositions = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String artist = resultSet.getString("artist");
            String album = resultSet.getString("album");
            int length = resultSet.getInt("length");
            String year = resultSet.getString("year");
            String genre = resultSet.getString("genre");
            String addDate = resultSet.getString("addDate");
            compositions.add(new Composition(id, name, artist, album, length, year, genre, addDate));
        }
        return compositions;
    }

    public static List<Artist> getArtists(final String query) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        List<Artist> artists = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int number = resultSet.getInt("number");
            artists.add(new Artist(id, new SimpleStringProperty(name), new SimpleIntegerProperty(number)));
        }
        return artists;
    }

    public static List<Genre> getGenres(final String query) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        List<Genre> genres = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int number = resultSet.getInt("number");
            genres.add(new Genre(id, new SimpleStringProperty(name), new SimpleIntegerProperty(number)));
        }
        return genres;
    }

    public static void deleteComposition(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(FileHandler.getTextFromFile("sql_requests/deleteComposition.sql"));
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(getCompositions(FileHandler.getTextFromFile("sql_requests\\selectAll.sql")));
    }

}