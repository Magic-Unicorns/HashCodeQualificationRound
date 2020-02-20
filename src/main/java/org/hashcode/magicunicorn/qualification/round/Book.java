package org.hashcode.magicunicorn.qualification.round;

import java.util.HashSet;
import java.util.Set;

public class Book {

	private final int id;
	private final int score;
	private final Data data;
	private final HashSet<Library> owningLibraries;

	public Book(int id, int score, Data data) {
		this.id = id;
		this.score = score;
		this.data = data;
		this.owningLibraries = new HashSet<>();
	}

	public int getId() {
		return id;
	}

	public int getScore() {
		return score;
	}

	public Data getData() {
		return data;
	}

	public void addOwningLibrary(Library library) {
		getOwningLibraries().add(library);
	}

	public Set<Library> getOwningLibraries() {
		return owningLibraries;
	}

}
