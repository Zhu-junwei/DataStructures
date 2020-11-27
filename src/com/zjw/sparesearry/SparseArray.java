package com.zjw.sparesearry;

import java.io.*;
import java.util.Arrays;

/**
 * 稀疏数组:
 *      当一个数组中大部分元素为0，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。
 *
 * 稀疏数组的处理方法是:
 *      记录数组一共有几行几列，有多少个不同的值
 *      把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模
 *
 *  如果二维数组如下：
 *      共4行5列，其中大部分值为0，3个非0数值
 *  {
 *      0   0   6   0   0
 *      5   0   0   9   0
 *      0   0   0   0   0
 *      0   0   0   0   0
 *  }
 *  转为稀疏数组如下：
 *      其中第一行[0][0],[0][1],[0][2]表示原始数组的行数、列数、非0的个数
 *      后面的3行中的每一行表示一个非0数值所在的行列及值的大小，如（0，3，6）表示原始数组的第0行第3列的值为6
 *  {
 *      4   5   3
 *      0   3   6
 *      1   0   5
 *      1   3   9
 *  }
 * @author zjw
 */
public class SparseArray {

    public static void main(String[] args) {
        //1、创建一个二维数组
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1 ;
        chessArr1[2][3] = 2 ;
        System.out.println("原始二维数组：");
        //记录非0的个数
        int sum = 0;
        for (int[] rows : chessArr1){
            for (int data : rows){
                System.out.printf("%2d ",data);
                if (data!=0) {
                    sum++;
                }
            }
            System.out.println();
        }

        //2、将二维数组转为稀疏数组
        //为稀疏数组赋值
        //赋值第0行
        int[][] sparseArr = new int[sum+1][3];
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;
        //赋值其他行
        int count = 0;
        for (int i=0; i<chessArr1.length ; i++){
            for (int j=0; j<chessArr1[i].length;j++){
                if (chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];

                }
            }
        }
        System.out.println("转换为的稀疏数组为：");
        printArray(sparseArr);

        //3、将稀疏数组转为二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i=1 ; i<sparseArr.length ; i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("转换后的二维数组为：");
        printArray(chessArr2);


//        saveArray(sparseArr,new File("array.csv"));
//        int[][] readArray = readArray(new File("array.csv"));
//        System.out.println("读取文件的稀疏列表：");
//        for (int[] line : readArray){
//            for (int data : line){
//                System.out.printf("%3d ",data);
//            }
//            System.out.println();
//        }
    }

    public static void saveArray(int[][] arr,File file) throws IOException {
        String path = SparseArray.class.getResource("").getPath().substring(1);
        File saveFile = new File(path+"\\"+file.getName());
        if (!saveFile.exists()){
            if(!saveFile.createNewFile()){
                throw new RuntimeException("创建文件："+saveFile+"失败；");
            }
        }
        //写数据
        FileWriter fw = new FileWriter(saveFile);
        for (int[] ints : arr) {
            String line = Arrays.toString(ints);
            line = line.replace(" ","");
            line = line.substring(1, line.length() - 1);
            fw.write(line + "\r\n");
        }
        fw.close();
    }


    public static int[][] readArray(File file) throws IOException {
        String path = SparseArray.class.getResource("").getPath().substring(1);
        File readFile = new File(path+"\\"+file.getName());
        if (!readFile.exists()){
           throw new RuntimeException("读取文件："+readFile+"失败，文件不存在");
        }
        //读取数据
        BufferedReader br = new BufferedReader(new FileReader(readFile));
        //读取头信息
        String line = br.readLine();
        String[] strings = line.split(",");
        int lens = Integer.parseInt(strings[2]);
        int[][] retArray = new int[lens+1][3];
        retArray[0][0] = Integer.parseInt(strings[0]);
        retArray[0][1] = Integer.parseInt(strings[1]);
        retArray[0][2] = Integer.parseInt(strings[2]);
        //读取头以外的信息
        for(int count=1;count<=lens;count++){
            line = br.readLine();
            strings = line.split(",");
            retArray[count][0] = Integer.parseInt(strings[0]);
            retArray[count][1] = Integer.parseInt(strings[1]);
            retArray[count][2] = Integer.parseInt(strings[2]);
        }
        br.close();
        return retArray;
    }

    /**
     * 打印二维数组
     * @param array 二维数组
     */
    public static void printArray(int[][] array){
        for (int[] rows : array){
            for (int data : rows){
                System.out.printf("%2d ",data);
            }
            System.out.println();
        }
    }
}
