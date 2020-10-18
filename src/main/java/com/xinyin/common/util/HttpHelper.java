package com.xinyin.common.util;

import okhttp3.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author billy
 */
public class HttpHelper {

    private final static Integer TIMEOUT = 5;

    public static Response get(String url) throws Exception {
        return get(url, new HashMap<>(), TIMEOUT);
    }

    public static Response get(String url, Map<String, Object> map) throws Exception {
        return get(url, map, TIMEOUT);
    }

    public static Response get(String url, Map<String, Object> map, Integer timeout) throws Exception {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(timeout, TimeUnit.MINUTES)
                .build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url)
                .newBuilder();
        for (String key : map.keySet()) {
            urlBuilder.addQueryParameter(key, String.valueOf(map.get(key)));
        }
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .build();
        return client.newCall(request).execute();
    }

    public static Response post(String url) throws Exception {
        return post(url, new HashMap<>(), TIMEOUT);
    }

    public static Response post(String url, Map<String, Object> map) throws Exception {
        return post(url, map, TIMEOUT);
    }

    public static Response post(String url, Map<String, Object> map, Integer timeout) throws Exception {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(timeout, TimeUnit.MINUTES)
                .build();
        FormBody.Builder formBody = new FormBody.Builder();
        for (String key : map.keySet()) {
            formBody.add(key, String.valueOf(map.get(key)));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(formBody.build())
                .build();
        return client.newCall(request).execute();
    }
}
