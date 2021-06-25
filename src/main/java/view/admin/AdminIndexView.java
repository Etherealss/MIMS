package view.admin;

import common.exception.UserUnloggedException;
import common.manage.OnlineUserManage;
import util.ViewUtil;
import view.SignView;
import view.UserManageView;

/**
 * @author wtk
 * @description
 * @date 2021-06-07
 */
public class AdminIndexView {

    private final SignView signView = new SignView();
    private final UserManageView userManageView = new UserManageView();
    private final SystemAdminManageView adminManageView = new SystemAdminManageView();
    private final MusicManageView musicManageView = new MusicManageView();
    private final DataManageView dataManageView = new DataManageView();

    public void enter() throws UserUnloggedException {
        action();
    }

    private void action() throws UserUnloggedException {
        String[] options = {"用户管理", "系统管理", "音乐管理", "数据文件管理", "退出登录"};
        while (true) {
            int op = ViewUtil.displayOptionsWithInput(options);
            switch (op) {
                case 1:
                    userManageView.enter(true);
                    break;
                case 2:
                    adminManageView.enter();
                    break;
                case 3:
                    musicManageView.enter();
                    break;
                case 4:
                    dataManageView.enter();
                    break;
                case 5:
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
