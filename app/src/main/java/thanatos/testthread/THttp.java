package thanatos.testthread;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created on 2016/12/26.
 * 作者：by Administrator
 * 作用：
 */

public class THttp {
    private static WeakReference<Context> reference=null;

    public static int cerId=Integer.MAX_VALUE;

    private THttp() {
    }

    /**
     * 注册默认方式请求
     * @param context
     * @return
     */
    public static THttp register(Context context){
        reference=new WeakReference<>(context);
        return  THttpHolder.T_HTTP;
    }

    /**
     * 注册带证书的https请求
     * @param context
     * @param cerid
     * @return
     */
    public static THttp register(Context context,int cerid){
        reference=new WeakReference<>(context);
        cerId=cerid;
        return  THttpHolder.T_HTTP;
    }

    private static class THttpHolder{
        private static THttp T_HTTP=new THttp();
    }

    public static Context getContext(){
        if (reference==null)throw new NullPointerException("please regist THttp");
        return reference.get();
    }

    /**
     * 销毁请求框架
     * 一般放在onDestory()
     */
    public  static void unRegist(){
        if (reference!=null){
            reference.clear();
            reference=null;
        }
    }

    /**
     * 将请求添加到请求队列
     * @param request
     */
    public static void addRequest(final StringRequest request){
   request.request();
}

}
