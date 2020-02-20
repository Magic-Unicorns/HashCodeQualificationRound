package org.hashcode.magicunicorn.qualification.round;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {

	private Data data;
	private Path outputFile;

	public App(List<String> inputInFile, Path outputFile) { // in out setting
		this.outputFile = outputFile;
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
			Library lib = new Library(id, sc.nextInt(), sc.nextInt(), sc.nextInt());
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

	public void run() throws IOException { // run algo
		Algo1 alg = new Algo1(data);
		List<Library> runAlgo = alg.runAlgo();
		try (BufferedWriter newBufferedWriter = Files.newBufferedWriter(outputFile, StandardCharsets.US_ASCII)) {
			for (Library lib : runAlgo) {
				List<Book> scannedBook = lib.getScannedBook();
				String l1 = lib.getId() + " " + scannedBook.size();
				String l2 = String.join(" ",
						scannedBook.stream().map(Book::getId).map(String::valueOf).collect(Collectors.toList()));
				newBufferedWriter.append(l1 + System.lineSeparator());
				newBufferedWriter.append(l2 + System.lineSeparator());
			}
		}
	}
}
