package org.hashcode.magicunicorn.qualification.round;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Data {
	private final int numberOfDifferentBooks;
	private final int numberOfLibraries;
	private final int numberOfDay;
	private final Map<Integer,Book> books;
	private final List<Library> libraries;

	public Data(int numberOfDifferentBooks, int numberOflibraries, int numberOfDay) {
		this(numberOfDifferentBooks, numberOflibraries, numberOfDay, new HashMap<>(), new ArrayList<>());
	}

	public Data(int numberOfDifferentBooks, int numberOflibraries, int numberOfDay, Map<Integer,Book> books,List<Library> libraries) {
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

	public Collection<Book> getBooks() {
		return books.values();
	}

	public void addBooks(List<Book> parseListofBook) {
		parseListofBook.forEach(b-> books.put(b.getId(), b));
	}

	public void addLibrary(Library parseLibrary) {
		libraries.add(parseLibrary);
	}

	public Optional<Book> getBookById(int id) {
		return Optional.ofNullable(books.get(id));
	}
	
	public List<Library> getLibraries() {
        return libraries;
    }
}
