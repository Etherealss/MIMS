package service.impl;

import common.enums.AdminType;
import common.enums.ExceptionType;
import common.exception.BadRequestException;
import common.exception.NotFoundException;
import common.exception.ServerException;
import common.factory.DaoFactory;
import dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.po.RechargeRecord;
import pojo.po.User;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wtk
 * @description 负责业务流程的实现
 * @date 2021-05-24
 */
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger("root");

    private UserDao userDao = DaoFactory.getUserDao();

    @Override
    public User login(User user) throws BadRequestException {
        // 查询用户并判断是否存在该用户，不存在则报异常
        User existUser = getExistUser(user.getId());
        // 检查密码是否匹配
        if (!user.getPassword().equals(existUser.getPassword())) {
            // 密码不匹配
            throw new BadRequestException(ExceptionType.WRONG_PASSWORD, "密码错误");
        }
        return existUser;
    }

    @Override
    public User findUser(int userId) throws NotFoundException {
        return getExistUser(userId);
    }

    @Override
    public void register(User user) throws BadRequestException {
        User existUser = userDao.findUser(user.getId());
        if (existUser != null) {
            throw new BadRequestException(ExceptionType.USER_EXISTED, "用户已存在");
        }

        userDao.insertUser(user);
    }

    @Override
    public User deleteUser(int userId) throws ServerException {
        User oldUser = getExistUser(userId);
        boolean b = userDao.deleteUser(userId);
        if (!b) {
            throw new ServerException("删除用户异常");
        }
        return oldUser;
    }

    @Override
    public User updateUser(User user) throws NotFoundException {
        User oldUser = getExistUser(user.getId());
        userDao.updateUser(user);
        return oldUser;
    }

    @Override
    public void updateUserPassword(int userId, String oldPw, String newPw) throws BadRequestException {
        User user = getExistUser(userId);
        if (!user.getPassword().equals(oldPw)) {
            // 密码不匹配
            throw new BadRequestException(ExceptionType.WRONG_PASSWORD, "旧密码错误");
        }
        user.setPassword(newPw);
        userDao.updateUser(user);
    }

    @Override
    public void recharge(int userId, float money) throws BadRequestException {
        User user = getExistUser(userId);
        if (money <= 0) {
            throw new BadRequestException(ExceptionType.ERROR_PARAM, "充值额度要求大于0");
        }
        user.setBalance(user.getBalance() + money);
        userDao.updateUser(user);
    }

    @Override
    public List<User> findAllAdminUser() {
        List<User> allUser = userDao.findAllUser();
        List<User> adminUser = new ArrayList<>();
        for (User user : allUser) {
            if (!user.getAdminType().equals(AdminType.NOT_ADMIN)) {
                adminUser.add(user);
            }
        }
        return adminUser;
    }

    @Override
    public void addAdmin(int userId, String type) throws BadRequestException {
        User user = userDao.findUser(userId);
        if (!user.getAdminType().equals(AdminType.NOT_ADMIN)) {
            throw new BadRequestException(ExceptionType.IS_ADMIN, "已经是管理员了，不能重复添加管理员");
        }

        if (AdminType.getTypeDescription(type) == null) {
            throw new BadRequestException(ExceptionType.NO_SUCH_ADMIN_TYPE, "没有这种管理员身份类型");
        }
        user.setAdminType(type);
        userDao.updateUser(user);
    }

    @Override
    public void updateAdminUser(int userId, String adminType) throws BadRequestException {
        User user = userDao.findUser(userId);
        if (AdminType.getTypeDescription(adminType) == null) {
            throw new BadRequestException(ExceptionType.NO_SUCH_ADMIN_TYPE, "没有这种管理员身份类型");
        }
        user.setAdminType(adminType);
        userDao.updateUser(user);
    }

    @Override
    public void removeAdminUser(int userId) throws BadRequestException {
        User user = userDao.findUser(userId);
        if (user.getAdminType().equals(AdminType.NOT_ADMIN)) {
            throw new BadRequestException(ExceptionType.NOT_ADMIN, "不是管理员，不能移除该身份");
        }
        user.setAdminType(AdminType.NOT_ADMIN);
        userDao.updateUser(user);
    }

    /**
     * 用户是否存在
     * @param userId
     * @return
     * @throws NotFoundException 用户不存在
     */
    private User getExistUser(int userId) throws NotFoundException {
        User user = userDao.findUser(userId);
        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }
}
