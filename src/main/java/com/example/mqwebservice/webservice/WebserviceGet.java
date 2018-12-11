package com.example.mqwebservice.webservice;

import com.google.common.io.ByteStreams;
import org.apache.commons.httpclient.HttpStatus;
import org.codehaus.jettison.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP GET请求
 */
public class WebserviceGet {
    /**
     * IP地址和端口
     */
    private static final String urlIp = "http://xxx:xxx/";

    public static void main(String[] args) throws Exception{
        //发送登录请求
        String loginUrl = "xxx/method?sUserID=admin&sPassword=123456&sExportType=JSON&sCharsetName=UTF-8";
        //获取用户主键
        JSONObject jsonLogin = new JSONObject(getMessge(loginUrl));
        String userEntry = jsonLogin.getString("UserEntry");
        //查询客户信息
        Map customerInfo = getMessge("xxx/method?sUserEntry=" + userEntry+"&CusName=");
        //批发/批发退货业绩报表
        String dStartDate = "2017-01-01";
        String dEndDate = "2019-01-01";
        int sType = 1;
        Map customerList = getMessge("xxx/method?sUserEntry=" + userEntry+"&dStartDate="+dStartDate+"&dEndDate="+dEndDate+"&sType="+sType+"&CusName=");
        System.out.println(customerInfo);
        System.out.println("---------------------------");
        System.out.println(customerList);

    }

    /**
     * 发送请求
     * @param method
     * @return
     */
    public static Map getMessge(String method){
        Map result = new HashMap(16);
        try {
            URL url = new URL(urlIp+method);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            //设置输入输出，因为默认新创建的connection没有读写权限，
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //接收服务端响应
            int responseCode = connection.getResponseCode();
            //表示服务端响应成功
            if(HttpStatus.SC_OK == responseCode){
                InputStream is = connection.getInputStream();
                //响应结果
                String s = new String(ByteStreams.toByteArray(is));
                result = com.alibaba.fastjson.JSONObject.parseObject(s, Map.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询在线状态1:"+e.getMessage());
        }
        return result;
    }

}
