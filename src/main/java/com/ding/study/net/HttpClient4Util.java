package com.ding.study.net;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * ClassName:HttpsUtil
 * Description:
 * Author:李朋飞
 * Date: 2017/11/10 11:03
 */
public class HttpClient4Util {

    private static Logger logger = LoggerFactory.getLogger(HttpClient4Util.class);

    private static final int SO_TIME_OUT = 15000;
    private static final int CONNECTION_TIME_OUT = 5000;
    public static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
    public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded;charset=UTF-8";

    public static String doGet(String url) throws RuntimeException {
        String result = "";
        HttpClient client = null;
        HttpGet httpGet = null;
        try {
            client = HttpClients.custom().build();
            httpGet = new HttpGet(url);

            long start_http = System.currentTimeMillis();
            HttpResponse response = client.execute(httpGet);
            logger.info("请求" + url + "耗时：" + (System.currentTimeMillis() - start_http));

            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
        }catch (Exception e){
            logger.error("HttpClientUtil.doGet|http get request exception error ", e);
            throw new RuntimeException("HttpClient4Util调用异常");
        }finally {
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
        }

        return result;
    }


    public static String doPost(String url, Map<String, String> map, String contentType) throws RuntimeException {

        String result = "";
        HttpClient client = null;
        HttpPost httpPost = null;
        try {
            if (StringUtils.isEmpty(contentType)) {
                throw new RuntimeException("HttpsUtil.doPost|Content-Type can not be null");
            }

            client = HttpClients.custom().build();
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", contentType);

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            for (String key : map.keySet()) {
                nameValuePairs.add(new BasicNameValuePair(key, map.get(key)));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            httpPost.setConfig(RequestConfig.custom().setSocketTimeout(SO_TIME_OUT).setConnectTimeout(CONNECTION_TIME_OUT).build());
            long start_http = System.currentTimeMillis();
            HttpResponse response = client.execute(httpPost);
            logger.info("请求" + url + "耗时：" + (System.currentTimeMillis() - start_http));
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            logger.error("HttpClientUtil.doPOST|http post request exception error ", e);
            throw new RuntimeException("HttpClient4Util调用异常");
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return result;
    }

    public static String doPost(String url, String paramStr, String contentType) throws RuntimeException {
        String result = "";
        HttpClient client = null;
        HttpPost httpPost = null;
        try {
            if (StringUtils.isEmpty(contentType)) {
                throw new RuntimeException("HttpsUtil.doPost|Content-Type can not be null");
            }
            client = HttpClients.custom().build();
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", contentType);
            StringEntity postEntity = new StringEntity(paramStr, "UTF-8");
            httpPost.setEntity(postEntity);
            httpPost.setConfig(RequestConfig.custom().setSocketTimeout(SO_TIME_OUT).setConnectTimeout(CONNECTION_TIME_OUT).build());

            long start_http = System.currentTimeMillis();
            HttpResponse response = client.execute(httpPost);
            logger.info("请求" + url + "耗时：" + (System.currentTimeMillis() - start_http));
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");

        } catch (Exception e) {
            logger.error("HttpClientUtil.doPOST|http post request exception error ", e);
            throw new RuntimeException("HttpClient4Util调用异常");
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return result;
    }

    public static String doPostKibana(String url, String paramStr, String contentType) throws RuntimeException {
        String result = "";
        HttpClient client = null;
        HttpPost httpPost = null;
        try {
            if (StringUtils.isEmpty(contentType)) {
                throw new RuntimeException("HttpsUtil.doPost|Content-Type can not be null");
            }
            client = HttpClients.custom().build();
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", contentType);
            httpPost.addHeader("kbn-xsrf", "kibana");
            StringEntity postEntity = new StringEntity(paramStr, "UTF-8");
            httpPost.setEntity(postEntity);
            httpPost.setConfig(RequestConfig.custom().setSocketTimeout(SO_TIME_OUT).setConnectTimeout(CONNECTION_TIME_OUT).build());

            long start_http = System.currentTimeMillis();
            HttpResponse response = client.execute(httpPost);
            logger.info("请求" + url + "耗时：" + (System.currentTimeMillis() - start_http));
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");

        } catch (Exception e) {
            logger.error("HttpClientUtil.doPOST|http post request exception error ", e);
            throw new RuntimeException("HttpClient4Util调用异常");
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return result;
    }
    public static byte[] doPostByte(String url, String paramStr, String contentType) throws RuntimeException {
        byte[] result ;
        HttpClient client = null;
        HttpPost httpPost = null;
        try {
            if (StringUtils.isEmpty(contentType)) {
                throw new RuntimeException("HttpsUtil.doPost|Content-Type can not be null");
            }
            client = HttpClients.custom().build();
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", contentType);
            StringEntity postEntity = new StringEntity(paramStr, "UTF-8");
            httpPost.setEntity(postEntity);
            httpPost.setConfig(RequestConfig.custom().setSocketTimeout(SO_TIME_OUT).setConnectTimeout(CONNECTION_TIME_OUT).build());

            long start_http = System.currentTimeMillis();
            HttpResponse response = client.execute(httpPost);
            logger.info("请求" + url + "耗时：" + (System.currentTimeMillis() - start_http));
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toByteArray(entity);

        } catch (Exception e) {
            logger.error("HttpClientUtil.doPOST|http post request exception error ", e);
            throw new RuntimeException("HttpClient4Util调用异常");
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return result;
    }

    public static String doPostJson(String url, String paramStr) throws RuntimeException {
        return doPost(url, paramStr, CONTENT_TYPE_JSON);
    }
    public static byte[] doPostJsonByteArray(String url, String paramStr) throws RuntimeException {
        return doPostByte(url, paramStr, CONTENT_TYPE_JSON);
    }
    public static String doPostJson(String url, Map<String, String> map) throws RuntimeException {
        return doPost(url, map, CONTENT_TYPE_JSON);
    }

    public static String doPostForm(String url, String paramStr) throws RuntimeException {
        return doPost(url, paramStr, CONTENT_TYPE_FORM);
    }

    public static String doPostForm(String url, Map<String, String> map) throws RuntimeException {
        return doPost(url, map, CONTENT_TYPE_FORM);
    }

    /**
     * 带headers的自定义contentType格式请求
     *
     * @param url
     * @param paramStr
     * @param headersMap
     * @param contentType
     * @return
     * @throws RuntimeException
     */
    public static String doPostWithHeaders(String url, String paramStr, Map<String, String> headersMap, String contentType) throws RuntimeException {

        String result = "";
        HttpClient client = null;
        HttpPost httpPost = null;
        try {
            if (StringUtils.isEmpty(contentType)) {
                throw new RuntimeException("HttpsUtil.doPostWithHeaders|Content-Type can not be null");
            }

            client = HttpClients.custom().build();
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", contentType);

            if (headersMap != null) {
                for (String key : headersMap.keySet()) {
                    httpPost.addHeader(key, headersMap.get(key));
                }
            }

            StringEntity postEntity = new StringEntity(paramStr, "UTF-8");
            httpPost.setEntity(postEntity);
            httpPost.setConfig(RequestConfig.custom().setSocketTimeout(SO_TIME_OUT).setConnectTimeout(CONNECTION_TIME_OUT).build());

            long start_http = System.currentTimeMillis();
            HttpResponse response = client.execute(httpPost);
            logger.info("请求" + url + "耗时：" + (System.currentTimeMillis() - start_http));
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            logger.error("HttpsUtil.doPostWithHeaders|http post request exception error ", e);
            throw new RuntimeException("HttpClient4Util调用异常");
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return result;
    }

    /**
     * 带headers的Json格式请求
     *
     * @param url
     * @param paramStr
     * @param headersMap
     * @return
     * @throws RuntimeException
     */
    public static String doPostJsonWithHeaders(String url, String paramStr, Map<String, String> headersMap) throws RuntimeException {
        return doPostWithHeaders(url, paramStr, headersMap, CONTENT_TYPE_JSON);
    }

    /**
     * 带headers的form表单格式请求
     *
     * @param url
     * @param paramStr
     * @param headersMap
     * @return
     * @throws RuntimeException
     */
    public static String doPostFormWithHeaders(String url, String paramStr, Map<String, String> headersMap) throws RuntimeException {
        return doPostWithHeaders(url, paramStr, headersMap, CONTENT_TYPE_FORM);
    }
}