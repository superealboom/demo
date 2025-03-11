package cn.afuo.javabasic.predicate;

/**
 * 学生断言接口
 */
public interface StudentPredicate {

    boolean test(Student student);

    String predicateName();

}
