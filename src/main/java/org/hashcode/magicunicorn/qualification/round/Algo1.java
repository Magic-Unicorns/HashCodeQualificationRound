package org.hashcode.magicunicorn.qualification.round;

import java.util.Collection;
import java.util.List;

public class Algo1 {

    int tempsRestant;

    Collection<Book> nonScannesBooks;

    int scoreMax;

    List<Library> nonInitLibs;

    public Algo1(Data data) {
        this.tempsRestant = data.getNumberOfDay();
        this.nonScannesBooks = data.getBooks();
        scoreMax = 0;
        this.nonInitLibs = data.getLibraries();
    }

    public void runAlgo() {
        while (tempsRestant > 0) {
            nonInitLibs.sort((l1, l2) -> -Integer.compare(l1.calculateLibScore(tempsRestant, nonScannesBooks),
                    l2.calculateLibScore(tempsRestant, nonScannesBooks)));
            Library l = nonInitLibs.get(0);
            scoreMax += l.calculateLibScore(tempsRestant, nonScannesBooks);
            nonInitLibs.remove(l);
            tempsRestant -= l.getNbOfDayToProcess();
        }
    }
}
