package com.ding;

import com.ding.bean.SendReq;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by 老丁 on 2017-12-19 0019.
 */
public class AllBeanTest {
    SendReq bean=new SendReq();

    @Before
    public void setUp() throws Exception {

  /* Field[] field = bean.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组

            for (int j = 0; j < field.length; j++) { // 遍历所有属性
                String name = field[j].getName(); // 获取属性的名字
                name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
                String type = field[j].getGenericType().toString(); // 获取属性的类型


                System.out.println("bean.set"+name+"(null);");
                System.out.println("bean.get"+name+"();");

            }
*/
    }



    @Test
    public void testAccountBean() throws Exception {

        assertNotNull(bean.toString());
    }



}
