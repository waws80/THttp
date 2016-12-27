package thanatos.testthread;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.util.Log;


import junit.framework.Assert;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created on 2016/12/26.
 * 作者：by Administrator
 * 作用：用来连接网络
 */

public class HttpConn {

    private static final String TAG = "HttpConn";
    private Context context;


    public HttpConn(Context context) {
        this.context=context;
    }

    /**
     * 请求的方式
     */
    enum Method{
        GET,
        POST,
        HEAD,
        OPTIONS,
        PUT,
        DELETE,
        TRACE;
    }

    /**
     * Set the method for the URL request, one of:
     * <UL>
     *  <LI>GET
     *  <LI>POST
     *  <LI>HEAD
     *  <LI>OPTIONS
     *  <LI>PUT
     *  <LI>DELETE
     *  <LI>TRACE
     * </UL> are legal, subject to protocol restrictions.  The default
     * method is GET.
     * @param url  request url eg: https://www.baidu.com
     */
    public  String  getResult(Method method , String url, JSONObject jsonObject, Map<String,String> map, int ids){
        try {
            if (url==null||url.isEmpty())throw new NullPointerException("url is null");
            HttpURLConnection conn=null;
            DataOutputStream outStream=null;
            if (url.startsWith("https")){

                if (ids==Integer.MAX_VALUE){
                    conn=setHttp(url);
                }else {
                    Log.w(TAG, "getResult: https"+ids );
                    conn = setHttps(url,ids);
                }
            }else {
                Log.w(TAG, "getResult: http" );
                conn = setHttp(url);
            }
            conn.setRequestMethod(method.name());
            conn.setReadTimeout(8000);
            conn.setConnectTimeout(8000);
            //添加请求头
            addHeader(conn,map);

            conn.setDoInput(true);
            if (method==null||jsonObject==null||jsonObject.toString().isEmpty()){
                method=Method.GET;
            }else {
                conn.setDoOutput(true);
                outStream = new DataOutputStream(conn.getOutputStream());
                outStream.write(jsonObject.toString().getBytes());
                outStream.flush();
            }
            Log.w(TAG, "method--: "+method );
            int responseCode = conn.getResponseCode();
            Log.w(TAG, "getResult: "+responseCode );
            if (responseCode==200){
                String text = Utils.getText(conn.getInputStream());
               if (outStream!=null){
                   outStream.close();
               }
                conn.disconnect();
                return text;
            }else {
                if (Utils.isNetworkAvailable(context)){
                    THttpError.mErrorCode=responseCode;
                    Log.w(TAG, "getResult: "+responseCode );
                    return "0x101,"+responseCode;
                }else {
                    THttpError.mErrorCode=responseCode;
                    return "0x111"+responseCode;
                }

            }

        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * conn的https连接
     * 对https加证书的支持
     * @param url
     * @param ids
     * @return
     */
    private HttpsURLConnection setHttps(String url, int ids){
        try {
            HttpsURLConnection conn = (HttpsURLConnection)new URL(url).openConnection();
            conn.setSSLSocketFactory(getSSlSocketFactory(ids));
            return conn;
        }  catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * conn的http连接
     * @param url
     * @return
     */
    private HttpURLConnection setHttp(String url){
        try {
            HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
            return conn;
        }  catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 对请求头的数据进行处理
     * @param connection
     * @param map
     */
    public void addHeader(HttpURLConnection connection, Map<String,String> map){
        if (map==null||map.isEmpty())return;
        for (String key : map.keySet()) {
            System.out.println("key= "+ key + " and value= " + map.get(key));
            connection.setRequestProperty( key, map.get(key));
        }


    }



    private KeyStore getKeyStore(int certResourceID){
        InputStream inputStream = context.getResources().openRawResource(
                certResourceID);
        try {
            //以 x.509 读取证书
            CertificateFactory cerFactory = CertificateFactory.getInstance("X.509");
            Certificate cer = cerFactory.generateCertificate(inputStream);
            //创建一个证书库，并将证书导入证书库
            String keyStoreType=KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", cer);
            return keyStore;
        } catch (Exception e){
            return null;
        }
    }

    private TrustManagerFactory getTrustManager(KeyStore ks){
        String tmfAlgorithm=TrustManagerFactory.getDefaultAlgorithm();
        try {
            TrustManagerFactory trustManagerFactory=TrustManagerFactory.getInstance(tmfAlgorithm);
            trustManagerFactory.init(ks);
            return trustManagerFactory;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (KeyStoreException e) {
            e.printStackTrace();
            return null;
        }
    }

    private SSLSocketFactory getSSlSocketFactory(int cerId){
        try {
            SSLContext sSLContext=SSLContext.getInstance("TLS");
            sSLContext.init(null,getTrustManager(getKeyStore(cerId)).getTrustManagers(),null);
            return sSLContext.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (KeyManagementException e) {
            e.printStackTrace();
            return null;
        }
    }
}
