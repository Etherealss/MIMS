package dao;

import pojo.po.User;

import java.util.List;

/**
 * @author wtk
 * @description
 * @date 2021-05-24
 */
public interface UserDao {

    /**
     * 添加新用户
     * @param user
     * @return
     */
    boolean insertUser(User user);

    /**
     * 直接查询用户
     * @param userId
     * @return
     */
    User findUser(int userId);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    boolean deleteUser(int userId);

    /**
     * 修改用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAllUser();

}
