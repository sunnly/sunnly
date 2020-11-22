package wang.sunnly.modules.logger.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Sunnly
 * @since 2020/10/26 0026 23:54
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MacroLogger {
    String name() ;
    String value() default "";
    String desc() default "";
}
