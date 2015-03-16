/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cc.openhome;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author Justin
 */
public class HtmlPatterns {
     private static final Map<String, Pattern> patterns = new HashMap<>();
     static {
        patterns.put("title", Pattern.compile("<p>(.+?)\n"));
        patterns.put("all", Pattern.compile("\\<[^>]*>"));
        patterns.put("img", Pattern.compile("<img (.+?)/>", Pattern.DOTALL));
    } 
     
    public static Pattern get(String key) {
        return patterns.get(key);
    }
}