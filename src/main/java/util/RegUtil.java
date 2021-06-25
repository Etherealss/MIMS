package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wtk
 * @description
 * @date 2021-05-26
 */
public class RegUtil {

    /**
     * 检查密码
     * @param password
     * @return
     */
    public static boolean validatePassword(String password) {
        String pattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(password);
        return m.find();
    }
}
