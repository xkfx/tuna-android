package com.example.tuna.lin.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.tuna.R;
import com.example.tuna.hui.model.Card;
import com.example.tuna.hui.model.CardListVO;
import com.example.tuna.hui.util.MyHttpUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class CardListActivity extends AppCompatActivity {

    private static final String TAG = "CardListActivity";

    /**
     * 当前页面中的单词列表
     */
    private List<Card> mCards;

    /**
     * 单词列表总页数
     */
    private int mTotalNumberOfPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_cards);

        Bundle bundle = this.getIntent().getExtras();
        long targetId = 1000L;
        if (bundle != null) {
            targetId = bundle.getLong("targetId");
            Log.d(TAG, Long.toString(targetId));
        }

        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url("http://120.78.82.66:8090/api/v1/card_review_vo?targetId="+ Long.toString(targetId)+ "&pageSize=7&pageNo=1").build();

        Call call = MyHttpUtil.OK_HTTP_CLIENT.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String json = Objects.requireNonNull(response.body()).string();
                CardListVO cardListVO = MyHttpUtil.GSON.fromJson(json, CardListVO.class);
                if (cardListVO != null) {
                    mCards = cardListVO.getCardList();
                    mTotalNumberOfPages = cardListVO.getTotalNumberOfPages();
                }

                Log.d(TAG, mCards.toString());
                Log.d(TAG, String.valueOf(mTotalNumberOfPages));
            }
        });
    }
}
