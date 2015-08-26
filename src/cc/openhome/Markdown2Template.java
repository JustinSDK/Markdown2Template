package cc.openhome;

import static cc.openhome.IO.markDownlFiles;
import java.nio.file.Paths;
import java.util.List;

public class Markdown2Template {
    public static void main(String[] args) {
        String srcDir = args[0];
        String docRoot = args[1];
        String codeLang = args.length > 2 ? args[2] : "";
        
        List<String> htmlFiles = markDownlFiles(Paths.get(srcDir));
        Template2015 template2015 = new Template2015(docRoot, codeLang);
        htmlFiles.stream()
                .map(Paths::get)
                .map(IO::pathContent)
                .map(template2015::toTemplate)
                .forEach(IO::write);
    }
}
