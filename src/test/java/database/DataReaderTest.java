package database;

import org.junit.Test;
import pojo.po.User;
import util.DataReader;

import java.util.Map;

public class DataReaderTest {

    @Test
    public void testInitUserData() throws Exception {
        DataReader reader = new DataReader();
        Map<Integer, User> userMap = reader.initUserData("src\\main\\resources\\UserData.txt");
        for (Map.Entry<Integer, User> entry : userMap.entrySet()) {
            System.out.println("key:\t" + entry.getKey() + "\tvalue:\t" + entry.getValue());
        }
    }
}