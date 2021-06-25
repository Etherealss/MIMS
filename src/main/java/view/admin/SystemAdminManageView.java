package view.admin;

import common.enums.AdminType;
import common.enums.ResultCode;
import common.exception.NotFoundException;
import common.exception.UserUnloggedException;
import common.manage.OnlineUserManage;
import controller.AdminController;
import controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.po.User;
import pojo.vo.Msg;
import util.InputUtil;
import util.ViewUtil;

import java.util.List;

/**
 * @author wtk
 * @description
 * @date 2021-06-07
 */
public class SystemAdminManageView {

    private Logger logger = LoggerFactory.getLogger("root");

    private final AdminController adminController = new AdminController();
    private final UserController userController = new UserController();

    public void enter() throws UserUnloggedException {
        User user = OnlineUserManage.getOnlineUser();
        if (!AdminType.SYSTEM_ADMIN.equals(user.getAdminType())) {
            System.out.println("您不是系统管理员！");
            return;
        }

        action();
    }

    private void action() {
        String[] options = {"查看管理员", "添加管理员", "删除管理员", "修改管理员身份", "返回"};
        while (true) {
            try {
                int op = ViewUtil.displayOptionsWithInput(options);
                switch (op) {
                    case 1:
                        showAdminList();
                        break;
                    case 2:
                        showAddAdmin();
                        break;
                    case 3:
                        showRemoveAdmin();
                        break;
                    case 4:
                        showUpdateAdminType();
                        break;
                    case 5:
                        return;
                    default:
                }
            } catch (NotFoundException e) {
                System.out.println("取消查询");
            } catch (Exception e) {
                logger.error("", e);
            }
        }
    }

    private void showAdminList() {
        Msg msg = adminController.findAllAdmin();
        if (msg.getCode() == ResultCode.SUCCESS) {
            List<User> addAdminUser = (List<User>) msg.getData("addAdminUser");
            System.out.println("一共查询到" + addAdminUser.size() + "个管理员记录");
            if (addAdminUser.size() == 0) {
                return;
            }
            for (User user : addAdminUser) {
                String info = "账号ID:" + user.getId() +
                        "\t用户名:" + user.getUsername() +
                        "\t管理员类型:" + AdminType.getTypeDescription(user.getAdminType());
                System.out.println(info);
            }
        }
    }

    private void showAddAdmin() throws NotFoundException, UserUnloggedException {
        User user = ViewUtil.findExistUser(userController, "请输入你想操作的用户：");
        if (OnlineUserManage.getOnlineUser().getId() == user.getId()) {
            System.out.println("不能修改当前管理员的管理员身份");
            return;
        }
        System.out.println(user.getAdminInfo());
        if (!AdminType.NOT_ADMIN.equals(user.getAdminType())) {
            System.out.println("该用户已经是管理员");
            return;
        }

        chooseAdminType(user);
    }

    private void showRemoveAdmin() throws NotFoundException, UserUnloggedException {
        User user = ViewUtil.findExistUser(userController, "请输入你想操作的用户：");
        if (OnlineUserManage.getOnlineUser().getId() == user.getId()) {
            System.out.println("不能修改当前管理员的管理员身份");
            return;
        }
        System.out.println(user.getAdminInfo());

        if (user.getAdminType().equals(AdminType.NOT_ADMIN)) {
            System.out.println("该用户不是管理员！");
            return;
        }
        System.out.println("是否移除该用户的管理员身份：" +
                AdminType.getTypeDescription(user.getAdminType()) +
                "1. 是\t2. 否");
        boolean confirm = InputUtil.confirm();
        if (confirm) {
            Msg msg = adminController.removeAdmin(user.getId());
            if (msg.getCode() == ResultCode.SUCCESS) {
                System.out.println("移除管理员成功！");
            } else {
                System.out.println("移除管理员失败");
            }
        }
    }

    private void showUpdateAdminType() throws NotFoundException, UserUnloggedException {
        User user = ViewUtil.findExistUser(userController, "请输入你想修改的管理员：");
        if (OnlineUserManage.getOnlineUser().getId() == user.getId()) {
            System.out.println("不能修改当前管理员的管理员身份");
            return;
        }
        System.out.println(user.getAdminInfo());
        if (user.getAdminType().equals(AdminType.NOT_ADMIN)) {
            System.out.println("该用户不是管理员");
            return;
        }

        chooseAdminType(user);
    }

    private void chooseAdminType(User user) {
        System.out.println("请选择管理员类型：");
        String[] admintypes = {"系统管理员", "用户管理员", "音乐管理员", "数据管理员", "取消"};
        int op = ViewUtil.displayOptionsWithInput(admintypes);
        switch (op) {
            case 1:
                submitUpdateAdminType(user, AdminType.SYSTEM_ADMIN);
                break;
            case 2:
                submitUpdateAdminType(user, AdminType.USER_ADMIN);
                break;
            case 3:
                submitUpdateAdminType(user, AdminType.MUSIC_ADMIN);
                break;
            case 4:
                submitUpdateAdminType(user, AdminType.DATA_ADMIN);
                break;
            case 5:
                return;
            default:
        }

    }

    /**
     * 因为每个地方都要检验一下选择的管理员身份和当前管理员身份是否相同
     * 所以抽出一个子方法
     * @param user
     * @param adminType
     */
    private void submitUpdateAdminType(User user, String adminType) {
        if (adminType.equals(user.getAdminType())) {
            System.out.println("管理员身份没有变更！");
            return;
        }
        Msg msg = adminController.updateAdmin(user.getId(), adminType);
        if (msg == null || msg.getCode() != ResultCode.SUCCESS) {
            System.out.println("修改失败！");
        } else {
            System.out.println("修改成功！");
        }
    }

}
