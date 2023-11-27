package com.ding.study.test;

import com.ding.study.util.AESUtil;
import com.ding.study.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TestMainEC {

    public static void main(String args[]) throws Exception {


        Map<String, String> map = new HashMap<>();
        map.put("ecifid", "1021219743");
        // .CsCustomerAction#custECdetail
        String keyStr = "xxxxxxx";
        byte[] raw = keyStr.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(JsonUtils.convertObjToJsonString(map).getBytes("utf-8"));

        String encodedData = URLEncoder.encode(AESUtil.base64Encode(encrypted), "utf-8");


        log.info("encodedData: {}", encodedData);
    }
}
