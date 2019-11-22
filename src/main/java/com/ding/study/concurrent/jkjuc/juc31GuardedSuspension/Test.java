package com.ding.study.concurrent.jkjuc.juc31GuardedSuspension;

/**保护性暂时挂起
 *
 * 1.服务端获取队列，如果等于空就等待wait
 * 2.客户端插入队列，插入并且notifyAll()
 * @author daniel 2019-11-22 0022.
 */
public class Test {
    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue,"Alice",3141592L).start();
        new ServerThread(requestQueue,"Bobby",6583184L).start();
    }

}
