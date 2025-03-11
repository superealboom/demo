package cn.afuo.javabasic.lambda;

/**
 * 静态减类
 */
public class StaticDemoSubtraction {

    public static int subtract(int a, int b){
        if (a > b) {
            return a - b;
        }
        return b - a;
    }

}
