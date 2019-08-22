package com.ding.study.datastructure;

import com.ding.study.util.JsonUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author daniel 2019-8-14 0014.
 */
public class ACollectionTest {
    public static void main(String[] args) {
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
          List list= Arrays.asList(arr);

        Collections.sort(list);
        Collections.binarySearch(list,4);
        HashMap hm=new HashMap();
        hm.put("","");

    }
}
