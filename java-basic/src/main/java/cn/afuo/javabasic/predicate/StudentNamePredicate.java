package cn.afuo.javabasic.predicate;

import org.apache.commons.lang3.StringUtils;

/**
 * 学生姓名断言
 */
public class StudentNamePredicate implements StudentPredicate {
    @Override
    public boolean test(Student student) {
        return student != null && StringUtils.isNotBlank(student.getName()) && student.getName().equals("tianci");
    }

    @Override
    public String predicateName() {
        return "筛选学生姓名";
    }
}
