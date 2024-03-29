package model;

import javafx.beans.property.*;

import java.text.SimpleDateFormat;

public class Composition {
    private int id;
    private StringProperty name;
    private StringProperty artist;
    private StringProperty album;
    private ObjectProperty<Length> length;
    private StringProperty year;
    private StringProperty genre;
    private StringProperty addDate;
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public Composition(int id, String name, String artist, String album, int length, String year, String genre, String date) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.artist = new SimpleStringProperty(artist);
        this.album = new SimpleStringProperty(album);
        this.length = new SimpleObjectProperty<>(new Length(length));
        this.year = new SimpleStringProperty(year);
        this.genre = new SimpleStringProperty(genre);
        this.addDate = new SimpleStringProperty(date);
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
        return length.getValue().getLength();
    }

    public ObjectProperty<Length> lengthProperty() {
        return length;
    }

    public void setLength(int length) {
        this.length.set(new Length(length));
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", name=" + name +
                ", artist=" + artist +
                ", album=" + album +
                ", length=" + length +
                ", year=" + year +
                ", genre=" + genre +
                ", addDate=" + addDate +
                '}';
    }

    public String getAddDate() {
        return addDate.get();
    }

    public StringProperty addDateProperty() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate.set(addDate);
    }
}
