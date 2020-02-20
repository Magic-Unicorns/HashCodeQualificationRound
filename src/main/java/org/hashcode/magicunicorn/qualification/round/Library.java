package org.hashcode.magicunicorn.qualification.round;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Library {

	private final int nbOfBook;
	private final int nbOfDayToProcess;
	private final int bookShippedPerDay;
	private final HashSet<Book> books;

	public Library(int nbOfBook, int nbOfDayToProcess, int bookShippedPerDay) {
		this.nbOfBook = nbOfBook;
		this.nbOfDayToProcess = nbOfDayToProcess;
		this.bookShippedPerDay = bookShippedPerDay;
		this.books = new HashSet<>();
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

	public Set<Book> getBooks() {
		return books;
	}

	public int calculateLibScore(int tempsRestant, Collection<Book> nonScannesBooks) {
		int scoreLibrary = 0;
		int nbBooksMax = (tempsRestant - getNbOfDayToProcess()) * getBookShippedPerDay();
		List<Book> books = getBooks().stream().filter(b -> nonScannesBooks.contains(b))
				.sorted((b1, b2) -> -Integer.compare(b1.getScore(), b2.getScore())).limit(nbBooksMax)
				.collect(Collectors.toList());
		for (Book b : books) {
			scoreLibrary += b.getScore();
			nonScannesBooks.remove(b);
		}
		return scoreLibrary;
	}
}
