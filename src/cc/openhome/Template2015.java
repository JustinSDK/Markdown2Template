package cc.openhome;

import static cc.openhome.IO.*;
import java.nio.file.Paths;

/**
 *
 * @author Justin
 */
public class Template2015 {
    private final String template = fileContent(Paths.get("resources\\Template2015.html"));
    
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
        return img2RWD(pathContent.toTemplate(template, docRoot));
    }
    
    private PathContent img2RWD(PathContent pathContent) {
        return pathContent.replace("img", rwdImg);
    }
}
