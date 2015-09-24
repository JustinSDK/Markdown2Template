package cc.openhome;

import static cc.openhome.IO.*;
import java.nio.file.Paths;

/**
 *
 * @author Justin
 */
public class Template2015 {

    private final String content = fileContent(Paths.get("resources\\Template2015.html"));

    private final String rwdImg
            = "<div class=\"pure-g\">"
            + "<div class=\"pure-u-1\">"
            + "<img class=\"pure-img-responsive\" $1 />"
            + "</div>"
            + "</div>";

    private final String docRoot;
    private final String codeLang;

    public Template2015(String docRoot, String codeLang) {
        this.docRoot = docRoot;
        this.codeLang = codeLang;
    }

    public PathContent toTemplate(PathContent pathContent) {
        return pathContent.markdown2Html(codeLang)
                .replace("img", rwdImg)
                .toTemplate(docRoot, content);
                
    }
}
