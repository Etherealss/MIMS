package view;

import common.enums.AdminType;
import common.enums.ResultCode;
import common.exception.UserUnloggedException;
import pojo.vo.Msg;
import common.factory.ControllerFactory;
import common.manage.OnlineUserManage;
import controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.po.User;
import util.ViewUtil;
import util.InputUtil;
import util.RegUtil;

import java.util.Date;

/**
 * @author wtk
 * @description 登录注册页面
 * @date 2021-05-24
 */
public class SignView {

    private Logger logger = LoggerFactory.getLogger("root");

    private UserController userController = ControllerFactory.getUserController();

    /**
     * 登录页面模拟
     */
    public boolean login(boolean isAdminLogin) {
        if (OnlineUserManage.getUser() != null) {
            System.out.println("您已经登录，是否退出并重新登录？\n1. 重新登录\n2. 取消登录");
            int continueLog = InputUtil.inputInt();
            if (continueLog == 0) {
                return false;
            }
        }
        System.out.println("登录：请输入账号ID：");
        int userId = InputUtil.inputInt();
        System.out.println("请输入账号密码：");
        String password = InputUtil.inputString();

        Msg loginmsg = userController.login(userId, password, isAdminLogin);

        int code = loginmsg.getCode();
        switch (code) {
            case ResultCode.SUCCESS:
                User user = (User) loginmsg.getData("user");
                System.out.println("登录成功！");
                if (isAdminLogin) {
                    System.out.println("欢迎管理员：" + ViewUtil.getUserInfo(user));
                } else {
                    System.out.println("欢迎用户：" + ViewUtil.getUserInfo(user));
                }
                OnlineUserManage.addUser(user);
                return true;

            case ResultCode.UNAUTHORIZED:
                System.out.println("密码错误！请检查");
                break;

            case ResultCode.NOT_ADMIN:
                System.out.println("不是管理员账号，不允许管理员登录");
                break;

            case ResultCode.NOT_FOUND:
                System.out.println("账号不存在！");
                break;

            default:
                System.out.println("服务器繁忙");
        }
        return false;
    }

    /**
     * 注册页面模拟
     */
    public void register() {
        System.out.println("注册：请输入想要注册的账号id（仅包含数字，输入-1退出）：");
        int id;
        while (true) {
            id = InputUtil.inputInt();

            if (id == -1) {
                System.out.println("退出注册");
                return;
            }

            // 检查要注册的账号id是否存在
            Msg msg = userController.findUser(id);
            if (msg.getCode() == 200) {
                // 账号已存在
                System.out.println("账号已存在，请重新输入");
            } else if (msg.getCode() == 404) {
                break;

            } else {
                System.out.println("服务器繁忙！请重新输入");
            }
        }

        System.out.println("请输入账号密码（密码至少包含数字和英文，长度6-20）：");
        String password;
        while (true) {
            password = InputUtil.inputString();
            if (!RegUtil.validatePassword(password)) {
                // 密码不通过
                System.out.println("密码不符合要求！密码至少包含数字和英文，" +
                        "不能包含特殊字符，长度为6-20\n请重新输入：");
            } else {
                // 通过
                break;
            }
        }

        System.out.println("请输入用户名：");
        String username = InputUtil.inputString();
        boolean sex;
        while (true) {
            System.out.println("请输入性别：（男或女）");
            String sexStr = InputUtil.inputString();
            if ("男".equals(sexStr)) {
                sex = true;
                break;
            } else if ("女".equals(sexStr)) {
                sex = false;
                break;
            } else {
                System.out.println("输入错误！");
            }
        }

        User user = new User();
        user.setId(id);
        user.setPassword(password);
        user.setUsername(username);
        user.setSex(sex);
        user.setRegisterDate(new Date());
        user.setAdminType(AdminType.NOT_ADMIN);

        Msg resultMsg = userController.register(user);
        if (resultMsg.getCode() == ResultCode.SUCCESS) {
            System.out.println("注册成功！欢迎新用户：" + ViewUtil.getUserInfo(user));
        } else if (resultMsg.getCode() == ResultCode.PRECONDITION_FAILED) {
            System.out.println("注册账号已存在！");
        } else {
            System.out.println("注册失败！请重试！");
        }
    }

    /**
     * 用户登出
     */
    public void logout() throws UserUnloggedException {
        OnlineUserManage.requireOnline();
        OnlineUserManage.removeUser();
    }
}