package org.hashcode.magicunicorn.qualification.round;

import java.util.ArrayList;
import java.util.List;

public class Data {
	private final int numberOfDifferentBooks;
	private final int numberOfLibraries;
	private final int numberOfDay;
	private final List<Book> books;
	private final List<Library> libraries;

	public List<Library> getLibraries() {
        return libraries;
    }

    public Data(int numberOfDifferentBooks, int numberOflibraries, int numberOfDay) {
		this(numberOfDifferentBooks, numberOflibraries, numberOfDay, new ArrayList<>(), new ArrayList<>());
	}

	public Data(int numberOfDifferentBooks, int numberOflibraries, int numberOfDay, List<Book> books,List<Library> libraries) {
		super();
		this.numberOfDifferentBooks = numberOfDifferentBooks;
		this.numberOfLibraries = numberOflibraries;
		this.numberOfDay = numberOfDay;
		this.books = books;
		this.libraries = libraries;
	}

	public int getNumberOfDifferentBooks() {
		return numberOfDifferentBooks;
	}

	public int getNumberOfLibraries() {
		return numberOfLibraries;
	}

	public int getNumberOfDay() {
		return numberOfDay;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void addBooks(List<Book> parseListofBook) {
		books.addAll(parseListofBook);
	}

	public void addLibrary(Library parseLibrary) {
		libraries.add(parseLibrary);
	}

}
