package com.ding.study.temp;

import com.ding.study.util.JsonUtils;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author daniel 2019-6-14 0014.
 */
public class FileTestMain {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream("D:\\DingSai\\data\\aa.txt");
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(Strings.isNullOrEmpty(line)){
                    continue;
                }
                System.out.println("a"+line);
                FileStatusInfo fileStatusInfo = JsonUtils.convertJsonStringToObj(line, FileStatusInfo.class);
                List<CreateGenerateGuaranteeBean> cggList = new Gson().fromJson(fileStatusInfo.getFileData(), new TypeToken<List<CreateGenerateGuaranteeBean>>() {
                }.getType());

                File file = new File("D:\\DingSai\\data\\bb.txt");
                FileOutputStream fos = null;
                if (!file.exists()) {
                    file.createNewFile();//如果文件不存在，就创建该文件
                    fos = new FileOutputStream(file);//首次写入获取
                } else {
                    //如果文件已存在，那么就在文件末尾追加写入
                    fos = new FileOutputStream(file, true);//这里构造方法多了一个参数true,表示在文件末尾追加写入
                }

                OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");//指定以UTF-8格式写入文件


                for (CreateGenerateGuaranteeBean bean : cggList) {
                    //写入内容
                    osw.write(saveAsynFile(bean));
                    //每写入一个Map就换一行
                    osw.write("\r\n");
                }
                //写入完成关闭流
                osw.close();
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }

    }

    /**
     * 生成sql
     * @param data
     * @return
     * @throws Exception
     */
    public static String saveAsynFile(CreateGenerateGuaranteeBean data) throws Exception {
        //异步生成PDF文件
        FileStatusInfo fileStatusInfo = new FileStatusInfo();
        //返回列表
        fileStatusInfo.setFileData(JsonUtils.convertObjToJsonString(data));
        //风险保障协议枚举AutoCreateFileBusinessEnum
        fileStatusInfo.setRequestEnum("xxx");
        fileStatusInfo.setState("100");
        fileStatusInfo.setCreateDte(new Date());
        fileStatusInfo.setUpdateDte(new Date());
        //请求的IDsychro
        fileStatusInfo.setRequestid(data.getTreatyId() + "");
        String str = "  insert into xxx values(xxxxx.nextval ,'" + fileStatusInfo.getRequestid() + "' ,'" + fileStatusInfo.getRequestEnum() + "'  ,'100'  ,'" + fileStatusInfo.getFileData() + "'  ,SYSDATE,SYSDATE) ; ";
        System.out.println(str);
        AtomicInteger a=new AtomicInteger();

        a.set(2);
         return str;
    }



}
