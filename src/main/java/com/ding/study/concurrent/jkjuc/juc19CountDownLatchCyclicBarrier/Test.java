package com.ding.study.concurrent.jkjuc.juc19CountDownLatchCyclicBarrier;

import com.ding.study.util.JsonUtils;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
/**
 * @author daniel 2021-10-14 0014.
 */
public class Test {


    public static void main(String[] args) throws Exception {
        final String[] resData = new String[1];
        CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();
        client.start();
        final CountDownLatch latch = new CountDownLatch(1);
        final HttpPost post = new HttpPost("http://tag-platform-webapi.tag-platform.paas.corp/api/data/searchUserTag");
        String param1="userId=46&tagNames=age&channel=yrcf";

        //设置请求头    这里根据个人来定义
        post.addHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
       // post.setHeader("Accept", "application/x-www-form-urlencoded");
        StringEntity stringEntity = new StringEntity(param1);
        post.setEntity(stringEntity);
        //执行
        client.execute(post, new FutureCallback<HttpResponse>() {
            //执行异步操作  请求完成后
            @Override
            public void completed(final HttpResponse response) {
                latch.countDown();
                //响应内容
                int a = response.getStatusLine().getStatusCode();
                System.out.println("状态码:"+a+"::"+System.currentTimeMillis());
                if (a == 200) {
                    HttpEntity entity = response.getEntity();
                    try {
                        resData[0] = EntityUtils.toString(entity);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("成功!");
                } else {
                    try {
                        //输出响内容
                        System.out.println(response.getStatusLine().getStatusCode()
                                + "  " + EntityUtils.toString(response.getEntity(), "UTF-8"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            //请求失败处理
            @Override
            public void failed(final Exception ex) {
                latch.countDown();
            }

            //请求取消后处理
            @Override
            public void cancelled() {
                latch.countDown();
            }

        });




        final HttpPost post2 = new HttpPost("http://tag-platform-webapi.tag-platform.paas.corp/api/data/searchUserTag");
        String param2="userId=146&tagNames=age&channel=yrcf";

        //设置请求头    这里根据个人来定义
        post2.addHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
        post2.setHeader("Accept", "application/x-www-form-urlencoded");
        StringEntity stringEntity2 = new StringEntity(param2);
        post2.setEntity(stringEntity2);
        //执行
        client.execute(post2, new FutureCallback<HttpResponse>() {
            //执行异步操作  请求完成后
            @Override
            public void completed(final HttpResponse response) {
                latch.countDown();
                //响应内容
                int a = response.getStatusLine().getStatusCode();
                System.out.println("状态码:"+a+"::"+System.currentTimeMillis());
                if (a == 200) {
                    HttpEntity entity = response.getEntity();
                    try {
                        resData[0] = EntityUtils.toString(entity);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("成功!");
                } else {
                    try {
                        //输出响内容
                        System.out.println(response.getStatusLine().getStatusCode()
                                + "  " + EntityUtils.toString(response.getEntity(), "UTF-8"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            //请求失败处理
            @Override
            public void failed(final Exception ex) {
                latch.countDown();
            }

            //请求取消后处理
            @Override
            public void cancelled() {
                latch.countDown();
            }

        });








        System.out.println("成功! 睡之前");

//Thread.sleep(111);


        System.out.println("成功! 睡之后");




        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //关闭
        try {
            client.close();
        } catch (IOException ignore) {

        }
        System.out.println("成功!"+JsonUtils.convertObjToJsonString(resData[0]));

    }
}
