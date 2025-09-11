package metaAnnotation;

@MyTarget()
public class metaTest {
    @MyTarget()
    public static void main(String[] args) {
//        @MyTarget 报错
        int test;
    }
}
