package com.czl.spring.learn;

public class User {
    private String name;
    private int age;
    private String address;

    public User(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public void printUser() {
        System.out.println("User{" +
            "name='" + name + '\'' +
            ", age=" + age + '\'' +
            ", address=" + address +
            '}');
    }
}
