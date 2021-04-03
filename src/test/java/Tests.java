import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Tests {

    @BeforeEach
    public void before(){
        System.out.println("before");
    }

    @AfterEach
    public void after(){
        System.out.println("after");
    }

    @Test
    public void firstTest(){
        System.out.println("firstTest");
        Assertions.assertTrue(1==1,"Один не равен одному");
    }

    @Test
    public void secondTest(){
        System.out.println("secondTest");
        Assertions.assertTrue(1==2,"Один не равен двум");
    }

}
