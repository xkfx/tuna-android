package com.example.tuna.lin;

import com.example.tuna.R;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tuna.MainActivity;

import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    /**
     * 设置延时跳转时间
     */
    private static final int times = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //将定时器放入Handler中
        //运行函数
        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
            //实现淡入浅出的效果
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        },times);

    }
    @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }



}
