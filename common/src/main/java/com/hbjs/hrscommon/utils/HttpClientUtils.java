package com.hbjs.hrscommon.utils;

import com.alibaba.fastjson.JSONObject;
import com.hbjs.hrscommon.dto.HttpResult;
import com.hbjs.hrscommon.dto.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class HttpClientUtils {

    public static void main(String[] args) {

    }

    /**
     * 发送get请求工具
     * @param url
     * @return
     */
    public static String sendGet(String url, Map<String, Object> params) {
        HttpGet httpGet = new HttpGet(url);
        if (!params.isEmpty()) {
            System.out.println(params);
        }
        return executeHttpClient(httpGet);
    }

    /**
     * 发送post请求工具
     * @param url
     * @param responseBody
     * @return
     */
    public static String sendPost(String url, ResponseBody<?> responseBody) {

        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader("Content-Type", "application/json");
        if (responseBody != null) {
            if (responseBody.getHeaders() != null) {
                Map<String, String> head = responseBody.getHeaders();
                for (String key : head.keySet()) {
                    httpPost.addHeader(key, head.get(key));
                }
            }

            if (responseBody.getData() != null) {
                log.info(JSONObject.toJSONString(responseBody.getData()));
                httpPost.setEntity(new StringEntity(JSONObject.toJSONString(responseBody.getData()), "UTF-8"));
            }
        }

        return executeHttpClient(httpPost);
    }

    public static String executeHttpClient(HttpUriRequest httpUriRequest) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String result;
        try {
            // 通过请求对象获取响应对象
            HttpResponse response = httpClient.execute(httpUriRequest);
            //判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //获取响应实体
                result = EntityUtils.toString(response.getEntity(),"utf-8");
                log.info("请求地址：{}，返回数据：{}", httpUriRequest.getURI(), result);
            } else {
                HttpResult httpResult = new HttpResult().setStatus(response.getStatusLine().getStatusCode());
                result = JSONObject.toJSONString(httpResult);
                log.warn("请求地址：{}，访问失败，错误代码：{}", httpUriRequest.getURI(), response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("调用接口失败，url：" + httpUriRequest.getURI());
        }
        return result;
    }


}
