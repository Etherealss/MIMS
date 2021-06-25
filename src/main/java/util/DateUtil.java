package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wtk
 * @description
 * @date 2021-06-23
 */
public class DateUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

    public static String dateFormat(Date date) {
        return sdf.format(date);
    }
}
