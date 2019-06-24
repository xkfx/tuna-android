package com.example.tuna;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.tuna.dong.CardListActivity;
import com.example.tuna.hui.model.Target;
import com.example.tuna.hui.util.MyHttpUtil;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    /**
     * 用于标识日志消息的来源。
     */
    private static final String TAG = "MainActivity";

    /**
     * 单词书列表
     */
    private List<Target> mTargets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_targets);

        ListView listView = findViewById(R.id.listViewTargetsMain);

        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url("http://120.78.82.66:8090/api/v1/targets").build();

        Call call = MyHttpUtil.OK_HTTP_CLIENT.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String json = Objects.requireNonNull(response.body()).string();
                mTargets = MyHttpUtil.GSON.fromJson(json, new TypeToken<List<Target>>() {}.getType());
                if (mTargets != null) {
                    ArrayList<HashMap<String, Object>> hashMaps = new ArrayList<>();
                    for (int i = 0; i < mTargets.size(); i++) {
                        HashMap<String,Object> hashMap = new HashMap<>();
                        hashMap.put("targetName", mTargets.get(i).getName());
                        hashMaps.add(hashMap);
                    }

                    String[] from = {"targetName"};
                    int[] to = {R.id.listViewTargetItemName};

                    runOnUiThread(() -> {
                        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,
                                hashMaps, R.layout.listview_target_item, from,to);
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener((adapterView, view, i, l) -> {
                            Toast.makeText(getApplicationContext(), mTargets.get(i).getId().toString(), Toast.LENGTH_LONG).show();

                            // 打开相应单词列表
                            Intent intent = new Intent(MainActivity.this, CardListActivity.class);

                            Bundle bundle = new Bundle();
                            bundle.putLong("targetId", mTargets.get(i).getId());
                            intent.putExtras(bundle);

                            startActivity(intent);
                        });
                    });
                }
            }
        });
    }
}
