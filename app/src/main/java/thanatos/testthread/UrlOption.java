package thanatos.testthread;

import java.net.URL;

/**
 * Created on 2016/12/26.
 * 作者：by Administrator
 * 作用：
 */

public class UrlOption {

    public  String mUrl;
    public  MUrl url=new UrlOption.MUrl();
    public  String result;


    static class MUrl{

        private String tag;

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}
