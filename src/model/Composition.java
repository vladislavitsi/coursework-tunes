package model;

import javafx.beans.property.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Composition {
    private StringProperty name;
    private StringProperty artist;
    private StringProperty album;
    private IntegerProperty length;
    private StringProperty year;
    private StringProperty genre;
    private ObjectProperty<Date> addDate;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public Composition(String name, String artist, String album, int length, String year, String genre, String date) {
        this.name = new SimpleStringProperty(name);
        this.artist = new SimpleStringProperty(artist);
        this.album = new SimpleStringProperty(album);
        this.length = new SimpleIntegerProperty(length);
        this.year = new SimpleStringProperty(year);
        this.genre = new SimpleStringProperty(genre);
        try {
            this.addDate = new SimpleObjectProperty<>(simpleDateFormat.parse(date));
        } catch (ParseException e) {
            this.addDate = null;
        }
    }

    public Composition() {
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getArtist() {
        return artist.get();
    }

    public StringProperty artistProperty() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist.set(artist);
    }

    public String getAlbum() {
        return album.get();
    }

    public StringProperty albumProperty() {
        return album;
    }

    public void setAlbum(String album) {
        this.album.set(album);
    }

    public int getLength() {
        return length.get();
    }

    public IntegerProperty lengthProperty() {
        return length;
    }

    public void setLength(int length) {
        this.length.set(length);
    }

    public String getYear() {
        return year.get();
    }

    public StringProperty yearProperty() {
        return year;
    }

    public void setYear(String year) {
        this.year.set(year);
    }

    public String getGenre() {
        return genre.get();
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Composition)) return false;
        Composition that = (Composition) o;
        return (name != null ? name.equals(that.name) : that.name == null)
                && (artist != null ? artist.equals(that.artist) : that.artist == null)
                && (album != null ? album.equals(that.album) : that.album == null);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        result = 31 * result + (album != null ? album.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Composition{" +
                "name=" + name +
                ", artist=" + artist +
                ", album=" + album +
                ", length=" + length +
                ", year=" + year +
                ", genre=" + genre +
                ", addDate=" + simpleDateFormat.format(addDate.getValue()) +
                '}';
    }

    public Date getAddDate() {
        return addDate.get();
    }

    public ObjectProperty<Date> addDateProperty() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate.set(addDate);
    }
}
