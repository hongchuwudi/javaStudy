package metaAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})     //  表示注解的目标为方法(注解的约束条件)
@Retention(RetentionPolicy.RUNTIME)                 //  表示注解的保留策略:编译运行时(一直活着)
public @interface MyTarget {

}
