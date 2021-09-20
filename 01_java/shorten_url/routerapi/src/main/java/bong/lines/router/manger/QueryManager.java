package bong.lines.router.manger;

import java.util.concurrent.ConcurrentHashMap;

public class QueryManager {

    private static final ConcurrentHashMap<String, String> queryGetter = new ConcurrentHashMap<>();

    static {
        queryGetter.put("asdweasdqwasd", "value=13141234");
    }

    public static String getQuery(String key){
        return queryGetter.get(key);
    }

}
