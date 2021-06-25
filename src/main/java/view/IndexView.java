package view;

import common.exception.UserUnloggedException;
import common.exception.UserhasLoggedException;
import common.manage.OnlineUserManage;
import util.InputUtil;
import util.ViewUtil;
import view.admin.*;

import javax.swing.text.View;

/**
 * @author wtk
 * @description 主页面
 * @date 2021-05-26
 */
public class IndexView {

    private final SignView signView = new SignView();
    private final UserIndexView userIndexView = new UserIndexView();
    private final AdminIndexView adminIndexView = new AdminIndexView();

    private void hello() {
        System.out.println("欢迎！");
    }

    public void enter() {
        hello();
        while (true) {
            String[] options = {"登录注册", "退出"};
            int op = ViewUtil.displayOptionsWithInput(options);
            if (op == 1) {
                // 登录注册
                showSign();
            }
            if (op == 2) {
                System.out.println("退出系统！");
                break;
            }
        }
    }

    /**
     * 登录注册操作
     */
    private void showSign() {
        String[] options = {"用户登录", "管理员登录", "注册新用户", "取消"};
        int op = ViewUtil.displayOptionsWithInput(options);
        switch (op) {
            case 1:
                boolean userLogin = signView.login(false);
                if (userLogin) {
                    try {
                        userIndexView.enter();
                    } catch (UserUnloggedException e) {
                        System.out.println("您尚未登录");
                    }
                }
                break;
            case 2:
                boolean adminLogin = signView.login(true);
                if (adminLogin) {
                    try {
                        adminIndexView.enter();
                    } catch (UserUnloggedException e) {
                        System.out.println("您尚未登录");
                    }
                }
                break;
            case 3:
                signView.register();
                break;
            case 4:
                return;
            default:
        }
    }
}
