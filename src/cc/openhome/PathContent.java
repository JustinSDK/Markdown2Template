package cc.openhome;

import java.nio.file.Path;
import java.util.regex.Matcher;
import org.markdown4j.Markdown4jProcessor;

public class PathContent {
    private final Path path;
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
    
    
    public PathContent toTemplate(String docRoot, String toc, String templateContent) {
        this.content = templateContent
                   .replace("#content#", content)
                   .replaceAll("#title#", title)
                   .replaceAll("#url#", docRoot + path.getFileName().toString().replace(".MD", ".html"))
                   .replaceAll("#description#", description)
                   .replaceAll("#toc#", toc);
        return this;
    }    
    
    public PathContent replace(String name, String replacement) {
        this.content = HtmlPatterns.get(name).matcher(content).replaceAll(replacement);
        return this;
    }    

    public Path getPath() {
        return path;
    }

    public String getContent() {
        return content;
    }
}
