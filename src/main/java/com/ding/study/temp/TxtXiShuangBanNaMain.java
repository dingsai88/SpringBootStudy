package com.ding.study.temp;

import com.ding.study.net.HttpClient4Util;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class TxtXiShuangBanNaMain {

    public static void main(String[] args) throws Exception {
        String filePath = "C:\\shenFenZhengZheng.txt"; // 文件路径
        String outputFilePath = "C:\\shenFenZhengZhengAll.txt";


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                 // 假设每行是用制表符分隔的
                String[] fields = line.split("\t");

                if (fields.length >= 2) { // 确保至少有两个字段
                    String customerCode = fields[0].trim();
                    String filePathField = fields[1].trim();

                    System.out.println("CUSTOMER_CODE: " + customerCode);
                    System.out.println("FILE_PATH: " + filePathField);


                    String url = "http://xxx/rc-server/api/faceController/getUserFaceMessage?customerCode=";

                    String result = HttpClient4Util.doGet(url + customerCode);
                    System.out.println( "  返回" + result);
                    // 使用JsonParser解析JSON字符串
                    // 创建 ObjectMapper 实例
                    ObjectMapper objectMapper = new ObjectMapper();

                    // 将 JSON 字符串解析为 JsonNode 对象
                    JsonNode jsonNode = objectMapper.readTree(result);

                    // 获取 message 字段的信息
                    String data = jsonNode.get("data").asText();
                    StringBuilder sb=new StringBuilder();
                    sb.append(customerCode);
                    sb.append("  ");
                    sb.append(filePathField);
                    sb.append("  ");
                    sb.append(data);


                    // 调用静态方法将结果写入到文件中
                  appendToFile(outputFilePath, sb.toString());

                    // 在这里可以继续处理这两个字段，例如构建命令字符串等
                } else {
                    System.out.println("Invalid line format: " + line);
                }


            }
        } catch (IOException e) {
            e.printStackTrace(); // 处理异常
        }
    }

    private static void appendToFile(String filePath, String content) {
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(content);
            bw.newLine();  // 换行

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
