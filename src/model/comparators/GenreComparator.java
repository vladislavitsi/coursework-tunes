package model.comparators;

import model.Composition;

import java.util.Comparator;

public class GenreComparator implements Comparator<Composition> {
    @Override
    public int compare(Composition o1, Composition o2) {
        return o1.getGenre().compareTo(o2.getGenre());
    }
}
