package thanatos.testthread;

import java.util.Collections;
import java.util.Map;

/**
 * Created on 2016/12/27.
 * 作者：by Administrator
 * 作用：  请求主框架
 */

public  abstract class Request {

    public abstract void request();

    /**
     * 添加请求头
     * @return map
     */
    public Map<String, String> getHeader() {
        return Collections.emptyMap();
    }
}
