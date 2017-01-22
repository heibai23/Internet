package codefirst.internet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private WebView internet_wv;
    private Button huc_btn;
    private Button util_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        util_btn= (Button) findViewById(R.id.btn_util);
        huc_btn= (Button) findViewById(R.id.btn_huc);
        util_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent util_intent=new Intent(getApplicationContext(),UseHttpUtisActivity.class);
                startActivity(util_intent);
            }
        });
        huc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent huc_intent=new Intent(getApplicationContext(),HttpURLCActivity.class);
                startActivity(huc_intent);
            }
        });
        internet_wv= (WebView) findViewById(R.id.wv_internet);
        internet_wv.getSettings().setJavaScriptEnabled(true);   //让  支持JS的脚本
        internet_wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //return super.shouldOverrideUrlLoading(view, url);
                view.loadUrl(url);  //根据传入的参数去加载
                return true;    //表示当前webview可处理打开的页面，不用去加载系统 浏览器
            }
        });
        internet_wv.loadUrl("http://live.3g.qq.com/g/s?aid=nba_new&f_aid_ext=nav&i_f=810&sid=&&icfa=home_touch&f_pid=135&iarea=239\n");
    }

}
