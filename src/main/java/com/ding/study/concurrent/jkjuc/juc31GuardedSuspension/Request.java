package com.ding.study.concurrent.jkjuc.juc31GuardedSuspension;

/**
 * @author daniel 2019-11-22 0022.
 */
public class Request {
    private final String name;

    public Request(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}
