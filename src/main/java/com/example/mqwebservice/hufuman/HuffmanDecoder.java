package com.example.mqwebservice.hufuman;

import java.util.Iterator;
import java.util.Map;

/**
 * @author: sskaigit
 * @date: 2019-01-04
 * 霍夫曼解码器
 */
public class HuffmanDecoder {
    /**
     * 代码段对应的代码集
     */
    private Map codeSet;

    public HuffmanDecoder(Map map){
        codeSet = map;
    }

    /**
     * 将代码段解析成消息文本
     * @param code
     * @return
     */
    public String decode(String code){
        String message = "";
        String key = "";
        char [] chars = code.toCharArray();
        for(int i=0;i<chars.length;i++){
            key += chars[i];
            if(codeSet.containsValue(key)){
                //代码集中存在该段代码
                Iterator it =codeSet.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry entry = (Map.Entry)it.next();
                    if(entry.getValue().equals(key)){
                        //获取该段代码对应的键值，即消息字符
                        message+= entry.getKey();
                    }
                }
                //代码段变量置为0
                key ="";
            }else{
                //该段代码不能解析为文本消息，继续循环
                continue;
            }
        }
        return message;
    }
}
