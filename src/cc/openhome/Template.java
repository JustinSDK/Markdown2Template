package cc.openhome;

import static cc.openhome.IO.*;
import java.nio.file.Paths;

/**
 *
 * @author Justin
 */
public class Template {

    private final String content;

    private final String rwdImg
            = "<div class=\"pure-g\">"
            + "<div class=\"pure-u-1\">"
            + "<img class=\"pure-img-responsive\" $1 />"
            + "</div>"
            + "</div>";

    private final String docRoot;
    private final String toc;
    private final String codeLang;

    public Template(String template, String docRoot, String toc, String codeLang) {
        this.content  = fileContent(Paths.get("resources\\" + template));
        this.docRoot = docRoot;
        this.toc = toc;
        this.codeLang = codeLang;
    }

    public PathContent toTemplate(PathContent pathContent) {
        return pathContent.markdown2Html(codeLang)
                .replace("img", rwdImg)
                .toTemplate(docRoot, toc, content);
    }
}
