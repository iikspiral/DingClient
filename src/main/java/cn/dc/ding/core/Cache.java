package cn.dc.ding.core;

import java.util.HashMap;

/**
 * 缓存类
 * Created by dongchen on 2017/1/23.
 */
public class Cache {

    private static HashMap<String, Object> cache = null;

    public static void cacheDate(String key, Object obj) {
        if (cache == null) {
            cache = new HashMap<String, Object>();
        }
        cache.put(key, obj);
    }

    public static Object getCache(String key) {
        if (cache == null) {
            return null;
        }
        return cache.get(key);
    }

}

