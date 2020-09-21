package com.czl.spring.learn.bean;

public class Orders {
    private String oname;

    public Orders() {
        System.out.println("1 constructor");
    }

    public void setOname(String oname) {
        System.out.println("2 set");
        this.oname = oname;
    }

    public void intMethod() {
        System.out.println("3 init");
    }

    public void destry() {
        System.out.println("5 destry");
    }
}
