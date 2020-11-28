package com.zjw.queue;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 使用数组模拟环形队列，
 * 优点：数组可以循环使用
 * 缺点：数组中有一个位置不能使用
 * @author 朱俊伟
 * @date 2020/11/28
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArray queue = new CircleArray(5);
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
 * 模拟环形队列的类
 */
class CircleArray{

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
     * 队列的大小
     */
    private final int maxSize;

    /**
     * 队列初始化
     * @param maxSize 定义队列的初始化容量
     */
    CircleArray(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
        rear = 0;
        front = 0;
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
        return (rear  + 1) % maxSize == front;
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
            array[rear] = data;
            rear = (rear+1)%maxSize ;
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
            int value = array[front];
            front = (front + 1) % maxSize ;
            return value;
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
            return array[front];
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
        int[] subArray;
        if (rear>front){
            subArray = Arrays.copyOfRange(array, front, rear);
        }else {
            int[] subFrontArray = Arrays.copyOfRange(array,front,maxSize);
            int[] subRearArray = Arrays.copyOfRange(array,0,rear);
            subArray = new int[subFrontArray.length + subRearArray.length];
            System.arraycopy(subFrontArray,0,subArray,0,subFrontArray.length);
            System.arraycopy(subRearArray,0,subArray,subFrontArray.length,subRearArray.length);
        }
        return Arrays.toString(subArray);
    }

}

