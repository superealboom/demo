package com.superealboom.demo.reflection;

/**
 * @description: dog
 * @author: tianci
 * @date: 2022/5/17 16:56
 */
public class Dog implements Animal {

    private final String dogName;

    public Dog(String dogName) {
        this.dogName = dogName;
    }

    @Override
    public void eat(String foodName) {
        System.out.println(dogName + " dog eat " + foodName);
    }

}
