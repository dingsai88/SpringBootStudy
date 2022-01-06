package com.ding.study.util;

import org.codehaus.groovy.util.StringUtil;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 数据结构:逻辑关系
 * 无关系
 * 1对1的关系
 * 1对多的关系
 * 多对多的关系
 * <p>
 * <p>
 * 物理关系:
 * 顺序存储
 * 链表存储
 * 散列存储
 * 索引存储
 * com.ding.study.util.TestMain
 * -server
 *
 * @author daniel 2019-6-27 0027.
 */
public class TestMain {


    public static void main(String[] args) {

        System.out.println("SHARED_UNIT:" + (1 << 16));
        Integer t = Integer.valueOf("10000000000000000", 2);
        System.out.println("SHARED_UNIT2 :" + t);
        System.out.println("SHARED_UNIT65535 :" +5);
        System.out.println("SHARED_UNIT2 :" + (1 & 65535));
        System.out.println("SHARED_UNIT2 :" + (0 & 65535));
        System.out.println("SHARED_UNIT2 :" + (3 & 65535));
        System.out.println("SHARED_UNIT3 :" + (1 >>>16));
        System.out.println("SHARED_UNIT4 0:" + (0>>>16));
        System.out.println("SHARED_UNIT4 1:" + (1>>>16));
        System.out.println("SHARED_UNIT4 2:" + (2>>>16));
        binaryToDecimal ( 65536);
        binaryToDecimal ( 65535);
    }

    public static void binaryToDecimal(int n) {
        String result = Integer.toBinaryString(n);

             System.out.println(result);
    }

    public static void t() {


        List<String> list = new ArrayList();
        for (int j = 0; j < 11000; j++) {
            try {
                list.get(-1);
            } catch (Exception ex) {
                int length = ex.getStackTrace().length;
                System.out.println(String.format("报错异常 :: %s, 堆栈长度 :: %s   %s", ex, length, j));
            }
        }

        int[] nums = new int[1];
        int m = nums[4];

    }

    public void test11() {
        List<String> list = new ArrayList();

        for (int j = 0; j < 10000; j++) {
            try {
                list.get(-1);
            } catch (Exception ex) {
                int length = ex.getStackTrace().length;
                System.out.println(String.format("报错异常 :: %s, 堆栈长度 :: %s", ex, length));
            }
        }
    }


    public static void main12(String[] args) throws Exception {
        String App_biz_id = "0123456789";
        String appBizId = App_biz_id.substring(2, App_biz_id.length());

        System.out.println(appBizId);

        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        System.out.println("开始：" + JsonUtils.convertObjToJsonString(now));


        System.out.println("结束:" + now.minusDays(88).format(formatter));
        System.out.println("结束:------------");
        int[] input = new int[]{8, 5, 6, 8, 9, 4, 3, 2, 5, 1};
        System.out.println("结束:------------1:" + input);
        System.out.println("结束:------------2:" + GetLeastNumbers_Solution(input, 3));


        System.out.println("测试开始");
        Integer i = 999;
        TestMain testMain = new TestMain();
        testMain.countInt(i);
        System.out.println("测试开始1");
        testMain.countInt(i);
        System.out.println("测试开始2");

    }

    public void countInt(Integer i) {
        System.out.println("开始:------------:" + i);
        i++;
        System.out.println("结束:------------:" + i);

    }


    public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (input == null || input.length == 0 || k == 0 || k > input.length) {
            return result;
        }
        //冒泡
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] > input[j]) {
                    int temp = input[i];
                    input[i] = input[j];
                    input[j] = temp;
                }
            }
        }


        for (int i = 0; i < k; i++) {
            result.add(input[i]);
        }
        return result;


    }

    public static boolean test() {
        Boolean needUpload;
        String aa = "a";
        String bb = "b";


        needUpload = (aa == null || false);
        System.out.println("1:" + needUpload);
        needUpload |= (bb == null || false);
        System.out.println("2:" + needUpload);
        return needUpload;

    }

    public static void main222(String[] args) throws Exception {
        Objects.requireNonNull(null);
        float j = 1;

        for (int i = 1; i < 44; i++) {
            System.out.println("\t\t\t<option value=\"" + (j / 100) + "\">" + j + "%</option>");
            j += 0.5;
        }

    }

    public static void main1(String[] args) throws Exception {
        String str = "66_";
        SimpleDateFormat simdf = new SimpleDateFormat("yyyyMMddHHmmss");

        System.out.println("现在时间：" + simdf.format(Calendar.getInstance().getTime()));
        String str2 = "66_1906271056_123456789012345678";

        System.out.println(UUID.randomUUID());
        System.out.println(getRequestId("123456789012345678").length());
        System.out.println(getRequestId("123456789012345678"));


    }

    /**
     * 获取请求支付中心的请求ID
     *
     * @param id GUARANTEE_LOAN_RECORD的自增ID
     * @return 66_yyMMddHHmm_表id
     */
    public static String getRequestId(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new RuntimeException("表自增ID为空");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
        return "66_" + sdf.format(Calendar.getInstance().getTime()) + "_" + id;
    }

    public static void main33(String[] args) throws Exception {
        String str = "66_";
        SimpleDateFormat simdf = new SimpleDateFormat("yyyyMMddHHmmss");

        System.out.println("现在时间：" + simdf.format(Calendar.getInstance().getTime()));
        String str2 = "66_1906271056_123456789012345678";
        System.out.println(str2.length());
        System.out.println(UUID.randomUUID());
        System.out.println(getRequestId("123456789012345678").length());
        System.out.println(getRequestId("123456789012345678"));

        for (int i = 0; i < 32; i++) {

            //  System.out.println("               sec.options["+i+"] = new Option(\""+(i+1)+"日\",\""+(i+1)+"\");");

        }


        Integer i = 22;
        System.out.println("aaa:" + (i.toString()).equals("22"));

        System.out.println("位移:" + (1 << 20));
    }
}
