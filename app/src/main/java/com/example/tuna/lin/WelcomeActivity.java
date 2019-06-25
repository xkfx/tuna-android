package com.example.tuna.lin;

import com.example.tuna.R;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tuna.MainActivity;

import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    //3秒后跳转
    private static final int times = 3000;
    private TimerTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                Intent mainIntemt = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(mainIntemt);
                finish();
                //实现淡入浅出的效果
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }

        },times);

    }



}
