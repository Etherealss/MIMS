package service;

import common.exception.BadRequestException;
import common.exception.NotFoundException;
import common.exception.ServerException;
import pojo.po.RechargeRecord;
import pojo.po.User;

import java.util.List;

/**
 * @author wtk
 * @description
 * @date 2021-05-24
 */
public interface UserService {

    /**
     * 登录
     * @param user
     * @return
     * @throws
     */
    User login(User user) throws ServerException;

    /**
     * 查询用户数据
     * @param userId
     * @return
     */
    User findUser(int userId) throws NotFoundException;

    /**
     * 注册用户
     * @param user
     * @throws BadRequestException 用户已存在
     */
    void register(User user) throws BadRequestException;

    /**
     *
     * @param userId
     * @return 原有的User数据
     * @throws BadRequestException 用户不存在
     */
    User deleteUser(int userId) throws ServerException;

    /**
     *
     * @param user
     * @return 旧的User数据
     * @throws BadRequestException 用户不存在
     */
    User updateUser(User user) throws BadRequestException;

    /**
     * 修改密码
     * @param userId
     * @param oldPw
     * @param newPw
     * @throws BadRequestException 旧密码错误
     */
    void updateUserPassword(int userId, String oldPw, String newPw) throws BadRequestException;

    /**
     * 充值
     * @param userId
     * @param money
     * @throws BadRequestException
     */
    void recharge(int userId, float money) throws BadRequestException;

    List<User> findAllAdminUser() throws BadRequestException;

    void addAdmin(int userId, String adminType) throws BadRequestException;

    void updateAdminUser(int userId, String adminType) throws BadRequestException;

    void removeAdminUser(int userId) throws BadRequestException;

}
