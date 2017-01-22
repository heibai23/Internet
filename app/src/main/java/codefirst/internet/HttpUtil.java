package codefirst.internet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cheng on 2017/1/22.
 */
//工具类，将发送Http请求的代码封装(使用HttpURLConnection的请求方式
public class HttpUtil  {

    public static void sendHttpRequest(final String address,final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                try {
                    URL url=new URL(address);       //调用时，网址从参数传入
                    connection= (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(6000);
                    connection.setReadTimeout(6000);
                    connection.setDoInput(true);    //准备向HUC输入或输出
                    connection.setDoOutput(true);
                    InputStream in=connection.getInputStream(); //获取输入流
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder response=new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null){     //按行读取
                        response.append(line);
                    }
                    if(listener!=null){
                        listener.onFinish(response.toString());     //回调
                    }

                 /*catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();*/
                }catch (Exception e){
                    if(listener!=null){
                        listener.onError(e);    //出错时调用
                    }
                }  finally {
                      if(connection!=null)
                          connection.disconnect();      //关闭程序时断开网络连接
                }
            }
        }).start();
    }
}
