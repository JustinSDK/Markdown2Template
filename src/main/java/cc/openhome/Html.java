package cc.openhome;

import java.nio.file.Path;

public class Html {
    private final String content;
    private final Path path;
    
    public Html(String content, Path path) {
        this.content = content;
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public Path getPath() {
        return path;
    }    
}
