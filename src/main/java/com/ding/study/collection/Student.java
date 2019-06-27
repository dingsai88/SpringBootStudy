package com.ding.study.collection;

/**
 * @author daniel 2019-6-21 0021.
 */
public class Student implements Comparable {
    private String name;
    private String age;
    private Integer ages=0;

    public Student() {

    }

    public Student(String name, String age) {
        this.name = name;
        this.age = age;

    }
    public Student(String name, Integer age) {
        this.name = name;
        this.ages = age;

    }
    public Integer getAges() {
        return ages;
    }

    public void setAges(Integer ages) {
        this.ages = ages;
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
        System.out.println("equals:" + this.getName());
        return true;

    }

    @Override
    public int hashCode() {
        System.out.println("hashCode:" + this.getName());
        return 1;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age='").append(ages).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Object o) {
        return Boolean.TRUE==(((Student) o).getAges() > this.getAges())?1:-1;
    }
}
