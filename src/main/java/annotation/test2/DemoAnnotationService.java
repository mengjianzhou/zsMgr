package annotation.test2;

import org.springframework.stereotype.Service;

/**
 * Created by zhaobo on 2016/9/27 0027.
 */
@Service
public class DemoAnnotationService {

    @Action(name="注解式拦截的add操作")
    public void add(){}

}
