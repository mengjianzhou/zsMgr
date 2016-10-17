package annotation.test1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by zhaobo on 2016/9/27 0027.
 */
public class AnnoTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = (UserService) context.getBean("userService");

        System.out.println(userService.sayHi("world!"));

        context.close();

    }
}
