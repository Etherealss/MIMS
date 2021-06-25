package controller;

import common.enums.AdminType;
import common.enums.ResultCode;
import common.enums.ExceptionType;
import common.exception.NotFoundException;
import common.exception.ServerException;
import common.manage.OnlineUserManage;
import pojo.po.RechargeRecord;
import pojo.vo.Msg;
import common.exception.BadRequestException;
import common.factory.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.po.User;
import service.RechargeRecordService;
import service.UserService;

import java.util.List;


/**
 * @author wtk
 * @description 用户控制层
 * 负责数据接收，以及结果处理
 * @date 2021-05-24
 */
public class UserController {

    private Logger logger = LoggerFactory.getLogger("root");

    private UserService userService = ServiceFactory.getUserService();
    private RechargeRecordService rechargeRecordService = ServiceFactory.getRechargeRecordService();


    /**
     * 登录
     * @param userId
     * @param password
     * @return
     */
    public Msg login(int userId, String password, boolean isAdminLogin) {
        logger.trace("登录");

        Msg msg = new Msg();
        try {
            User loginUser = userService.login(new User(userId, password));
            // 如果是管理员登录，但该用户不是管理员，则进入if
            if (isAdminLogin && AdminType.NOT_ADMIN.equals(loginUser.getAdminType())) {
                msg.setCode(ResultCode.NOT_ADMIN);
                msg.setMessage("账号验证通过但不是管理员，不允许管理员登录");
            } else {
                // 如果不是管理员登录，或者管理员登录时用户是管理员，则返回成功
                msg.setCode(ResultCode.SUCCESS);
                msg.setMessage("login successfully");
                msg.addData("user", loginUser);
            }

        } catch (NotFoundException e) {
            msg.setCode(404);
            msg.setMessage("user not found");
        } catch (BadRequestException e) {
            if (ExceptionType.WRONG_PASSWORD.equals(e.getType())) {
                msg.setCode(401);
                msg.setMessage("password wrong");
            } else {
                msg.setCode(500);
                msg.setMessage("登录结果异常");
            }

        } catch (Exception e) {
            logger.error("登录异常", e);
        }
        return msg;
    }


    /**
     * 查找用户
     * @param userId
     * @return
     */
    public Msg findUser(int userId) {
        try {
            User user = userService.findUser(userId);
            // 账号存在
            return Msg.success("user", user);
        } catch (NotFoundException e) {
            return Msg.userNotFound();
        } catch (Exception e) {
            logger.error("查询用户异常", e);
            return Msg.exception("查询用户异常");
        }
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    public Msg register(User user) {
        try {
            userService.register(user);
            return Msg.success();
        } catch (BadRequestException e) {
            if (ExceptionType.USER_EXISTED.equals(e.getType())) {
                return new Msg(ResultCode.PRECONDITION_FAILED, "账号已存在");
            } else {
                return Msg.exception();
            }
        }
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    public Msg deleteUser(int userId) {
        try {
            User user = userService.deleteUser(userId);
            assert user != null;
            return Msg.success();
        } catch (NotFoundException e) {
            return Msg.userNotFound();
        } catch (ServerException e) {
            return Msg.exception("删除用户异常");
        } catch (Exception e) {
            logger.error("删除用户异常", e);
            return Msg.exception(e.getMessage());
        }
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    public Msg updateUser(User user) {
        try {
            User oldUser = userService.updateUser(user);
            return Msg.success("user", oldUser);
        } catch (NotFoundException e) {
            return Msg.userNotFound();
        } catch (BadRequestException e) {
            return Msg.exception();
        } catch (Exception e) {
            logger.error("更新用户异常", e);
            return Msg.exception();
        }
    }

    /**
     * @param userId
     * @param oldPw
     * @param newPw
     * @return
     */
    public Msg updateUserPassword(int userId, String oldPw, String newPw) {
        try {
            userService.updateUserPassword(userId, oldPw, newPw);
            return Msg.success();
        } catch (NotFoundException e) {
            return Msg.userNotFound();
        } catch (BadRequestException e) {
            if (e.getType().equals(ExceptionType.WRONG_PASSWORD)) {
                return new Msg(ResultCode.BAD_REQUEST, "原密码错误");
            } else {
                return Msg.exception();
            }
        } catch (Exception e) {
            logger.error("修改用户密码异常", e);
            return Msg.exception(e);
        }
    }

    public Msg recharge(int userId, float money) {
        try {
            userService.recharge(userId, money);
            rechargeRecordService.recharge(userId, money);
            return Msg.success();
        } catch (NotFoundException e) {
            return Msg.userNotFound();
        } catch (BadRequestException e) {
            if (e.getType().equals(ExceptionType.ERROR_PARAM)) {
                return Msg.exception("充值额度要求大于0");
            }
            return Msg.exception(e);
        }
    }

    public Msg findRecentRecharge(int userId) {
        List<RechargeRecord> records = rechargeRecordService.findRecentRecharge(userId);
        if (records.size() == 0) {
            return Msg.notFound("最近没有充值记录");
        }
        return Msg.success("records", records);
    }
}
