package demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)             //  约束函数
@Retention(RetentionPolicy.RUNTIME)     //  表示注解的保留策略
public @interface MyTests {
    int count() default 3;
}
