package com.example.mqwebservice.hufuman;

/**
 * @author: sskaigit
 * @date: 2019-01-04
 * 用于辅助创建霍夫曼树的优先级队列
 */
public class PriorityQueue {
    private Node first;
    private int length;

    public PriorityQueue(){
        length = 0;
        first = null;
    }

    //插入节点
    public void insert(Node node){
        //队列为空
        if(first == null){
            first = node;
        }else{
            Node cur = first;
            Node previous = null;
            //定位要插入位置的前一个节点和后一个节点
            while(cur.getFrequency()< node.getFrequency()){
                previous = cur;
                //已到达队尾
                if(cur.getNext() ==null){
                    cur = null;
                    break;
                }else{
                    cur =cur.getNext();
                }

            }
            //要插入第一个节点之前
            if(previous == null){
                node.setNext(first);
                first = node;
            }else if(cur == null){
                //要插入最后一个节点之后
                previous.setNext(node);
            }else{
                //插入到两个节点之间
                previous.setNext(node);
                node.setNext(cur);
            }
        }
        length++;
    }

    /**
     * 删除队头元素
     */
    public Node delete(){
        Node temp = first;
        first = first.getNext();
        length--;
        return temp;
    }

    /**
     * 获取队列长度
     * @return
     */
    public int getLength(){
        return length;
    }

    /**
     * 按顺序打印队列
     */
    public void display(){
        Node cur = first;
        System.out.print("优先级队列：\t");
        while(cur != null){
            System.out.print(cur.getKey()+":"+cur.getFrequency()+"\t");
            cur = cur.getNext();
        }
        System.out.println();
    }

    /**
     * 构造霍夫曼树
     * @return
     */
    public HuffmanTree buildHuffmanTree(){
        while(length > 1){
            //取出队列的第一个节点作为新节点的左子节点
            Node hLeft = delete();
            //取出队列的第二个节点作为新节点的右子节点
            Node hRight = delete();
            //新节点的权值等于左右子节点的权值之和
            Node hRoot = new Node(hLeft.getFrequency()+hRight.getFrequency());
            hRoot.setLeft(hLeft);
            hRoot.setRight(hRight);
            insert(hRoot);
        }
        //最后队列中只剩一个节点，即为霍夫曼树的根节点
        return new HuffmanTree(first);
    }
}
