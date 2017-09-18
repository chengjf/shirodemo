package com.chengjf.shiro.demo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootApp.class})
public class MainTest {


//    @Autowired
//    private IHelloService helloService;
//
//    @Before
//    public void init(){
//        System.out.println("init");
//    }
//
//    @After
//    public void destroy(){
//        System.out.println("destroy");
//    }
//
    @Test
    public void test(){
        System.out.println("test");
//        System.out.println(helloService.sayHello("chengjf"));

//        Assert.assertEquals("Hello chengjf",helloService.sayHello("chengjf"));
    }
}
