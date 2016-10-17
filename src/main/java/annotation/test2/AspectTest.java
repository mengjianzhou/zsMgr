package annotation.test2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by zhaobo on 2016/9/27 0027.
 */
public class AspectTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);

//        DemoAnnotationService demoAnnotationService = context.getBean(DemoAnnotationService.class);
//        demoAnnotationService.add();
//
//        DemoMethodService demoMethodService = context.getBean(DemoMethodService.class);
//        demoMethodService.add();
//
//        context.close();

    }
}
