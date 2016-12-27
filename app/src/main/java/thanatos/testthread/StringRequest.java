package thanatos.testthread;

import android.content.Context;

import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2016/12/26.
 * 作者：by Administrator
 * 作用：请求框架
 */

public class StringRequest  extends Request{


    private RequestImp requestImp;
    private HttpConn.Method method;
    private String url;
    private ResultModel.OnListener listener;

    private JSONObject jsonObject;


    public StringRequest(HttpConn.Method method, String url, JSONObject jsonObject, ResultModel.OnListener
            listener) {
        this.method=method;
        this.url=url;
        this.listener=listener;
        this.jsonObject=jsonObject;
        request();

    }
    @Override
    public void request(){
        if (requestImp==null)
        requestImp=new RequestImp(new HttpConn(THttp.getContext()),jsonObject==null? HttpConn.Method.GET:method,url,
                jsonObject,getHeader(),THttp
                .cerId,listener);
    }
}
