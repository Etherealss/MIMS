package view;

import common.exception.UserUnloggedException;
import common.manage.OnlineUserManage;
import util.ViewUtil;


/**
 * @author wtk
 * @description
 * @date 2021-06-07
 */
public class UserIndexView {

    private final SignView signView = new SignView();
    private final MusicView musicView = new MusicView();
    private final UserManageView userManageView = new UserManageView();


    public void enter() throws UserUnloggedException {
        action();
    }

    private void action() throws UserUnloggedException {
        String[] options = {"进入音乐区", "个人中心", "退出登录"};
        while (true) {
            int op = ViewUtil.displayOptionsWithInput(options);
            switch (op) {
                case 1:
                    musicView.enter();
                    break;
                case 2:
                    // 个人设置
                    userManageView.enter(false);
                    break;
                case 3:
                    if (OnlineUserManage.isOnline()) {
                        // 当前在线，选择1表示退出登录
                        showLogout();
                    }
                    return;
                default:
            }
        }
    }

    /**
     * 退出登录
     */
    private void showLogout() throws UserUnloggedException {
        signView.logout();
        System.out.println("退出登录成功！");
    }

}
