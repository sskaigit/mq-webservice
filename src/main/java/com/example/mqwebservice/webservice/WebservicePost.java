package com.example.mqwebservice.webservice;

import com.google.common.io.ByteStreams;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP POST请求
 */
public class WebservicePost {
    /**
     * HTTP POST请求
     */
    public static void main(String[] args) throws Exception{
        String wsdl = "http://IP:端口/xxx?wsdl";
        String methodName = "method";
        Map<String ,String> map = new HashMap<>(16);
        map.put("sUserID","xxx");
        map.put("sPassword","xxx");
        map.put("sExportType","JSON");
        map.put("sCharsetName","UTF-8");
        //请求体xml
        String xml = getXml(map, methodName);
        //发送请求
        String s = sendURLConnection(wsdl, xml);
        System.out.println(s);
    }

    /**
     * POST请求体
     * @param map 请求参数
     * @param methodName 方法名
     * @return
     */
    public static String getXml(Map<String ,String> map , String methodName){
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<soap:Envelope "
                + "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "
                + "xmlns:xsd='http://www.w3.org/2001/XMLSchema' "
                + "xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/'>");
        sb.append("<soap:Body>");
        sb.append("<" + methodName + " xmlns='http://tempuri.org/'>");
        //post参数
        for (String str : map.keySet()){
            sb.append("<"+str+">"+map.get(str)+"</"+str+">");
        }
        sb.append("</" + methodName + ">");
        sb.append("</soap:Body>");
        sb.append("</soap:Envelope>");

        return sb.toString();
    }

    /**
     * HttpClient发送SOAP请求
     * @param wsdl url地址
     * @param xml   请求体参数
     * @return
     * @throws Exception
     */
    public static String sendHttpPost(String wsdl, String xml) throws Exception{
        int timeout = 10000;
        // HttpClient发送SOAP请求
        System.out.println("HttpClient 发送SOAP请求");
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(wsdl);
        // 设置连接超时
        client.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
        // 设置读取时间超时
        client.getHttpConnectionManager().getParams().setSoTimeout(timeout);
        // 然后把Soap请求数据添加到PostMethod中
        RequestEntity requestEntity = new StringRequestEntity(xml, "text/xml", "UTF-8");
        // 设置请求体
        postMethod.setRequestEntity(requestEntity);
        int status = client.executeMethod(postMethod);
        // 打印请求状态码
        System.out.println("status:" + status);
        // 获取响应体输入流
        InputStream is = postMethod.getResponseBodyAsStream();
        // 获取请求结果字符串
        return new String(ByteStreams.toByteArray(is));
    }

    /**
     * HttpURLConnection 发送SOAP请求
     * @param wsdl url地址
     * @param xml   请求体参数
     * @return
     * @throws Exception
     */
    public static String sendURLConnection(String wsdl, String xml) throws Exception{
        int timeout = 10000;
        // HttpURLConnection 发送SOAP请求
        System.out.println("HttpURLConnection 发送SOAP请求");
        URL url = new URL(wsdl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        conn.setRequestMethod("POST");
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setConnectTimeout(timeout);
        conn.setReadTimeout(timeout);

        DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
        dos.write(xml.getBytes("utf-8"));
        dos.flush();
        InputStream inputStream = conn.getInputStream();
        // 获取请求结果字符串
        return new String(ByteStreams.toByteArray(inputStream));
    }
}
