package cc.openhome.example;

import cc.openhome.Markdown2Template;

/**
 *
 * @author Justin
 */
public class MapperExample {
     public static void main(String[] args) {
         String srcDir = "D:\\workspace\\Gossip\\CodeData\\EV3Tutorial\\";
         String docRoot = "http://openhome.cc/Gossip/CodeData/EV3Tutorial/";
         String codeLang = "ev3";
         Markdown2Template.main(new String[] {srcDir, docRoot, codeLang});
     }
}
