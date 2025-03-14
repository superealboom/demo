package cn.afuo.javabasic.reflection;


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
