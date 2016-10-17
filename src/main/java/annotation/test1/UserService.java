package annotation.test1;

import org.springframework.stereotype.Service;

/**
 * Created by zhaobo on 2016/9/27 0027.
 */
@Service
public class UserService {

    public String  sayHi(String str){
        return "hello "+ str;
    }
}
