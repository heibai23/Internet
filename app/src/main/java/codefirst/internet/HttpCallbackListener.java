package codefirst.internet;

/**
 * Created by cheng on 2017/1/22.
 */

//接收返回的数据（服务器传回来的
public interface HttpCallbackListener {
    void onFinish(String response);     //服务器成功响应时调用
                    //根据返回内容执行具体逻辑
    void onError(Exception e);
}
