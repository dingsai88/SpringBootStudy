package com.ding.study.util;

import java.io.*;
import java.util.Scanner;

/**
 * @author daniel 2020-9-23 0023.
 */
public class TestFileGBKToUtf8Main {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入需要改变编码格式的文件位置");
        String str = scan.nextLine();
        File file = new File(str);
        System.out.println("文件的初始编码");
        String bm1 = scan.nextLine();
        System.out.println("文件需要转换成的编码");
        String bm2 = scan.nextLine();
        getAllFiles(file, bm1, bm2);
    }

    /**
     *
     * @param file 要编译的文件
     * @param bm1 文件的初始编码
     * @param bm2 文件需要转换成的编码

     */
    public static void getAllFiles(File file, String bm1, String bm2) throws Exception {
        if (file.isDirectory()) {
            File[] test = file.listFiles();
            for (File test1 : test) {
                //类的名字
                String str = test1.getPath();
                if (str.endsWith("java") & test1.isFile()) {
                    String[] s = str.split("\\.");
                    String filecope = s[0] + "cope." + s[1];
                    System.out.println(filecope);
                    File fil = new File(filecope);
                    //转格式
                    InputStreamReader isr = new InputStreamReader(new FileInputStream(test1), bm1);
                    OutputStreamWriter osr = new OutputStreamWriter(new FileOutputStream(fil), bm2);
                    int re = -1;
                    while ((re = isr.read()) != -1) {
                        osr.write(re);
                    }
                    isr.close();
                    osr.close();
                    InputStreamReader isrr = new InputStreamReader(new FileInputStream(fil), bm2);
                    OutputStreamWriter osrw = new OutputStreamWriter(new FileOutputStream(test1), bm2);
                    int r = -1;
                    while ((r = isrr.read()) != -1) {
                        osrw.write(r);
                    }
                    isrr.close();
                    osrw.close();
                    boolean d = fil.delete();
                    System.out.println(str + "文件转换utf-8成功:" + d);
                }
                getAllFiles(test1, bm1, bm2);
            }
        }
    }
}
