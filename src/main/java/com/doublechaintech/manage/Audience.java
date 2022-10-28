package com.doublechaintech.manage;

public class Audience {
    private String name;

    public static Audience withName(String name) {
        Audience audience=new Audience();
        audience.setName(name);
        return audience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
