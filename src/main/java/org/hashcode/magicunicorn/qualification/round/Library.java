package org.hashcode.magicunicorn.qualification.round;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Library {

	private final int nbOfBook;
	private final int nbOfDayToProcess;
	private final int bookShippedPerDay;
	private final HashSet<Book> books;
	private final int id;
	
	private List<Book> livresScannes;

	public Library(int id,int nbOfBook, int nbOfDayToProcess, int bookShippedPerDay) {
		this.id = id;
		this.nbOfBook = nbOfBook;
		this.nbOfDayToProcess = nbOfDayToProcess;
		this.bookShippedPerDay = bookShippedPerDay;
		this.books = new HashSet<>();
		this.livresScannes = new ArrayList<>(nbOfBook);
	}

	public void addBook(Book book) {
		book.addOwningLibrary(this);
		getBooks().add(book);
	}

	public int getNbOfBook() {
		return nbOfBook;
	}

	public int getNbOfDayToProcess() {
		return nbOfDayToProcess;
	}

	public int getBookShippedPerDay() {
		return bookShippedPerDay;
	}

    public Set<Book> getBooks(){
        return books;
    }
    
    public int calculateLibScore(int tempsRestant, Collection<Book> nonScannesBooks) {
        int scoreLibrary = 0;
        List<Book> books = getBooksScanables(tempsRestant, nonScannesBooks);
        for(Book b: books) {
            scoreLibrary += b.getScore();
        }
        return scoreLibrary;
    }

    protected List<Book> getBooksScanables(int tempsRestant, Collection<Book> nonScannesBooks) {
        int nbBooksMax = (tempsRestant - getNbOfDayToProcess())*getBookShippedPerDay();
        List<Book> books = getBooks().stream().filter(b -> nonScannesBooks.contains(b)).limit(nbBooksMax).collect(Collectors.toList());
        return books;
    }
    
    public void processLib(int tempsRestant, Collection<Book> nonScannesBooks) {
        List<Book> books = getBooksScanables(tempsRestant, nonScannesBooks);
        for(Book b: books) {
            livresScannes.add(b);
            nonScannesBooks.remove(b);
        }
    }
     
    public int calculateLibScore(int tempsRestant, List<Book> nonScannesBooks, List<Library> libsnonInit) {
        int scoreLibrary = 0;
        int tp = tempsRestant;
        List<Book> listBookRest = new ArrayList<>(nonScannesBooks);
        List<Library> listLibRest = new ArrayList<>(libsnonInit);
        List<Book> books = getBooksScanables(tempsRestant, nonScannesBooks);
        for(Book b: books) {
            scoreLibrary += b.getScore();
        }
        processLib(tp, listBookRest);
        listLibRest.remove(this);
        while(tp > 0 && !listBookRest.isEmpty() && !listLibRest.isEmpty()) {
            Map<Library, Integer> libmap = new HashMap<>();
            for(Library lib: listLibRest) {
                libmap.put(lib, lib.calculateLibScore(tempsRestant, nonScannesBooks, listLibRest));
            }
            listLibRest.sort((l1, l2) -> -Integer.compare(libmap.get(l1),
                    libmap.get(l2)));
            Library l = listLibRest.get(0);
            scoreLibrary += l.calculateLibScore(tempsRestant, nonScannesBooks, listLibRest);
            l.processLib(tempsRestant, nonScannesBooks);
            
            listLibRest.remove(l);
            tp -= l.getNbOfDayToProcess();
        }
        return scoreLibrary;
    }

	public int getId() {
		return id;
	}

	public List<Book> getScannedBook() {
		return livresScannes;
	}
}
