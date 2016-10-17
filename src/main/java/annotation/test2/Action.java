package annotation.test2;

import java.lang.annotation.*;

/**
 * Created by zhaobo on 2016/9/27 0027.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String name();
}
