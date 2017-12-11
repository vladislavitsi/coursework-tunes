package model.comparators;

import model.Composition;

import java.util.Comparator;

public class YearComparator implements Comparator<Composition>{
    @Override
    public int compare(Composition o1, Composition o2) {
        return o1.getYear().compareTo(o2.getYear());
    }
}
