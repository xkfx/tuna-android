package com.example.tuna.hui.util;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 对okHttp进行简单封装
 */
public class MyHttpUtil {

    private static final OkHttpClient mOK_HTTP_CLIENT = new OkHttpClient();

    public static final Gson GSON = new Gson();

    public static Response doGet(String url) throws IOException {
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        Call call = mOK_HTTP_CLIENT.newCall(request);
        return call.execute();
    }
}
