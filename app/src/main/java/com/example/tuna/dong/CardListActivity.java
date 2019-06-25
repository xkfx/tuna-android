package com.example.tuna.dong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.tuna.R;
import com.example.tuna.hui.model.Card;
import com.example.tuna.hui.model.CardListVO;
import com.example.tuna.hui.util.MyHttpUtil;
import com.example.tuna.lin.WordActivity;

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

    /**
     * 当前页数
     */
    private int mPage=1;

    /**
     * 单词本 ID
     */
    private long mTargetId;

    /**
     * 单词本名
     */
    private String mTargetName;
    /**
     * 当前listview
     */
    private ListView mListView ;

    /* 上一页，下一页按钮
     */
    private Button mPreBtn;
    private Button mNextBtn;

    /**
     * 单词本标题
     */
    private TextView mListTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_cards);
        mListView = findViewById(R.id.list_card);
        Bundle bundle = this.getIntent().getExtras();
        long targetId = 1000L;
        String targetName = null;
        if (bundle != null) {
            targetId = bundle.getLong("targetId");
            targetName = bundle.getString("targetName");
            Log.d(TAG, Long.toString(targetId));
        }
        mTargetId = targetId;
        mTargetName = targetName;
        mPreBtn = findViewById(R.id.previousBtn);
        mNextBtn = findViewById(R.id.nextBtn);
        mListTitle = findViewById(R.id.Title);
        mListTitle.setText(mTargetName);
        ShowCards(0);

    }
    public void ShowCards(int action){
        // 修改 mPage 并检查 mPreBtn
        if (action==0){
            mPreBtn.setEnabled(false);
        }
        else if(action==1){
            mPage = mPage+action;
            mPreBtn.setEnabled(true);
        }
        else if(action==-1){
            mPage = mPage+action;
            if(mPage==1){
                mPreBtn.setEnabled(false);
            }
        }
        else{
            Log.d(TAG,"ShowCards() para error");
        }

        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url("http://120.78.82.66:8090/api/v1/card_review_vo?targetId="+ Long.toString(mTargetId)
                + "&pageSize=7&pageNo="+Integer.toString(mPage)).build();

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
                    ArrayList<HashMap<String, Object>> hashMaps = new ArrayList<>();
                    for (int i = 0; i < mCards.size(); i++) {
                        HashMap<String,Object> hashMap = new HashMap<>();
                        hashMap.put("word", mCards.get(i).getFront());
                        hashMaps.add(hashMap);
                    }
                    String[] from = {"word"};
                    int[] to = {R.id.card_item};

                    runOnUiThread(() -> {
                        SimpleAdapter adapter = new SimpleAdapter(CardListActivity.this,
                                hashMaps, R.layout.listview_cards_item, from,to);
                        mListView.setAdapter(adapter);
                        //ListView点击监听
                        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                            public void onItemClick(AdapterView parent, View view, int position, long id){
                                //获取单词通过Intent传入下一个Activity
                                Intent intent = new Intent(CardListActivity.this,WordActivity.class);
                                intent.putExtra("word",mCards.get(position).getFront());
                                startActivity(intent);
                                //从左往右的跳转动画
                                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                            }
                        });
                    });
                }

                Log.d(TAG, mCards.toString());
                Log.d(TAG, String.valueOf(mTotalNumberOfPages));
            }
        });

        // 检查 mNextBtn
        if(mPage==mTotalNumberOfPages){
            mNextBtn.setEnabled(false);
        }
        else mNextBtn.setEnabled(true);
    }
    public void ToPrePage(View view){
        int toPrePage = -1;
        ShowCards(toPrePage);
    }
    public void ToNextPage(View view){
        int toNextPage = 1;
        ShowCards(toNextPage);
    }


}
