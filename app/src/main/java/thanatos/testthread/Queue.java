package thanatos.testthread;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.Set;

/**
 * Created on 2016/12/27.
 * 作者：by Administrator
 * 作用：
 */

public class Queue  {

    private static final String TAG = "Queue";


    public Queue(Set<Request> requestSet) {
        queueRequest(requestSet);
    }

    public void queueRequest(final Set<Request> requests){
        Log.w(TAG, "queueRequest: "+requests.size() );
        for (final Request request: requests) {
           UIHandler mUIHandler=new UIHandler(requests,request);
           request.request();
           mUIHandler.sendEmptyMessage(0x100);
        }
    }

    private static class  UIHandler extends Handler{

        private WeakReference<Set<Request>> reference;
        private Request request;

         UIHandler(Set<Request> requestSet,Request request){
            reference=new WeakReference<>(requestSet);
            this.request=request;
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
             reference.get().remove(request);
            Log.w(TAG, "queueRequest: "+reference.get().size() );
        }
    }





}
