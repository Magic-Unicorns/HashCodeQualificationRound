package org.hashcode.magicunicorn.qualification.round;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Algo1 {

    int tempsRestant;

    List<Book> nonScannesBooks;

    int scoreMax;

    List<Library> nonInitLibs;

    public Algo1(Data data) {
        this.tempsRestant = data.getNumberOfDay();
        this.nonScannesBooks = data.getBooksAsList();
        scoreMax = 0;
        this.nonInitLibs = data.getLibraries();
    }

    public List<Library> runAlgo() {
        List<Library> libs = new ArrayList<>();
        while (tempsRestant > 0 && !nonScannesBooks.isEmpty() && !nonInitLibs.isEmpty()) {
            
            
            Map<Library, Integer> libmap = new HashMap<>();
            for(Library lib: nonInitLibs) {
                libmap.put(lib, lib.calculateLibScore(tempsRestant, nonScannesBooks, nonInitLibs));
            }
            
            
            nonInitLibs.sort((l1, l2) -> -Integer.compare(libmap.get(l1),
                    libmap.get(l2)));
            Library l = nonInitLibs.get(0);
            scoreMax += l.calculateLibScore(tempsRestant, nonScannesBooks);
            l.processLib(tempsRestant, nonScannesBooks);
            libs.add(l);
            nonInitLibs.remove(l);
            tempsRestant -= l.getNbOfDayToProcess();
        }
        return libs;
    }
}
