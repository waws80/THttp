package thanatos.testthread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private String url="https://www.qigeairen.com/api/v2.0/activity/0";


    private THttp mThttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //兼容5.0以下使用https和使用证书访问https
//        mThttp=THttp.register(this,R.raw.qgar);
        //访问http或5.0以上访问https
        mThttp=THttp.register(this);
        for (int i = 0; i < 1; i++) {
            StringRequest request1=new StringRequest(HttpConn.Method.GET, url,null,new ResultModel
                    .OnListener() {

                @Override
                public void complate(String result) {
                    Log.w(TAG, "complate: "+result );
                }

                @Override
                public void Error(String error) {
                    Log.w(TAG, "Error: "+error );
                }
            }){
                @Override
                public Map<String, String> getHeader() {
                    Map<String,String > map=new HashMap<>();
                    map.put("token","");
                    return map;
                }
            };
            StringRequest request2=new StringRequest(HttpConn.Method.GET, url,null,new ResultModel
                    .OnListener() {

                @Override
                public void complate(String result) {
                    Log.w(TAG, "complate: "+result );
                }

                @Override
                public void Error(String error) {
                    Log.w(TAG, "Error: "+error );
                }
            }){
                @Override
                public Map<String, String> getHeader() {
                    Map<String,String > map=new HashMap<>();
                    map.put("token","");
                    return map;
                }
            };
            StringRequest request3=new StringRequest(HttpConn.Method.GET, url,null,new ResultModel
                    .OnListener() {

                @Override
                public void complate(String result) {
                    Log.w(TAG, "complate: "+result );
                }

                @Override
                public void Error(String error) {
                    Log.w(TAG, "Error: "+error );
                }
            }){
                @Override
                public Map<String, String> getHeader() {
                    Map<String,String > map=new HashMap<>();
                    map.put("token","");
                    return map;
                }
            };
            StringRequest request4=new StringRequest(HttpConn.Method.GET, url,null,new ResultModel
                    .OnListener() {

                @Override
                public void complate(String result) {
                    Log.w(TAG, "complate: "+result );
                }

                @Override
                public void Error(String error) {
                    Log.w(TAG, "Error: "+error );
                }
            }){
                @Override
                public Map<String, String> getHeader() {
                    Map<String,String > map=new HashMap<>();
                    map.put("token","");
                    return map;
                }
            };
            StringRequest request5=new StringRequest(HttpConn.Method.GET, url,null,new ResultModel
                    .OnListener() {

                @Override
                public void complate(String result) {
                    Log.w(TAG, "complate: "+result );
                }

                @Override
                public void Error(String error) {
                    Log.w(TAG, "Error: "+error );
                }
            }){
                @Override
                public Map<String, String> getHeader() {
                    Map<String,String > map=new HashMap<>();
                    map.put("token","");
                    return map;
                }
            };
            StringRequest request6=new StringRequest(HttpConn.Method.GET, url,null,new ResultModel
                    .OnListener() {

                @Override
                public void complate(String result) {
                    Log.w(TAG, "complate: "+result );
                }

                @Override
                public void Error(String error) {
                    Log.w(TAG, "Error: "+error );
                }
            }){
                @Override
                public Map<String, String> getHeader() {
                    Map<String,String > map=new HashMap<>();
                    map.put("token","");
                    return map;
                }
            };
            StringRequest request7=new StringRequest(HttpConn.Method.GET, url,null,new ResultModel
                    .OnListener() {

                @Override
                public void complate(String result) {
                    Log.w(TAG, "complate: "+result );
                }

                @Override
                public void Error(String error) {
                    Log.w(TAG, "Error: "+error );
                }
            }){
                @Override
                public Map<String, String> getHeader() {
                    Map<String,String > map=new HashMap<>();
                    map.put("token","");
                    return map;
                }
            };
            StringRequest request8=new StringRequest(HttpConn.Method.GET, url,null,new ResultModel
                    .OnListener() {

                @Override
                public void complate(String result) {
                    Log.w(TAG, "complate: "+result );
                }

                @Override
                public void Error(String error) {
                    Log.w(TAG, "Error: "+error );
                }
            }){
                @Override
                public Map<String, String> getHeader() {
                    Map<String,String > map=new HashMap<>();
                    map.put("token","");
                    return map;
                }
            };
            StringRequest request9=new StringRequest(HttpConn.Method.GET, url,null,new ResultModel
                    .OnListener() {

                @Override
                public void complate(String result) {
                    Log.w(TAG, "complate: "+result );
                }

                @Override
                public void Error(String error) {
                    Log.w(TAG, "Error: "+error );
                }
            }){
                @Override
                public Map<String, String> getHeader() {
                    Map<String,String > map=new HashMap<>();
                    map.put("token","");
                    return map;
                }
            };
            StringRequest request0=new StringRequest(HttpConn.Method.GET, url,null,new ResultModel
                    .OnListener() {

                @Override
                public void complate(String result) {
                    Log.w(TAG, "complate: "+result );
                }

                @Override
                public void Error(String error) {
                    Log.w(TAG, "Error: "+error );
                }
            }){
                @Override
                public Map<String, String> getHeader() {
                    Map<String,String > map=new HashMap<>();
                    map.put("token","");
                    return map;
                }
            };
            StringRequest request=new StringRequest(HttpConn.Method.GET, url,null,new ResultModel
                    .OnListener() {

                @Override
                public void complate(String result) {
                    Log.w(TAG, "complate: "+result );
                }

                @Override
                public void Error(String error) {
                    Log.w(TAG, "Error: "+error );
                }
            }){
                @Override
                public Map<String, String> getHeader() {
                    Map<String,String > map=new HashMap<>();
                    map.put("token","");
                    return map;
                }
            };
                mThttp.addRequest(request1);
                mThttp.addRequest(request2);
                mThttp.addRequest(request3);
                mThttp.addRequest(request4);
                mThttp.addRequest(request5);
                mThttp.addRequest(request6);
                mThttp.addRequest(request7);
                mThttp.addRequest(request8);
                mThttp.addRequest(request9);
                mThttp.addRequest(request0);
                mThttp.addRequest(request);
                mThttp.start();

        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        THttp.unRegist();
    }
}
