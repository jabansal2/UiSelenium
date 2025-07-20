package TestScripts;

import org.testng.annotations.*;

@Test
public class trialsTest {

    @BeforeClass
    public void ex_before_class(){
        System.out.println("Before class executed");
    }

    @BeforeSuite
    public void ex_before_suite(){
        System.out.println("Before Suite executed");
    }

    @BeforeTest
    public void ex_before_test(){
        System.out.println("Before Test executed");
    }

    @BeforeMethod
    public void ex_before_method(){
        System.out.println("Before Method executed");
    }

    public void test1(){
        System.out.println("test 1 executed");
        System.out.println("----------------");
    }

    public void test2(){
        System.out.println("test 2 executed");
    }
}
