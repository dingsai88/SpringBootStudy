package designpatterns.proxy;

import java.util.ArrayList;
import java.util.List;

/**
 * ����ģʽ��Ϊ���������ṩһ�ִ����Կ��ƶ��������ķ��ʡ�
 * <p>
 * ʹ�ó��ϣ�
 * <p>
 * Զ�̴���һ�������ڲ�ͬ�ĵ�ַ�ռ��ṩ�ֲ�����������������һ����������ڲ�ͬ��ַ�ռ����ʵ��
 * <p>
 * ������������������ܴ�ͨ������ģʽ�����ʵ�����ܳ�ʱ�����ʵ����
 * <p>
 * ��ȫ�������Կ�����ʵ����ķ���Ȩ�ޡ�
 *
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-19 ����10:44:26
 */
public class Zmain {

    /**
     * @param args
     * @author daniel
     * @time 2016-5-19 ����10:43:07
     */
    public static void main(String[] args) {


        Proxy proxy = new Proxy();
        proxy.request();



    }

}
