package org.hashcode.magicunicorn.qualification.round;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Library {

	private int nbOfBook;
	private int nbOfDayToProcess;
	private int bookShippedPerDay;

	public Library(int nbOfBook, int nbOfDayToProcess, int bookShippedPerDay) {
		this.nbOfBook = nbOfBook;
		this.nbOfDayToProcess = nbOfDayToProcess;
		this.bookShippedPerDay = bookShippedPerDay;
	}

    public int getSignTime() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNbOfDayToProcess() {
        return nbOfDayToProcess;
    }

    public int getBookShippedPerDay() {
        return bookShippedPerDay;
    }

    public Set<Book> getBooks(){
        return null;
    }
    
    public int calculateLibScore(int tempsRestant, List<Book> nonScannesBooks) {
        int scoreLibrary = 0;
        int nbBooksMax = (tempsRestant - getSignTime())*getBookShippedPerDay();
        List<Book> books = getBooks().stream().filter(b -> nonScannesBooks.contains(b)).sorted((b1,b2)->-Integer.compare(b1.getScore(), b2.getScore())).limit(nbBooksMax).collect(Collectors.toList());
        for(Book b: books) {
            scoreLibrary += b.getScore();
            nonScannesBooks.remove(b);
        }
        return scoreLibrary;
    }
}
