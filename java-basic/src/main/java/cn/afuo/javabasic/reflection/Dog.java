package cn.afuo.javabasic.reflection;


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
