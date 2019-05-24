package com.example.tuna.hui.rpc.impl;

import com.example.tuna.hui.model.Target;
import com.example.tuna.hui.rpc.TargetService;
import com.example.tuna.hui.util.MyHttpUtil;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import okhttp3.Response;

public class TargetServiceImpl implements TargetService {

    private static final String URL_PREFIX = "http://120.78.82.66:8090/api/v1/targets";

    @Override
    public List<Target> listAll() throws IOException {
        Response response = MyHttpUtil.doGet(URL_PREFIX);
        final String json = Objects.requireNonNull(response.body()).string();
        return MyHttpUtil.GSON.fromJson(json, new TypeToken<List<Target>>() {}.getType());
    }
}
