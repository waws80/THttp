package thanatos.testthread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private String url="https://www.qigeairen.com/api/v2.0/activity/0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //兼容5.0以下使用https和使用证书访问https
        THttp.register(this,R.raw.qgar);
        //访问http或5.0以上访问https
        THttp.register(this);
        for (int i = 0; i < 1; i++) {
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
            THttp.addRequest(request);
        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        THttp.unRegist();
    }
}
