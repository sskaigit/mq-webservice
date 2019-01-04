package com.example.mqwebservice.hufuman;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author: sskaigit
 * @date: 2019-01-04
 * 霍夫曼编码器
 */
public class HuffmanEncoder {
    /**
     * 辅助建立霍夫曼树的优先级队列
     */
    private PriorityQueue queue;
    /**
     * 霍夫曼树
     */
    private HuffmanTree tree;
    /**
     * 以数组的形式存储消息文本
     */
    private String [] message;
    /**
     * 存储字符以及词频的对应关系
     */
    private Map keyMap;
    /**
     * 存储字符以及代码的对应关系
     */
    private Map codeSet;

    public HuffmanEncoder(){
        queue = new PriorityQueue();
        keyMap = new HashMap<String,Integer>();
    }

    /**
     * 获取指定字符串的霍夫曼编码
     * @param msg
     * @return
     */
    public String encode(String msg){
        resolveMassage(msg);
        buildCodeSet();
        String code = "";
        //将消息文本的逐个字符翻译成霍夫曼编码
        for(int i=0;i<message.length;i++){
            code =code+codeSet.get(message[i]);
        }
        return code;
    }

    /**
     * 将一段字符串消息解析成单个字符与该字符词频的对应关系，存入Map
     */
    private void resolveMassage(String msg){
        //将消息转换成字符数组
        char [] chars =msg.toCharArray();
        message = new String[chars.length];
        for(int i =0;i<chars.length;i++){
            String key = "";
            //将当前字符转换成字符串
            key =chars[i]+"";

            message [i] =  key;
            if(keyMap.containsKey(key)){
                //如果Map中已存在该字符，则词频加一
                keyMap.put(key,(Integer)keyMap.get(key)+1);
            }else{
                //如果Map中没有该字符，加入Map
                keyMap.put(key,1);
            }
        }
    }

    /**
     * 建立对应某段消息的代码集
     */
    private void buildCodeSet(){
        Iterator it =keyMap.entrySet().iterator();
        while(it.hasNext()){
            Entry entry =(Entry)it.next();
            //用该字符和该字符的词频为参数，建立一个新的节点，插入优先级队列
            queue.insert(new Node((Integer)entry.getValue(),(String)entry.getKey()));
        }
        queue.display();
        //利用优先级队列生成霍夫曼树
        tree =queue.buildHuffmanTree();
        //获取霍夫曼树对应的代码集
        codeSet = tree.getCodeSet();
    }

    /**
     * 打印该段消息的代码集
     */
    public void printCodeSet(){
        Iterator it =codeSet.entrySet().iterator();
        System.out.println("代码集：");
        while(it.hasNext()){
            Entry entry =(Entry)it.next();
            System.out.println(entry.getKey()+"——>"+entry.getValue());
        }
        System.out.println();
    }

    /**
     * 获取该段消息的代码集
     * @return
     */
    public Map getCodeSet(){
        return codeSet;
    }
}
