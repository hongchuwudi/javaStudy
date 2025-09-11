package Test;

//  自定义注解
public @interface A {
    String names = null;

    int age() default 2;

    String[] address();
}
