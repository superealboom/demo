package cn.afuo.javabasic.predicate;

/**
 * 学生年龄断言
 */
public class StudentAgePredicate implements StudentPredicate {
    @Override
    public boolean test(Student student) {
        return student != null && student.getAge() != null && student.getAge().equals(28);
    }

    public String predicateName() {
        return "筛选学生年龄";
    }
}
