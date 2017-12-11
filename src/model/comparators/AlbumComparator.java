package model.comparators;

import model.Composition;

import java.util.Comparator;

public class AlbumComparator implements Comparator<Composition> {
    @Override
    public int compare(Composition o1, Composition o2) {
        return o1.getAlbum().compareTo(o2.getAlbum());
    }
}
