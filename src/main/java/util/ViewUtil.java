package util;

import common.enums.ResultCode;
import common.exception.NotFoundException;
import controller.MusicController;
import controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.po.Music;
import pojo.po.User;
import pojo.vo.Msg;

/**
 * @author wtk
 * @description
 * @date 2021-05-26
 */
public class ViewUtil {

    private static final Logger logger = LoggerFactory.getLogger("root");

    /**
     * @param user
     * @return 形如：username [userid]
     */
    public static String getUserInfo(User user) {
        return user.getUsername() + "[" + user.getId() + "]";
    }


    public static void displayOptions(String[] options) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < options.length; i++) {
            builder.append(i + 1).append(". ").append(options[i]).append("\t");
        }
        builder.append("\n请选择：");
        System.out.println(builder.toString());
    }

    public static int displayOptionsWithInput(String[] options) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < options.length; i++) {
            builder.append(i + 1).append(". ").append(options[i]).append("\t");
        }
        builder.append("\n请选择：");

        boolean isWrong = true;
        int op = 0;
        while (isWrong) {
            System.out.println(builder.toString());
            op = InputUtil.inputInt();
            if (op < 1 || op > options.length) {
                System.out.println("输入错误！请重新输入！");
                isWrong = true;
            } else {
                isWrong = false;
            }
        }
        return op;
    }

    /**
     * 查找要操作的用户
     * 因为输入的id可能没有匹配的用户，经常需要写判断语句，同时需要写while循环
     * 所以把这一部分都抽出来封装为方法
     * @param userController
     * @param discription 操作的用户说明文字
     * @return
     * @throws NotFoundException 没找到用户
     */
    public static User findAndShowExistUser(UserController userController,
                                            String discription) throws NotFoundException {
        User user = findExistUser(userController, discription);
        System.out.println("用户账号信息：\n" + user.getInfo());
        return user;
    }

    /**
     * 查找要操作的用户
     * 因为输入的id可能没有匹配的用户，经常需要写判断语句，同时需要写while循环
     * 所以把这一部分都抽出来封装为方法
     * @param userController
     * @param discription 操作的用户说明文字
     * @return
     * @throws NotFoundException 没找到用户
     */
    public static User findExistUser(UserController userController,
                                     String discription) throws NotFoundException {
        while (true) {
            System.out.println(discription + "(输入-1取消操作)");
            int id = InputUtil.inputInt();
            if (id == -1) {
                throw new NotFoundException("用户查询失败");
            }
            Msg msg = userController.findUser(id);
            if (msg.getCode() == ResultCode.SUCCESS) {
                User user = (User) msg.getData("user");

                return user;

            } else if (msg.getCode() == ResultCode.NOT_FOUND) {
                System.out.println("账号不存在！");
            } else {
                System.out.println("账号查询失败！");
            }
        }
    }


    /**
     * 查找要操作的音乐
     * @param musicController
     * @param discription
     * @return
     */
    public static Music findAndShowExistMusic(MusicController musicController, String discription) {
        while (true) {
            System.out.println(discription + "(输入-1取消操作)");
            int id = InputUtil.inputInt();
            if (id == -1) {
                return null;
            }
            Msg msg = musicController.findMusic(id);

            if (msg.getCode() == ResultCode.SUCCESS) {
                Music music = (Music) msg.getData("music");
                System.out.println("音乐信息：\n" + music.getInfo());
                return music;
            } else if (msg.getCode() == ResultCode.NOT_FOUND) {
                System.out.println("音乐不存在！");
            } else {
                System.out.println("音乐查询失败！");
            }
        }
    }
}
