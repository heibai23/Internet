package codefirst.internet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by cheng on 2017/1/22.
 */
//工具类测试  HttpUtil
public class UseHttpUtisActivity extends AppCompatActivity {

    private String address="http://www.baidu.com";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_util);

        Button use_btn;
        final TextView test_tv;
        use_btn= (Button) findViewById(R.id.btn_use);
        test_tv= (TextView) findViewById(R.id.tv_util);
        /*use_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        test_tv.setText(response);      //根据返回内容执行具体逻辑
                        Toast.makeText(getApplicationContext(),"什么",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Exception e) {
                        test_tv.setText("出错了");             //根据返回内容执行具体逻辑
                        Toast.makeText(getApplicationContext(),"出错",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });*/
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                test_tv.setText(response);      //根据返回内容执行具体逻辑
                Toast.makeText(getApplicationContext(),"什么",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Exception e) {
                test_tv.setText("出错了");             //根据返回内容执行具体逻辑
                Toast.makeText(getApplicationContext(),"出错",Toast.LENGTH_LONG).show();
            }
        });
    }
}
