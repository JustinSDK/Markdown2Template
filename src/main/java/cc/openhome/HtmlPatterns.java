package cc.openhome;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class HtmlPatterns {
    private static final Map<String, Pattern> patterns = new HashMap<>();

    static {
        patterns.put("title", Pattern.compile("(?:<p>|<h1>)(.+?)(?:\\n|</h1>)"));
        patterns.put("all", Pattern.compile("\\<[^>]*>"));
        patterns.put("img", Pattern.compile("<img (.+?)/>", Pattern.DOTALL));
    }

    public static Pattern get(String key) {
        return patterns.get(key);
    }
}
