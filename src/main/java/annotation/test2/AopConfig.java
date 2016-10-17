package annotation.test2;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by zhaobo on 2016/9/27 0027.
 */
@Configuration
@ComponentScan("annotation.test2")
@EnableAspectJAutoProxy
public class AopConfig {
}
