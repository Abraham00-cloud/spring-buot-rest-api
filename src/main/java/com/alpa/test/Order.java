package com.alpa.test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
    @JsonProperty("p-name")
    private String name;

    @JsonProperty("p-color")
    private String color;

    @JsonProperty("p-age")
    private int age;

    
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getColor() {
        return color;
    }


    public void setColor(String color) {
        this.color = color;
    }


    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Order [name=" + name + ", color=" + color + ", age=" + age + "]";
    }


}
