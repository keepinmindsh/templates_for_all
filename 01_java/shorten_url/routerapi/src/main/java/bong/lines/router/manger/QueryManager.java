package bong.lines.router.manger;

import java.util.concurrent.ConcurrentHashMap;

public class QueryManager {

    private static final ConcurrentHashMap<String, String> queryGetter = new ConcurrentHashMap<>();

    static {
        queryGetter.put("asdweasdqwasd", "http://localhost:2001/real_test?value=123");
        queryGetter.put("sfasvsdfefewf", "http://localhost:9000/greeting/greeting?value=123");
    }

    public static String getQuery(String key){
        return queryGetter.get(key);
    }

}
