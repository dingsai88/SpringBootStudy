package com.ding.study.util;


import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

/**
 * maven:
 * <p>
 * <dependency>
 * <groupId>org.apache.httpcomponents</groupId>
 * <artifactId>httpcore</artifactId>
 * <version>4.4.10</version>
 * </dependency>
 * <p>
 * <dependency>
 * <groupId>org.apache.httpcomponents</groupId>
 * <artifactId>httpclient</artifactId>
 * <version>4.5.6</version>
 * </dependency>
 * <dependency>
 * <groupId>org.apache.httpcomponents</groupId>
 * <artifactId>httpmime</artifactId>
 * <version>4.5.9</version>
 * </dependency>
 * <p>
 * <dependency>
 * <groupId>commons-fileupload</groupId>
 * <artifactId>commons-fileupload</artifactId>
 * <version>1.2.1</version>
 * </dependency>
 * <p>
 * 上传两张图片
 *
 * @author daniel 2019-8-30 0030.
 */
public class NetMain {
    /**
     * 上传两张图片
     *
     * @return
     */
    public static String decaptcha() {
        //167-http://xx.xx.15.126:8092/app/file/stream/upload
        //155-http://xx.xx.11.91:8080/app/file/stream/upload
        HttpPost post = new HttpPost("http://xx.xx.11.91:8080/app/file/stream/upload");
        RequestConfig config = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(20000).build();
        post.setConfig(config);
        File back = new File("D:\\back.jpg");
        File front = new File("D:\\front.jpg");
        FileBody backBody = new FileBody(back, ContentType.DEFAULT_BINARY);
        FileBody frontBody = new FileBody(front, ContentType.DEFAULT_BINARY);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addPart("backPhoto", backBody);
        builder.addPart("frontPhoto", frontBody);
        // 相当于 <input type="file" class="file" name="file">,匹配@RequestParam("file")
        // .addPart()可以设置模拟浏览器<input/>的表单提交
        builder.addTextBody("userId", "333");
        builder.addTextBody("idCardNo", "11111111111");
        builder.addTextBody("bankCode", "zb");
        builder.addTextBody("userIp", "124.0.0.4");
        builder.addTextBody("isNew", "true");
        HttpEntity entity = builder.build();
        post.setEntity(entity);
        String result = "";
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            CloseableHttpResponse closeableHttpResponse = client.execute(post);
            HttpEntity resEntity = closeableHttpResponse.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(resEntity);
                System.out.println("response content:" + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                try {
                    client.close();//关闭httpclient
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("最终返回:"+NetMain.decaptcha());
    }
}
