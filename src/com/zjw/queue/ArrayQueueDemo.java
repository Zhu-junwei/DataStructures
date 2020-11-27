package com.zjw.queue;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 使用数据模拟队列
 * @author 朱俊伟
 * @date 2020/11/27
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        Queue queue = new Queue(5);
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag){
            System.out.println("1.添加数据 2.取出数据 3.显示队列头 4.清屏 5.退出系统 6.队列信息");
            int i = scanner.nextInt();
            switch (i){
                case 1:
                    System.out.println("请数据数据：");
                    int data = scanner.nextInt();
                    queue.addQueue(data);
                    break;
                case 2:
                    try {
                        int queueData = queueData = queue.getQueue();
                        System.out.println("取出的数据为:\t"+queueData);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        int queueHeadData = queue.getHeadQueue();
                        System.out.println("队列的头为:\t"+queueHeadData);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    systemCls();
                    break;
                case 5:
                    flag = false;
                    break;
                case 6:
                    System.out.println(queue.getQueueInfo());
                default:
                    break;
            }
        }
    }

    /**
     * 清空控制台
     */
    public static void systemCls(){
        // 调用命令行解释器cmd 传参 一个允许调用内置命令的命令的参数/c  调用内置命令cls
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

/**
 * 模拟队列的类
 */
class Queue{

    /**
     * 指向队列的尾部
     */
    private int rear;

    /**
     * 指向队列的头部
     */
    private int front;

    /**
     * 使用数组模拟队列
     */
    private final int[] array;

    /**
     * 队列初始化
     * @param queueMaxSize 定义队列的初始化容量
     */
    Queue(int queueMaxSize) {
        array = new int[queueMaxSize];
        rear = -1;
        front = -1;
    }

    /**
     * 判断队列是否为空
     * @return true为空 false非空
     */
    public boolean isEmpty(){
        return  rear == front ;
    }

    /**
     * 判断队列是否满
     * @return true为满 false不满
     */
    public boolean isFull(){
        return rear == array.length-1;
    }

    /**
     * 向队列中添加数据
     * @param data 需要添加的数据
     */
    public void addQueue(int data){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列满，不能添加数据");
        } else {
            rear ++ ;
            array[rear] = data;
        }
    }

    /**
     * 向队列取数据，取出的数据不再队列中
     * @return 取出的数据
     */
    public int getQueue() throws Exception{
        //判断队列是否为空
        if (isEmpty()){
            throw new RuntimeException("队列为空，不能取数据");
        } else {
            front ++ ;
            return array[front];
        }
    }

    /**
     * 获取队列的头，数据还在队列中
     * @return 取出的数据
     */
    public int getHeadQueue(){
        //判断队列是否为空
        if (isEmpty()){
            throw new RuntimeException("队列为空，不能取数据");
        } else {
            return array[front+1];
        }
    }

    /**
     * 获取当前队列信息
     * @return 队列信息
     */
    public String getQueueInfo(){
        if (isEmpty()){
            return "队列为空";
        }
        int[] subArray = Arrays.copyOfRange(array, front+1, rear+1);
        return Arrays.toString(subArray);
    }

}