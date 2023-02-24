package com.ding.study.nowcoder.BM73_LongestPalindrome;

import com.ding.study.nowcoder.a24Array_AddTwoNumber.SultionArray_AddTwoNumber;
import com.ding.study.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * https://www.nowcoder.com/practice/b4525d1d84934cf280439aeecc36f4af?tpId=295&tqId=25269&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj
 * BM73 最长回文子串
 * @author daniel
 * @date 2023/2/24 17:40
 **/
@Slf4j
public class SultionLongestPalindrome {

    public int getLongestPalindrome(String a) {
        char[] chars = a.toCharArray();
        int max = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = 0; j < chars.length - 1; j++) {
                log.info("  i :{} idata:{}  j:{} jdata:{}  ", i, chars[i], chars.length - 1 - j, chars[chars.length - 1 - j]);
                boolean bo = equalsData(chars, i, chars.length - 1 - j);
                if (bo) {
                    log.info(" true  触发 i :{} idata:{}  j:{} jdata:{}  ", i, chars[i], chars.length - 1 - j, chars[chars.length - 1 - j]);
                    if (max < (chars.length - j) - (i)) {
                        max = (chars.length - j) - (i);
                    }
                }
            }
        }
        return max;
    }

    public boolean equalsData(char[] chars, int begin, int end) {
        for (int i = 0; i < end; i++) {
            int b = begin + i;
            int e = end - i;
            log.info("  b :{} idata:{}  e:{} jdata:{}  ", b, chars[b], e, chars[e]);
            if (e - b == 2) {
                if (chars[b] == chars[e]) {
                    return true;
                } else {
                    return false;
                }
            }
            if (e - b == 1) {
                if (chars[b] == chars[e]) {
                    return true;
                } else {
                    return false;
                }
            }
            if (chars[b] != chars[e]) {
                return false;
            }
        }


        return false;
    }


    public static void main(String[] args) {
        SultionLongestPalindrome obj = new SultionLongestPalindrome();
        System.out.println("结束:" + JsonUtils.convertObjToJsonString(obj.getLongestPalindrome("1234543aa")));
        System.out.println("结束:" + JsonUtils.convertObjToJsonString(obj.getLongestPalindrome("123454321")));

    }
}
