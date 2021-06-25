package util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class InputUtilTest {

    private Logger logger = LoggerFactory.getLogger(InputUtilTest.class);


    @Test
    public void testInputDate() throws Exception {
        System.out.println(new Date().getTime());
    }
}