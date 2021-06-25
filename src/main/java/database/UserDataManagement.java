package database;

import database.base.BaseDataManagementByList;
import pojo.po.User;

/**
 * @author wtk
 * @description 用户数据管理
 * @date 2021-05-24
 */
public class UserDataManagement extends BaseDataManagementByList<User> {

    private static UserDataManagement management = new UserDataManagement();
    private UserDataManagement(){}
    public static UserDataManagement getManagement() {
        return management;
    }
}
