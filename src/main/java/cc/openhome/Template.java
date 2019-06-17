package cc.openhome;

import static cc.openhome.IO.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;

public class Template {
    private final String templateContent;
    private final String urlBase;
    private final String toc;
    private final String codeLang;

    public Template(String templateFile, String urlBase, String toc, String codeLang) {
        this.templateContent = content(
                Paths.get(getClass().getClassLoader().getResource(templateFile).getPath().substring(1)));
        this.urlBase = urlBase;
        this.toc = toc;
        this.codeLang = codeLang;
    }
    
    private String description(String html) {
    	String all = HtmlPatterns.get("all")
	        .matcher(html)
	        .replaceAll("")
	        .trim();
        return (all.length() > 100 ? all.substring(0, 100) : all) +  "...";
    }

    public Html toHTML(HtmlPreProcessor htmlPreProcessor) {
        Path path = htmlPreProcessor.getPath();
        String title = htmlPreProcessor.getTitle();
        String content = htmlPreProcessor.getContent();

        String replacement = codeLang.equals("") ? 
                "<pre class=\"prettyprint\"><code>" :
                String.format("<pre class=\"prettyprint\"><code lang=\"%s\">", codeLang);
        
        String desc = description(content);

        content = templateContent
                   .replace("#content#", content.replaceAll("<pre><code>", replacement))
                   .replaceAll("#title#", title)
                   .replaceAll("#url#", urlBase + path.getFileName().toString().replace(".md", ".html").replace(".MD", ".html"))
                   .replaceAll("#description#", Matcher.quoteReplacement(desc))
                   .replaceAll("#toc#", toc); 
        
        return new Html(content, Paths.get(path.toString().replace(".md", ".html").replace(".MD", ".html")));
    }
}
