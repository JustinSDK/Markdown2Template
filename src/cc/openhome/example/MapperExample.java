package cc.openhome.example;

import cc.openhome.Markdown2Template;

/**
 *
 * @author Justin
 */
public class MapperExample {
     public static void main(String[] args) {
         String templateFile = args[0];
         String srcDir = args[1];
         String docRoot = args[2];
         String codeLang = args[3];
         Markdown2Template.main(new String[] {templateFile, srcDir, docRoot, codeLang}); 
     }
}
