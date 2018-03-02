package cc.openhome;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IO {
    public static <R> R withIO(UncheckedIO<R> io) {
        try {
            return io.run();
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    public static String content(Path path) { 
        return withIO(() -> Files.readAllLines(path).stream()
                                 .reduce((acc, line) -> acc + line + System.getProperty("line.separator"))
                                 .get());
    }
    
    public static void write(Path path, String content) {
        withIO(() -> Files.write(path, content.getBytes("UTF-8"), TRUNCATE_EXISTING, CREATE));
    }    
}
