package com.example.mqwebservice.hufuman;

import lombok.Data;

/**
 * @author: sskaigit
 * @date: 2019-01-04
 */
@Data
public class Node {
    /**
     * 树节点存储的关键字，如果是非叶子节点为空
     */
    private String key;
    /**
     * //关键字词频
     */
    private int frequency;
    /**
     * /左子节点
     */
    private Node left;
    /**
     * 右子节点
     */
    private Node right;
    /**
     * 优先级队列中指向下一个节点的引用
     */
    private Node next;

    /**
     * 构造方法1
     * @param fre
     * @param str
     */
    public Node(int fre,String str){
        frequency = fre;
        key = str;
    }

    /**
     * 构造方法2
     * @param fre
     */
    public Node(int fre){
        frequency = fre;
    }
}
