package util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileChooseUtilTest {

    private Logger logger = LoggerFactory.getLogger(FileChooseUtilTest.class);


    @Test
    public void testChooseSavePath() throws Exception {
        FileChooseUtil.chooseSavePath("12321");
    }
}