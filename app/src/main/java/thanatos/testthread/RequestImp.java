package thanatos.testthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.webkit.WebView;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * Created on 2016/12/26.
 * 作者：by Administrator
 * 作用：将获取到的数据进行线程切换
 */

public class RequestImp implements ResultModel {

    private static final String TAG = "RequestImp";
    //HttpUrlConication 的实现
    private HttpConn mHttpConn;
    //主线程handler
    private THttpHandler mHandler=null;
    //信号量来控制每次进入线程池的数量
    private Semaphore semaphore=new Semaphore(1);
    //线程池的最大数量
    private ExecutorService executorService=Executors.newFixedThreadPool(1);
    //请求数据的回调
    private static OnListener listeners;

    /**
     *
     * 请求数据的实现
     * @param mHttpConn    HttpUrlConication 的实现
     * @param method       请求的方式
     * @param url           请求的网址
     * @param jsonObject    请求体
     * @param map           请求头
     * @param ids           支持https所需要的证书的资源ID（默认值为Integer.MAX_VALUE）
     * @param listener      请求数据的回调
     */
    public RequestImp(HttpConn mHttpConn, HttpConn.Method method, String url, JSONObject jsonObject, Map<String,String>
            map,
                      int ids,
                      OnListener
            listener) {
        this.mHttpConn = mHttpConn;
        listeners=listener;
        getResult(method,url,jsonObject,map,ids);
    }

    /**
     * 获取到轮询队列返回到主线程的数据，并通过回调事件进行回调。
     */
    private  class THttpHandler extends Handler{
        private Finish finish;

        public THttpHandler(Finish finish){
            this.finish=finish;
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            UrlOption option= (UrlOption) msg.obj;
            if (option.mUrl.equals(option.url.getTag())){
                if (option.result==null){

                }else {
                    if (option.result.contains(",")){
                        String[] split = option.result.split(",");
                        if (split[0].equals("0x101"))listeners.Error(split[1]);
                        if (split[0].equals("0x111"))listeners.Error(split[1]+"---网络异常");
                    }
                    listeners.complate(option.result);
                }

            }
        }
    }

    /**
     * 将请求放到子线程进行线程轮询，并发送给主线程handler
     * @param method   请求的方式
     * @param url    请求的url
     * @param jsonObject   请求体
     * @param map       请求头
     * @param ids 支持https所需要的证书的资源ID（默认值为Integer.MAX_VALUE）
     */

    public void getResult(final HttpConn.Method method, final String url, final JSONObject jsonObject, final
    Map<String,String> map,final int ids) {

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    Looper.prepare();
                    mHandler=new THttpHandler(finish);
                    String result = mHttpConn.getResult(method, url,jsonObject,map,ids);
                    UrlOption option=new UrlOption();
                    option.mUrl=url;
                    option.result=result;
                    option.url.setTag(url);
                    Message message=Message.obtain();
                    message.obj=option;
                    mHandler.sendMessage(message);
                    semaphore.release();
                    Looper.loop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.shutdown();
    }

    private Finish finish;
    public void Isuccess(Finish finish){
        this.finish=finish;

    }


   interface Finish{
       void success(String res);
   }

}
