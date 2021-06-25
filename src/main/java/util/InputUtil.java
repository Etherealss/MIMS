package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author wtk
 * @description 输入
 * @date 2021-05-24
 */
public class InputUtil {
    private static Scanner scanner = new Scanner(System.in);
    private static DateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

    /**
     * 输入一个String数据
     * @return
     */
    public static String inputString() {
        return scanner.next();
    }

    /**
     * 输入一个int数据，包括异常处理
     * @return
     */
    public static int inputInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("输入无效！请再次输入一个有效数字：");
                // 刷新输入流，否则会出现死循环
                scanner.next();
            }
        }
    }

    /**
     * 输入一个float数据，包括异常处理
     * @return
     */
    public static float inputFloat() {
        while (true) {
            try {
                return scanner.nextFloat();
            } catch (InputMismatchException e) {
                System.out.println("输入无效！请再次输入一个有效数字：");
                scanner.next();
            } catch (Exception e) {
                System.out.println("输入无效！请再次输入：");
            }
        }
    }

    /**
     * 输入一个double数据，包括异常处理
     * @return
     */
    public static double inputDouble() {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("输入无效！请再次输入一个有效数字：");
                scanner.next();
            }
        }
    }

    public static Date inputDate() {
        while (true) {
            String dateStr = scanner.next();
            try {
                Date date = sdf.parse(dateStr);
                return date;
            } catch (Exception e) {
                System.out.println("输入日期格式错误！日期格式为：yyyy.MM.dd");
            }
        }
    }

    /**
     * 输入密码
     * @return
     */
    public static String inputPassword() {
        String password;
        while (true) {
            password = InputUtil.inputString();
            if (!RegUtil.validatePassword(password)) {
                // 密码不通过
                System.out.println("密码不符合要求！密码至少包含数字和英文，" +
                        "不能包含特殊字符，长度为6-20\n请重新输入：");
            } else {
                // 通过
                return password;
            }
        }
    }

    /**
     * 输入1或2确定，1表示“是”，2表示“否”
     * @return true表示1，即“是”，false表示2，即“否”
     */
    public static boolean confirm() {
        while (true) {
            int i = InputUtil.inputInt();
            if (i == 1) {
                return true;
            } else if (i == 2) {
                return false;
            } else {
                System.out.println("输入错误！请输入：\t1. 是\t2. 否");
            }
        }
    }
}
