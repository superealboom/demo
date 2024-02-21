package com.superealboom.demo.enums;

public enum Season {

    SPRING("春天","春暖花开"),
    SUMMER("夏天","夏日炎炎"),
    AUTUMN("秋天","秋高气爽"),
    WINTER("冬天","白雪皑皑");
    //声明Season对象的属性priavte final修饰
    private final String seasonName;
    private final String seasonDesc;

    //私有化的构造器,并给对象属性赋值
    private Season(String seasonName,String seasonDesc){
        this.seasonName=seasonName;
        this.seasonDesc=seasonDesc;
    }



    //其他诉求:获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }
}
