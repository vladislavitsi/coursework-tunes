package control;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.Artist;
import model.Composition;
import model.Genre;

import java.sql.*;
import java.util.*;
import java.util.Date;

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

    public static void insertArtist(String name) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(FileHandler.getTextFromFile("sql_requests/insertArtist.sql"));
        preparedStatement.setString(1, name);
        preparedStatement.execute();
    }

    public static void insertGenre(String name) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(FileHandler.getTextFromFile("sql_requests/insertGenre.sql"));
        preparedStatement.setString(1, name);
        preparedStatement.execute();
    }

    public static void insertComposition(String name, int artist, String album, int length, String year, int genre, Date addDate) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(FileHandler.getTextFromFile("sql_requests/insertComposition.sql"));
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, album);
        preparedStatement.setInt(3, length);
        preparedStatement.setString(4, year);
        preparedStatement.setInt(5, genre);
        preparedStatement.setInt(6, artist);
        preparedStatement.setString(7, Composition.SIMPLE_DATE_FORMAT.format(addDate));
        preparedStatement.execute();
    }
}