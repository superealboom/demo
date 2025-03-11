package cn.afuo.javabasic.lambda;


public class ThreadDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
           for (int i=0;i<10;i++) {
               System.out.println("新建线程-> " + i);
           }
        });
        thread.start();
    }

}
