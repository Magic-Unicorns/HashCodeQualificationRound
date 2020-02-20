package org.hashcode.magicunicorn.qualification.round;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

class AbstractTest {
	private static final Instant i = Instant.now();

	protected void testImpl(String string) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
					.withZone(ZoneId.systemDefault());
			Path outputFile = Paths.get("output", formatter.format(i));
			new App(Files.lines(Paths.get(getClass().getResource(string).toURI()), StandardCharsets.UTF_8)
					.collect(Collectors.toList()), outputFile).run();
		} catch (IOException | URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
