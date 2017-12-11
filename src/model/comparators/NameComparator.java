package model.comparators;

import model.Composition;

import java.util.Comparator;

public class NameComparator implements Comparator<Composition> {
    @Override
    public int compare(Composition o1, Composition o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
