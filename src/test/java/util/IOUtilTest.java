package util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class IOUtilTest {


    @Test
    public void testReadTxt() throws Exception {
        List<String> strings = IOUtil.readTxt("src\\main\\resources\\UserData.txt");
        for (String string : strings) {
            System.out.println(string);
        }
    }
}