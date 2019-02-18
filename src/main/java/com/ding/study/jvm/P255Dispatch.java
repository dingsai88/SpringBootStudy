package com.ding.study.jvm;

import javax.swing.*;

/**
 * @author daniel 2019-2-15 0015.
 */
public class P255Dispatch {

    static class QQ {
    }

    static class _360 {
    }

    ;

    public static class Father {
        public void hardChoice(QQ arg) {
            System.out.println("father choose qq");
        }

        public void hardChoice(_360 arg) {
            System.out.println("father choose _360");
        }

    }

    public static class Son extends Father {
        @Override
        public void hardChoice(QQ arg) {
            System.out.println("Son choose qq");
        }

        @Override
        public void hardChoice(_360 arg) {
            System.out.println("Son choose _360");
        }

    }

    public static void main(String[] args) {
        Father father = new Father();
        Son son = new Son();
        father.hardChoice(new _360());
        son.hardChoice(new QQ());

    }


}
