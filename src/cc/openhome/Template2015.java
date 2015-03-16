package cc.openhome;

import static cc.openhome.IO.*;
import static java.lang.System.out;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.markdown4j.Markdown4jProcessor;

/**
 *
 * @author Justin
 */
public class Template2015 {
    private final String template = fileContent(Paths.get("resources\\template.html"));
    
    private final String rwdImg =
                  "<div class=\"pure-g\">" 
                +     "<div class=\"pure-u-1\">" 
                +         "<img class=\"pure-img-responsive\" $1 />"
                +     "</div>"
                + "</div>";
    
    private final String docRoot;
    
    public Template2015(String docRoot) {
       this.docRoot = docRoot;
    }    
    
    public PathContent toTemplate(PathContent pathContent) {
        pathContent.setContent(template
                   .replace("#content#", Matcher.quoteReplacement(pathContent.getContent()))
                   .replaceAll("#title#", pathContent.getTitle())
                   .replaceAll("#url#", docRoot + pathContent.getPath().getFileName().toString().replace(".MD", ".html"))
                   .replaceAll("#description#", pathContent.getDescription()));
        return img2RWD(pathContent);
    }
    
    private PathContent img2RWD(PathContent pathContent) {
        return replace(pathContent, "img", rwdImg);
    }
        
    private PathContent replace(PathContent pathContent, String name, String replacement) {
        pathContent.setContent(HtmlPatterns.get(name).matcher(pathContent.getContent()).replaceAll(replacement));
        return pathContent;
    }

}
