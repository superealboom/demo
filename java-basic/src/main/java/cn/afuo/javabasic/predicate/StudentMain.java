package cn.afuo.javabasic.predicate;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试类
 */
public class StudentMain {

    public static void main(String[] args) {
        List<Student> studentList = getStudentList();
        // 去掉年龄不符合的
        filterStudent(studentList, new StudentAgePredicate());
        // 去掉姓名不符合的
        filterStudent(studentList, new StudentNamePredicate());
    }


    public static void filterStudent(List<Student> studentList, StudentPredicate studentPredicate) {
        studentList.removeIf(student -> !studentPredicate.test(student));
        System.out.println(studentPredicate.predicateName());
        studentList.forEach(student -> System.out.println(student.getName() + "," + student.getAge()));
    }


    public static List<Student> getStudentList() {
        List<Student> studentList = new ArrayList<>();
        // age name 都不符合
        Student student1 = new Student();
        student1.setAge(10);
        student1.setName("test");
        studentList.add(student1);
        // age name 都符合
        Student student2 = new Student();
        student2.setAge(28);
        student2.setName("tianci");
        studentList.add(student2);
        // age 符合，name 不符合
        Student student3 = new Student();
        student3.setAge(28);
        student3.setName("test");
        studentList.add(student3);
        // name 符合， age 不符合
        Student student4 = new Student();
        student4.setAge(10);
        student4.setName("tianci");
        studentList.add(student4);
        return studentList;
    }

}
