package cn.afuo.javabasic.lambda;

/**
 * 静态类调用
 */
public class StaticDemo {

    public static void main(String[] args) {
        StaticDemoInterface staticDemoInterface = StaticDemoSubtraction::subtract;
        int test = staticDemoInterface.test(1, 2);
        System.out.println("两个整数相减的绝对值：" + test);
    }
}
