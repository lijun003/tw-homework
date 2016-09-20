package com.ifox.util;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, SECONDS)
            .writeTimeout(10, SECONDS)
            .readTimeout(30, SECONDS)
            .build();
    public static OkHttpClient getOkHttpClient() {
        return okHttpClient == null ? new OkHttpClient() : okHttpClient;
    }

    public static Response get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Call call = getOkHttpClient().newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            return response;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    public static void get(String url, Callback callback) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Call call = getOkHttpClient().newCall(request);
        call.enqueue(callback);
    }

    public static Response post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = getRequest(url, body);
        Response response = getOkHttpClient().newCall(request).execute();
        if (response.isSuccessful()) {
            return response;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    public static void post(String url, String json, Callback callback) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = getRequest(url, body);
        Call call = getOkHttpClient().newCall(request);
        call.enqueue(callback);

    }

    public static void post(String url, RequestBody requestBody, Callback callback) throws IOException {
        Request request = getRequest(url, requestBody);
        Call call = getOkHttpClient().newCall(request);
        call.enqueue(callback);

    }

    private static Request getRequest(String url, RequestBody body) {
        return new Request.Builder()
                .url(url)
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; charset=utf-8")
                .post(body)
                .build();
    }
}
