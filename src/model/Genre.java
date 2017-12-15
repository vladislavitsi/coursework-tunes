package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Genre {
    private int id;
    private StringProperty name;
    private IntegerProperty counter;

    public Genre(int id, StringProperty name, IntegerProperty counter) {
        this.id = id;
        this.name = name;
        this.counter = counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCounter() {
        return counter.get();
    }

    public IntegerProperty counterProperty() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter.set(counter);
    }

    @Override
    public String toString() {
        return name.getValue();
    }
}
