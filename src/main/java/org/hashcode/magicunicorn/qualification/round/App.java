package org.hashcode.magicunicorn.qualification.round;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class App {

	private Data data;

	public App(List<String> inputInFile, Path outputFile) { // in out setting
		Iterator<String> iterator = inputInFile.iterator();
		data = parseHeader(iterator.next());
		data.addBooks(parseListofBook(iterator.next(), data.getNumberOfDifferentBooks(), data));
		int i = 0;
		while (iterator.hasNext()) {
			String libparam = iterator.next();
			if (!libparam.trim().isEmpty()) {
				data.addLibrary(parseLibrary(libparam, iterator.next(), i++, data));
			}
		}
	}

	private Library parseLibrary(String libParameter, String libcontent, int id, Data data) {
		try (Scanner sc = new Scanner(libParameter); Scanner content = new Scanner(libcontent)) {
			Library lib = new Library(sc.nextInt(), sc.nextInt(), sc.nextInt());
			while (content.hasNextInt()) {
				lib.addBook(data.getBookById(content.nextInt()).orElseThrow(IllegalStateException::new));
			}
			return lib;
		}
	}

	private Data parseHeader(String header) {
		try (Scanner sc = new Scanner(header)) {
			return new Data(sc.nextInt(), sc.nextInt(), sc.nextInt());
		}
	}

	private List<Book> parseListofBook(String books, int nbOfBook, Data data) {
		ArrayList<Book> output = new ArrayList<>(nbOfBook);
		try (Scanner sc = new Scanner(books)) {
			for (int i = 0; i < nbOfBook; i++) {
				output.add(new Book(i, sc.nextInt(), data));
			}
		}
		return output;
	}

	public void run() { // run algo
		// TODO Auto-generated method stub

	}

}
