package cc.openhome.example;

import cc.openhome.Markdown2Template;

/**
 *
 * @author Justin
 */
public class MapperExample {
     public static void main(String[] args) {
         String srcDir = "D:\\workspace\\Gossip\\CodeData\\DockerLayman\\";
         String docRoot = "http://openhome.cc/Gossip/CodeData/DockerLayman/";
         String codeLang = "java";
         Markdown2Template.main(new String[] {srcDir, docRoot, codeLang});
     }
}
