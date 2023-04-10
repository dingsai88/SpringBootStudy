package com.ding.study.net;

/**
 * @author daniel
 * @date 2023/4/9 20:30
 **/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 *

 curl http://xxx:43206/health/chatGPT35Stream -H "Content-Type: application/json"    -d ' {"data":"说个100字小说"}'

 */
public class TestGPT {
    public static void main(String[] args) throws IOException {
       // String url = "http://x.a.com/health/chatGPT35Stream";
        String url ="http://x.a.com/health/chatGPT35Stream";
        String jsonBody = "{\"data\":\"写首李白风格的诗\"}"; // 替换成实际的JSON

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

        con.setDoOutput(true);

        byte[] jsonBytes = jsonBody.getBytes(StandardCharsets.UTF_8);
        con.getOutputStream().write(jsonBytes);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.print(inputLine);
        }
        in.close();
    }
}
