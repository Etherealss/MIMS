package controller;

import common.enums.ExceptionType;
import common.enums.ResultCode;
import common.exception.BadRequestException;
import common.factory.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.po.User;
import pojo.vo.Msg;
import service.UserService;

import java.util.List;

/**
 * @author wtk
 * @description
 * @date 2021-06-07
 */
public class AdminController {

    private Logger logger = LoggerFactory.getLogger("root");

    private UserService userService = ServiceFactory.getUserService();

    public Msg findAllAdmin() {
        try {
            List<User> allAdminUser = userService.findAllAdminUser();
            return Msg.success("addAdminUser", allAdminUser);
        } catch (Exception e) {
            logger.error("异常", e);
            return Msg.exception();
        }
    }

    public Msg addAdmin(int userId, String adminType) {
        try {
            userService.addAdmin(userId, adminType);
            return Msg.success();
        } catch (BadRequestException e) {
            if (ExceptionType.IS_ADMIN.equals(e.getType())) {
                return new Msg(ResultCode.IS_ADMIN, "已经是管理员了");
            } else if (ExceptionType.NO_SUCH_ADMIN_TYPE.equals(e.getType())) {
                return new Msg(ResultCode.NO_SUCH_ADMIN, "没有这种类型的管理员");
            }
        } catch (Exception e) {
            logger.error("添加管理员异常", e);
        }
        return Msg.exception();
    }

    public Msg updateAdmin(int userId, String adminType) {
        try {
            userService.updateAdminUser(userId, adminType);
            return Msg.success();
        } catch (BadRequestException e) {
            if (ExceptionType.NO_SUCH_ADMIN_TYPE.equals(e.getType())) {
                return new Msg(ResultCode.NO_SUCH_ADMIN, "没有这种类型的管理员");
            }
        } catch (Exception e) {
            logger.error("修改管理员异常", e);
        }
        return Msg.exception();
    }

    public Msg removeAdmin(int userId) {
        try {
            userService.removeAdminUser(userId);
            return Msg.success();
        } catch (BadRequestException e) {
            logger.info("不是管理员");
            return new Msg(ResultCode.NOT_ADMIN, "不是管理员");
        } catch (Exception e) {
            logger.error("", e);
        }
        return Msg.exception();
    }
}
