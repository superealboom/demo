package com.superealboom.demo.reflection;

import java.lang.reflect.Method;

/**
 * @description: 反射
 * @author: tianci
 * @date: 2022/5/17 16:55
 */
public class Demo {

    public static void main(String[] args) {
        Animal animal = null;
        try {
            animal = (Animal) Class.forName("com.superealboom.demo.reflection.Dog").getConstructor(String.class).newInstance("spike");
            Method method = animal.getClass().getDeclaredMethod("eat", String.class);
            method.invoke(animal, "(dog food)");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            animal = (Animal) Class.forName("com.superealboom.demo.reflection.Cat").getConstructor(String.class).newInstance("Tom");
            Method method = animal.getClass().getDeclaredMethod("eat", String.class);
            method.invoke(animal, "(cat food)");
        } catch (Exception e) {
            e.printStackTrace();
        }

        animal.eat("apple");
    }
}
