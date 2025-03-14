package cn.afuo.javabasic.reflection;

import java.lang.reflect.Method;

/**
 * 反射
 */
public class AnimalMain {

    public static void main(String[] args) throws Exception {
        eat("cn.afuo.javabasic.reflection.Dog","Spike","eat","dog food");
        eat("cn.afuo.javabasic.reflection.Cat","Tom","eat","cat food");
    }


    private static void eat(String className, String constructorName, String methodName, String foodName) throws Exception {
        Animal animal = (Animal) Class.forName(className).getConstructor(String.class).newInstance(constructorName);
        Method method = animal.getClass().getDeclaredMethod(methodName, String.class);
        method.invoke(animal, foodName);
    }
}
