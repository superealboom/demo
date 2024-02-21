package com.superealboom.demo.reflection;

/**
 * @description: cat
 * @author: tianci
 * @date: 2022/5/17 17:10
 */
public class Cat implements Animal {

    private final String catName;

    public Cat (String catName) {
        this.catName = catName;
    }

    @Override
    public void eat(String foodName) {
        System.out.println(catName + " cat eat " + foodName);
    }
}
