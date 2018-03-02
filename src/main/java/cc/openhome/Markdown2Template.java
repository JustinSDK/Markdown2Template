package cc.openhome;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class Markdown2Template {
    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        props.load(
            Markdown2Template.class.getClassLoader()
                             .getResourceAsStream("application.properties")
        );
        
        String templateFile = props.getProperty("templateFile");
        String urlBase = props.getProperty("urlBase");
        String toc = props.getProperty("toc");
        String codeLang = props.getProperty("codeLang", "");
        String docDir = props.getProperty("docDir");
        
        Template template = new Template(templateFile, urlBase, toc, codeLang);
        HtmlPreProcessor.htmlPreProcessors(Paths.get(docDir))
                        .stream()
                        .map(template::toHTML)
                        .forEach(html -> {
                            IO.write(html.getPath(), html.getContent());
                        });
    }
}
