package cc.openhome;

import static java.lang.System.out;
import java.nio.file.Path;
import java.util.regex.Matcher;
import org.markdown4j.Markdown4jProcessor;

public class PathContent {
    private Path path;
    private String content;
    private String title;
    private String description;
    
    private final Markdown4jProcessor markdownProcessor = new Markdown4jProcessor(); 

    public PathContent(Path path, String content) {
        this.path = path;
        this.content = content;
    }

    public PathContent markdown2Html(String codeLang) {
        this.content = IO.withIO(() -> markdownProcessor.process(this.content));
        return processTitle().processDescription().pre2PrettyPrint(codeLang);
    }
    
    public PathContent markdown2Html() {
        return markdown2Html("");
    }
        
    private PathContent processTitle() {
        this.title = tagContent(this.content, "title");
        this.content = this.content.replaceFirst("<p>" + this.title + "\n<br  />", "<p>");
        return this;
    }
    
    private PathContent processDescription() {
        this.description = HtmlPatterns.get("all").matcher(this.content).replaceAll("").trim().substring(0, 100) + "...";
        return this;
    }
        
    private String tagContent(String content, String tag) {
        Matcher matcher = HtmlPatterns.get(tag).matcher(content);
        matcher.find();
        return matcher.group(1);
    }

    private PathContent pre2PrettyPrint(String lang) {
        String replacement = "<pre class=\"prettyprint\"><code lang=\"" + lang + "\">";
        if (lang.equals("")) {
            replacement = "<pre class=\"prettyprint\"><code>";
        }
        this.content = this.content.replaceAll("<pre><code>", replacement);
        return this;
    }

    private PathContent pre2PrettyPrint() {
        return pre2PrettyPrint("");
    }

    public Path getPath() {
        return path;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Markdown4jProcessor getMarkdownProcessor() {
        return markdownProcessor;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
