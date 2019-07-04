package com.ding.study.temp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author daniel 2019-7-3 0003.
 */
public class ReadFileTestMain {

    public static void main(String[] args) throws Exception {

        toArrayByFileReader1("C:\\Users\\Administrator.CE-20160511RDFS\\Desktop\\错误日志资产.txt");

    }

    public static void toArrayByFileReader1(String name) {
        try {
            FileReader fr = new FileReader(name);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                String[] array = str.split("\t");
                if (array[1].length() > 30) {
                    //System.out.println("update  a  set CONTRACT_PATH='" + array[2].substring(0,16) + "' where id=" + array[0] + " ; ");
                    System.out.println(" update esign_request  set contact_no ='"+array[1].substring(0,16)+".pdf' , contract_path ='"+array[1].substring(0,16)+"' , retry_count = 0, retry_time = SYSDATE() where request_id ='"+array[0] +"' ;");
                }

            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
