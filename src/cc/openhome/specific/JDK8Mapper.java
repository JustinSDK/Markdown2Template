/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cc.openhome.specific;

import cc.openhome.Markdown2Template;

/**
 *
 * @author Justin
 */
public class JDK8Mapper {
     public static void main(String[] args) {
         String srcDir = "C:\\workspace\\Gossip\\CodeData\\JDK8\\";
         String docRoot = "http://openhome.cc/Gossip/CodeData/JDK8/";
         String codeLang = "java";
         Markdown2Template.main(new String[] {srcDir, docRoot, codeLang});
     }
}
