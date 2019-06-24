package com.example.tuna.lin;
import com.example.tuna.R;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WordActivity extends AppCompatActivity {
    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_actvity);
        Intent intent = getIntent();
        String url =intent.getStringExtra("word");
        webview = new WebView(this);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webview.getSettings().setJavaScriptEnabled(true);  //设置WebView属性,运行执行js脚本
        webview.loadUrl("https://fanyi.baidu.com/?aldtype=85#en/zh/"+url);          //调用loadUrl方法为WebView加入链接
        webview.setWebViewClient(new WebViewClient(){    //
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                try{
                    if(url.startsWith("baiduboxlite://")||url.startsWith("https://")||url.startsWith("baiduboxapp://")){
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                }catch (Exception e){
                    return false;
                }
                view.loadUrl(url);
                return true;
            }
        });
        setContentView(webview);
    }
}
