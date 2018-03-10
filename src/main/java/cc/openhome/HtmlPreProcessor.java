package cc.openhome;

import static java.util.stream.Collectors.toList;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Stream;

import org.markdown4j.Markdown4jProcessor;

public class HtmlPreProcessor {
    private final Path path;
    private String content;
    private String title;
    
    private final Markdown4jProcessor markdownProcessor = new Markdown4jProcessor(); 

    public HtmlPreProcessor(Path path) {
        String html = IO.withIO(() -> markdownProcessor.process(IO.content(path)));
        String rwdImg = 
                "<div class=\"pure-g\">"
              + "<div class=\"pure-u-1\">"
              + "<img class=\"pure-img-responsive\" $1 />"
              + "</div>"
              + "</div>";       
        
        this.path = path;
        this.title = title(html);
        this.content = HtmlPatterns.get("img").matcher(
            html.replaceFirst(String.format("<p>%s\\n<br  />", title), "<p>")
        ).replaceAll(rwdImg);

    }

    private String title(String content) {
        Matcher matcher = HtmlPatterns.get("title").matcher(content);
        matcher.find();
        return matcher.group(1);
    }

    public String getTitle() {
        return title;
    }
    
    public Path getPath() {
        return path;
    }

    public String getContent() {
        return content;
    }
    
    public static List<HtmlPreProcessor> htmlPreProcessors(Path path) {
        try (final Stream<Path> paths = IO.withIO(() -> Files.list(path))) {
            return paths.map(Path::toString)
                        .filter(str -> str.endsWith(".MD") || str.endsWith(".md"))
                        .map(Paths::get)
                        .map(HtmlPreProcessor::new)
                        .collect(toList());
        }
    }    
}
