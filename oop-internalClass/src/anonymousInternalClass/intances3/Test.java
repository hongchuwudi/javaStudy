package anonymousInternalClass.intances3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class Test {
    public static void main(String[] args) {
        Student[] students = new Student[5];
        students[0] = new Student("xiaoming", 11, 190.1, '难');
        students[1] = new Student("xiaoming", 44, 160.1, 'n');
        students[2] = new Student("xioawang", 33, 170.1, 'n');
        students[3] = new Student("xiaotgian", 22, 180.1, 'n');
        students[4] = new Student("xiaoming", 55, 120.1, '难');

        //  需求:
        //  public static void sort(T[]a,new Comparator<T> c)
        //  comparator 比较对象
//        Arrays.sort(students, new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//
////                if(o1.getAge() >  o2.getAge())  return 1;
////                else if(o1.getAge() <  o2.getAge()) return -1;
////                else return 0;
//
//                //  减少if和else的冗余写法
//                return Integer.compare(o1.getAge(), o2.getAge());
//
//                //  可以range
////                return o1.getAge() - o2.getAge();
//            }
//        });


        Arrays.stream(students)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(Student::getAge))
                .forEach(s -> System.out.println(s.getName() + ' ' + s.getAge() + ' ' + s.getSex()));
    }
}
