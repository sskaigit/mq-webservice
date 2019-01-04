package com.example.mqwebservice.hufuman;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

/**
 * @author: sskaigit
 * @date: 2019-01-04
 */
public class HuffmanTest {
    /**
     * 测试编码
     */
    @Test
    public void encode(){
        PriorityQueue queue = new PriorityQueue();
        Node n1 = new Node(1,"if");
        Node n2 = new Node(1,"U");
        Node n3 = new Node(1,"T");
        Node n4 = new Node(2,"Y");
        Node n5 = new Node(2,"E");
        Node n6 = new Node(2,"A");
        Node n7 = new Node(3,"I");
        Node n8 = new Node(4,"sp");
        Node n9 = new Node(5,"S");
        queue.insert(n3);
        queue.insert(n2);
        queue.insert(n1);
        queue.insert(n6);
        queue.insert(n5);
        queue.insert(n4);
        queue.insert(n7);
        queue.insert(n8);
        queue.insert(n9);
        queue.display();

        HuffmanTree tree =queue.buildHuffmanTree();
        Map map = tree.getCodeSet();
        Iterator it =map.entrySet().iterator();
        System.out.println("霍夫曼编码结果：");
        while(it.hasNext()){
            Map.Entry<String,String> entry = (Map.Entry)it.next();
            System.out.println(entry.getKey()+"——>"+entry.getValue());
        }
    }

    /**
     * 自动编码,解码
     */
    @Test
    public void stringEncode(){
        String message = "chen long fei is hero !";
        HuffmanEncoder encoder = new HuffmanEncoder();
        String code =encoder.encode(message);

        encoder.printCodeSet();
        System.out.print("编码结果：");
        System.out.println(code);

        HuffmanDecoder decoder = new HuffmanDecoder(encoder.getCodeSet());
        String message2 =decoder.decode(code);
        System.out.print("解码结果：");
        System.out.println(message);
    }
}
