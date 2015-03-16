package cc.openhome;

import cc.openhome.IO;
import static cc.openhome.IO.markDownlFiles;
import cc.openhome.Template2015;
import static java.lang.System.out;
import java.nio.file.Paths;
import java.util.List;

public class Markdown2Template {
    public static void main(String[] args) {
        String srcDir = args[0];
        String docRoot = args[1];
        String codeLang = args.length > 2 ? args[2] : "";
        
        List<String> htmlFiles = markDownlFiles(Paths.get(srcDir));
        Template2015 template2015 = new Template2015(docRoot);
        htmlFiles.stream()
                .map(Paths::get)
                .map(IO::pathContent)
                .map(pathContent -> pathContent.markdown2Html(codeLang))
                .map(template2015::toTemplate)
                .forEach(IO::write);
    }
}
