import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringUtilTest {
    //  测量类 :junit单元测试框架 对业务类中的业务进行测试
    //  测试方法: 必须是公开的public 无参无返回值
    //  测试方法必须加上@Test 注解(Junit框架的核心步骤)
    @Test
    public void testPrintNumber() {
        StringUtil.printNumber("张三abc");
        StringUtil.printNumber("");
        StringUtil.printNumber(null);
    }

    @Test
    public void testGetMaxIndex() {
        int idx = StringUtil.getMaxIndex("fdadfasfa");
        int idx2 = StringUtil.getMaxIndex("");
        int idx3 = StringUtil.getMaxIndex(null);

        //  断言assert
        Assertions.assertEquals("fdadfasfa".length() - 1, idx);
        Assertions.assertEquals("".length(), idx2);
        Assertions.assertEquals(0, idx3);
    }
}
