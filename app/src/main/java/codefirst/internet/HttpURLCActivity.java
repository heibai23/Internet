package codefirst.internet;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cheng on 2017/1/22.
 */
//客户端像服务器发出HTTP请求，服务器收到请求后返回数据给客户端，客户端再进行解析
public class HttpURLCActivity extends AppCompatActivity {

    private TextView showWeb_tv;
    private Button send_btn;
    private Button sendWithHc_btn;

    //private URLConnection connection;
    public static final int SHOW_RESPONSE=0;
    private Handler handler=new Handler(){      //控制线程，接收发过来的Message
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SHOW_RESPONSE:
                    String response= (String) msg.obj;
                    showWeb_tv.setText(response);
                 break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpurlconn);


        showWeb_tv= (TextView) findViewById(R.id.tv_showWeb);
        send_btn= (Button) findViewById(R.id.btn_send);
        sendWithHc_btn= (Button) findViewById(R.id.btn_sendWithHC);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestWithHttpURLConnection();     //发送请求的方法
            }
        });
        sendWithHc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestWithHttpClient();
            }


        });

    }
    //使用HttpClient发送HTTP请求
    private void sendRequestWithHttpClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //HttpClient 已经废弃

            }
        }).start();
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {     //耗时操作，放在线程
            @Override
            public void run() {
                HttpURLConnection connection=null;
                try {
                    URL url=new URL("http://www.qq.com");
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");//请求方式
                    connection.setConnectTimeout(8000); //连接响应时间
                    connection.setReadTimeout(8000);
                    InputStream in=connection.getInputStream();
                                        //接下来对获取到的输入流进行读取
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder response=new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null){
                        response.append(line);      //获取到数据
                    }
                    Message message=new Message();
                    message.what=SHOW_RESPONSE;         //利用Message传递数据（在线程中
                    message.obj=response.toString();
                    handler.sendMessage(message);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }  finally {
                     if(connection!=null){
                         connection.disconnect();   //断开连接
                     }
                }

            }
        }).start();
    }
}
