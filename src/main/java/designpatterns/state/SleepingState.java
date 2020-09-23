package designpatterns.state;

/**
 * 加班加到 睡觉状态
 *
 * @author daniel
 * @version 正式版
 */
public class SleepingState extends State {

    @Override
    public void writeProgram(Work w) {
        System.out.println("\n终态不会变");
        System.out.println("当前时间" + w.getHour() + ",不行了疲惫至极。");
    }

}
