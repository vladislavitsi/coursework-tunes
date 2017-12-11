package control;

import model.Composition;

import java.sql.*;
import java.text.SimpleDateFormat;
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
            String name = resultSet.getString("name");
            String artist = resultSet.getString("artist");
            String album = resultSet.getString("album");
            int length = resultSet.getInt("length");
            String year = resultSet.getString("year");
            String genre = resultSet.getString("genre");
            String addDate = resultSet.getString("addDate");
            compositions.add(new Composition(name, artist, album, length, year, genre, addDate));
        }
        return compositions;
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(getCompositions(FileHandler.getTextFromFile("sql_requests\\selectAll.sql")));

    }

}