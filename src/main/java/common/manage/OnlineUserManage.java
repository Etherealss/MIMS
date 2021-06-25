package common.manage;

import common.enums.AdminType;
import common.exception.BadRequestException;
import common.exception.UserUnloggedException;
import common.exception.UserhasLoggedException;
import pojo.po.User;

/**
 * @author wtk
 * @description 在线用户管理，单线程环境
 * @date 2021-05-26
 */
public class OnlineUserManage {

    /**
     * 在线用户
     */
    private static User user;

    public static void addUser(User user) {
        OnlineUserManage.user = user;
    }

    public static User getUser() {
        return user;
    }

    public static User getOnlineUser() throws UserUnloggedException {
        if (user == null) {
            throw new UserUnloggedException();
        }
        return user;
    }

    public static void removeUser() {
        user = null;
    }

    /**
     * @return 在线为true
     */
    public static boolean isOnline() {
        return user != null;
    }

    /**
     * 要求在线
     * @throws UserUnloggedException 未登录则报异常
     */
    public static void requireOnline() throws UserUnloggedException {
        if (user == null) {
            throw new UserUnloggedException();
        }
    }

    /**
     * 要求不在线
     * @throws UserhasLoggedException 已登录则报异常
     */
    public static void requireOutline() throws UserhasLoggedException {
        if (user != null) {
            throw new UserhasLoggedException();
        }
    }
}
