package com.zb.service;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.web.context.WebApplicationContext;

/**
 * Created by zhaobo on 2016/10/14 0014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath*:ApplicationContext.xml")
@WebAppConfiguration
public class LoginTest {

    private Log log = LogFactory.getLog(LoginTest.class);

//    @Autowired
//    WebApplicationContext applicationContext;

    @Before
    public void setUp(){

        log.info("Begin init");
    }

    @Test
    public void testLogin(){

    }

}















