package util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegUtilTest {

    @Test
    public void testValidatePassword() throws Exception {
        System.out.println(RegUtil.validatePassword("1a1a1aa1"));
        System.out.println(RegUtil.validatePassword("a21bcba1"));
        System.out.println(RegUtil.validatePassword("a1545645"));

        System.out.println(RegUtil.validatePassword("abc001."));
        System.out.println(RegUtil.validatePassword("abc001+"));
        System.out.println(RegUtil.validatePassword("abc001\\"));
        System.out.println(RegUtil.validatePassword("-abc001"));
        System.out.println(RegUtil.validatePassword("1111101"));
        System.out.println(RegUtil.validatePassword("adadsd"));
    }
}