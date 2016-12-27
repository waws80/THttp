package thanatos.testthread;

/**
 * Created on 2016/12/26.
 * 作者：by Administrator
 * 作用：数据接口（监听回调）
 */

public interface ResultModel {
    interface OnListener{
        void complate(String result);
        void Error(String error);
    }
}
