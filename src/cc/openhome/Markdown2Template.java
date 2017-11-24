package cc.openhome;

import static cc.openhome.IO.markDownlFiles;
import java.nio.file.Paths;
import java.util.List;

public class Markdown2Template {
    public static void main(String[] args) {
        String templateName = args[0];
        String srcDir = args[1];
        String docRoot = args[2];
        String toc = args[3];
        String codeLang = args.length > 4 ? args[4] : "";
        
        List<String> htmlFiles = markDownlFiles(Paths.get(srcDir));
        Template template = new Template(templateName, docRoot, toc, codeLang);
        htmlFiles.stream()
                .map(Paths::get)
                .map(IO::pathContent)
                .map(template::toTemplate)
                .forEach(IO::write);
    }
}
