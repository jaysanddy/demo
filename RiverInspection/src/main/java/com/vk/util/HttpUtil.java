package com.vk.util;


import com.vk.response.LocationInfo;
import org.apache.commons.codec.EncoderException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by WeiJinTechnology on 2017/2/21.
 */
public class HttpUtil {
    private static CloseableHttpClient httpClient = createHttpsClient();

    public static String getData(String url) throws IOException, EncoderException {
        HttpGet httpGet = new HttpGet(url);
        //在header里加入 Bearer {token}，添加认证的token，并执行get请求获取json数据
        //httpGet.addHeader("Authorization", "Bearer " + ACCESS_TOKEN);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        return body;
    }

    /**
     * json 字符串post请求
     * @param url
     * @param postBody
     * @return
     * @throws IOException
     * @throws EncoderException
     */
    public static String getPostData(String url,String postBody) throws IOException, EncoderException {
        HttpPost httpPost = new HttpPost(url);
        //在header里加入 Bearer {token}，添加认证的token，并执行get请求获取json数据
        //httpGet.addHeader("Authorization", "Bearer " + ACCESS_TOKEN);
        StringEntity se = new StringEntity(postBody,"UTF-8");
        se.setContentType("application/x-www-form-urlencoded");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded"));

        httpPost.setEntity(se);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        return body;
    }

    /**
     * json 字符串post请求
     * @param url
     * @param postBody
     * @return
     * @throws IOException
     * @throws EncoderException
     */
    public static String getPostData(Map<String,String> headerMap, String contentType, String url, String postBody) throws IOException, EncoderException {
        HttpPost httpPost = new HttpPost(url);
        //在header里加入 Bearer {token}，添加认证的token，并执行get请求获取json数据
        //httpGet.addHeader("Authorization", "Bearer " + ACCESS_TOKEN);
        if(headerMap!=null){
            Iterator<Map.Entry<String, String>> headers = headerMap.entrySet().iterator();
            while (headers.hasNext()){
                Map.Entry<String, String> header = headers.next();
                httpPost.setHeader(header.getKey(),header.getValue());
            }
        }
        StringEntity se = new StringEntity(postBody,"UTF-8");
        if(contentType!=null){
            se.setContentType(contentType);
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        }else {
            se.setContentType("text/json;charset=UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        }
        httpPost.setEntity(se);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        return body;
    }

    //创建http client
    public static CloseableHttpClient createHttpsClient() {
        X509TrustManager x509mgr = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] xcs, String string) {
            }
            @Override
            public void checkServerTrusted(X509Certificate[] xcs, String string) {
            }
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        //因为java客户端要进行安全证书的认证，这里我们设置ALLOW_ALL_HOSTNAME_VERIFIER来跳过认证，否则将报错
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{x509mgr}, null);
            sslsf = new SSLConnectionSocketFactory(
                    sslContext,
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }


    private static String URL_PATH = "http://wx.qlogo.cn/mmopen/5BPFEvNH8TbCdVdjMHhQHnAZukylbODMxHj7nt9qPM8Ajz3iaTJw4DjdBwLIEEV32K4o48PHRrVMb0qjp33RNoRfdic7o7w4lg/0";



    public static void downLoadWebPicture(String url,String path) {

        // 获取输入流
        InputStream inputStream = getInputStream(url);

        byte[] date = new byte[1024];
        int len = 0;
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(path);

            while ((len = inputStream.read(date)) != -1) {
                fileOutputStream.write(date, 0, len);

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            try {
                if (inputStream != null) {
                    inputStream.close();

                }

                if (fileOutputStream != null) {
                    fileOutputStream.close();

                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    /**
     * @return
     */
    public static InputStream getInputStream(String urlPath) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(urlPath);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设置连接网络的超时时间
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                inputStream = httpURLConnection.getInputStream();

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return inputStream;
    }

    public static String getLocation(String lat,String log){
        String result = "未获取到位置信息";
        try {
            String ak = "nQFkOqT6ku29Bb985jahkYvghrUm5g32";
            //String data = getData("http://api.map.baidu.com/geocoder?location="+lat+","+log+"&output=json&pois=1");
            String data = getData("http://api.map.baidu.com/geocoder/v2/?location="+lat+","+log+"&output=json&pois=1&ak=nQFkOqT6ku29Bb985jahkYvghrUm5g32");

            if(data!=null){
                System.out.println(data);
                LocationInfo locationInfo = GsonUtil.getGson().fromJson(data,LocationInfo.class);
                if(locationInfo.getStatus()==0){
                    System.out.println(locationInfo.getResult().getFormatted_address());
                    result = locationInfo.getResult().getFormatted_address();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static  void main(String[] args){
       // downLoadWebPicture("http://wx.qlogo.cn/mmopen/5BPFEvNH8TbCdVdjMHhQHnAZukylbODMxHj7nt9qPM8Ajz3iaTJw4DjdBwLIEEV32K4o48PHRrVMb0qjp33RNoRfdic7o7w4lg/0","J:\\test.jpg");
        String latitude = "31.259622223075";
        String longitude = "120.57011437559";
        HttpUtil.getLocation(latitude,longitude);
    }
    @SuppressWarnings("rawtypes")
    public static String localIp(){
        String ip = null;
        Enumeration allNetInterfaces;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                List<InterfaceAddress> InterfaceAddress = netInterface.getInterfaceAddresses();
                for (InterfaceAddress add : InterfaceAddress) {
                    InetAddress Ip = add.getAddress();
                    if (Ip != null && Ip instanceof Inet4Address) {
                        ip = Ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            // TODO Auto-generated catch block
        }
        return ip;
    }

}
