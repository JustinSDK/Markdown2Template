/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.openhome;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

/**
 *
 * @author Justin
 */
public class IO {

    public static List<String> markDownlFiles(Path path) {
        try (final Stream<Path> paths = withIO(() -> Files.list(path))) {
            return paths.map(Path::toString).filter((String str) -> str.endsWith(".MD")).collect(toList());
        }
    }

    public static <R> R withIO(UncheckedIO<R> io) {
        try {
            return io.run();
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    public static String fileContent(Path path) { 
        return withIO(() -> Files.readAllLines(path).stream().reduce((String acc, String line) -> acc + line + System.getProperty("line.separator")).get());
    }
    
    public static PathContent pathContent(Path path) {
        return new PathContent(path, fileContent(path));
    }
    
    public static void write(PathContent pathContent) {
        Path htmlPath = Paths.get(pathContent.getPath().toString().replace(".MD", ".html"));
        withIO(() -> Files.write(htmlPath, pathContent.getContent().getBytes("UTF-8"), TRUNCATE_EXISTING, CREATE));
    }
}
