package dao.impl;

import dao.UserDao;
import database.UserDataManagement;
import pojo.po.User;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.List;

/**
 * @author wtk
 * @description 负责数据的访问
 * @date 2021-05-24
 */
public class UserDaoImpl implements UserDao {

    private UserDataManagement management = UserDataManagement.getManagement();

    @Override
    public boolean insertUser(User user) {
        return management.add(user) == null;
    }

    @Override
    public User findUser(int userId) {
        return management.find(userId);
    }

    @Override
    public boolean deleteUser(int userId) {
        // 返回不为null则说明原本就有数据，返回true
        return management.delete(userId) != null;
    }

    @Override
    public void updateUser(User user) {
        management.update(user);
    }

    @Override
    public List<User> findAllUser() {
        return management.findAll();
    }
}
