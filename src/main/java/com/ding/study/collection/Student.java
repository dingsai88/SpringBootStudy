package com.ding.study.collection;

/**
 * @author daniel 2019-6-21 0021.
 */
public class Student {
    private String name;
    private String age;

    public Student() {

    }

    public Student(String name, String age) {
        this.name = name;
        this.age = age;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("equals:"+this.getName());
        return true;

    }

    @Override
    public int hashCode() {
        System.out.println("hashCode:"+this.getName());
        return 1;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age='").append(age).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
