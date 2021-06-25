package view;

import common.enums.AdminType;
import common.enums.ResultCode;
import common.exception.NotFoundException;
import common.exception.UserUnloggedException;
import common.manage.OnlineUserManage;
import controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.po.RechargeRecord;
import pojo.po.User;
import pojo.vo.Msg;
import util.InputUtil;
import util.ViewUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author wtk
 * @description 用户管理界面（包含了用户管理和个人设置功能）
 * @date 2021-05-26
 */
public class UserManageView {

    private Logger logger = LoggerFactory.getLogger("root");

    private UserController userController = new UserController();

    /**
     * 进入用户管理界面
     * @param isAdminPattern 是否为管理员模式
     * @throws UserUnloggedException
     */
    public void enter(boolean isAdminPattern) throws UserUnloggedException {
        User user = OnlineUserManage.getOnlineUser();
        if (isAdminPattern) {
            if (!AdminType.USER_ADMIN.equals(user.getAdminType())) {
                System.out.println("您不是用户管理员！");
                return;
            }
            adminAction();
        } else {
            userAction();
        }
    }

    private void adminAction() throws UserUnloggedException {
        String[] options = {"查询用户", "删除用户", "修改用户信息", "返回"};
        while (true) {
            try {
                ViewUtil.displayOptions(options);
                int op = InputUtil.inputInt();
                switch (op) {
                    case 1:
                        // 查询用户
                        showFindUser();
                        break;
                    case 2:
                        // 删除用户
                        showDeleteUser();
                        break;
                    case 3:
                        showUpdateUser(true);
                        break;
                    case 4:
                        // 返回上一页
                        return;
                    default:
                        System.out.println("输入错误！");
                }
            } catch (NotFoundException e) {
                System.out.println("用户不存在！");
            } catch (UserUnloggedException e) {
                throw e;
            } catch (Exception e) {
                logger.error("", e);
            }
        }
    }

    private void userAction() throws UserUnloggedException {
        String[] options = {"修改个人信息", "修改账号密码", "充值", "最近五条充值记录", "返回"};
        try {
            while (true) {
                int op = ViewUtil.displayOptionsWithInput(options);
                switch (op) {
                    case 1:
                        showUpdateUser(false);
                        break;
                    case 2:
                        showUpdatePassword();
                        break;
                    case 3:
                        showRecharge();
                        break;
                    case 4:
                        showRecentRecharge();
                    case 5:
                        return;
                    default:
                }
            }
        } catch (NotFoundException e) {
            System.out.println("用户不存在！");
        } catch (UserUnloggedException e) {
            throw e;
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    private void showFindUser() throws NotFoundException {
        ViewUtil.findAndShowExistUser(userController, "请输入你需要查询的用户：");
    }

    private void showDeleteUser() {
        System.out.println("请输入你的删除的账号：");
        int id = InputUtil.inputInt();
        User curUser = OnlineUserManage.getUser();
        if (curUser.getId() == id) {
            System.out.println("不能删除当前用户！");
            return;
        }
        Msg msg = userController.findUser(id);
        if (msg.getCode() == ResultCode.SUCCESS) {
            User user = (User) msg.getData("user");
            System.out.println("待删除账号查询成功！账号信息：\n" + user.getInfo());
            System.out.println("是否删除？1. 是\t2. 否");
            int confirm = InputUtil.inputInt();
            if (confirm == 1) {
                Msg deleteMsg = userController.deleteUser(id);
                if (deleteMsg.getCode() == ResultCode.SUCCESS) {
                    System.out.println("删除成功！");
                } else {
                    System.out.println("删除失败！请重试！");
                }
            }
        } else if (msg.getCode() == ResultCode.NOT_FOUND) {
            System.out.println("账号不存在！");
        } else {
            System.out.println("账号查询失败！");
        }
    }

    /**
     * @param isAdminPattern true为管理员模式，需要输入账号id，修改指定账号
     * false为个人设置，用于修改当前用户
     */
    private void showUpdateUser(boolean isAdminPattern) throws UserUnloggedException, NotFoundException {
        User user;
        if (isAdminPattern) {
            user = ViewUtil.findAndShowExistUser(userController, "请输入你需要修改的用户：");
        } else {
            user = OnlineUserManage.getOnlineUser();
        }

        while (true) {
            System.out.println("请选择你要修改的信息：");
            String[] options = {"性别", "用户名", "注册时间", "余额", "完成并保存修改", "取消修改"};
            int op = ViewUtil.displayOptionsWithInput(options);

            switch (op) {
                case 1:
                    updateSex(user);
                    break;
                case 2:
                    updateUsername(user);
                    break;
                case 3:
                    updateRegisterDate(user);
                    break;
                case 4:
                    updateBalance(user);
                    break;
                case 5:
                    userController.updateUser(user);
                    System.out.println("保存修改成功！");
                    return;
                default:
                    System.out.println("取消修改！");
                    return;
            }
        }
    }

    private void showUpdatePassword() {
        System.out.println("请输入原密码：");
        String oldPw = InputUtil.inputPassword();
        String newPw;
        while (true) {
            System.out.println("请输入新密码：");
            newPw = InputUtil.inputPassword();
            System.out.println("请再次输入新密码：");
            String newPw2 = InputUtil.inputPassword();
            if (!newPw.equals(newPw2)) {
                System.out.println("前后两次输入的新密码不一致！请重新输入。");
            } else {
                break;
            }
        }
        Msg msg = userController.updateUserPassword(OnlineUserManage.getUser().getId(), oldPw, newPw);
        if (msg.getCode() == ResultCode.SUCCESS) {
            System.out.println("修改成功！");
        } else if (msg.getCode() == ResultCode.BAD_REQUEST) {
            System.out.println("原密码错误！");
        } else {
            System.out.println("修改失败！");
        }
    }

    private void updateSex(User user) {
        System.out.println("请输入你的性别：");
        int sex = ViewUtil.displayOptionsWithInput(new String[]{"男", "女", "取消修改"});
        if (sex == 1) {
            // 男
            user.setSex(true);
        } else if (sex == 2) {
            // 女
            user.setSex(false);
        } else {
            // 取消修改
            return;
        }
        userController.updateUser(user);
        System.out.println("修改成功！用户信息：" + user.getInfo());
    }

    private void updateUsername(User user) {
        System.out.println("请输入你的新用户名：");
        String username = InputUtil.inputString();
        user.setUsername(username);
        System.out.println("修改成功！用户信息：" + user.getInfo());
    }

    private void updateRegisterDate(User user) {
        while (true) {
            System.out.println("请修改你的注册时间(yyyy.MM.dd)：");
            String datestr = InputUtil.inputString();
            SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
            try {
                Date date = format.parse(datestr);
                user.setRegisterDate(date);
                break;
            } catch (ParseException e) {
                System.out.println("输入错误！请重新输入：");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("修改成功！用户信息：" + user.getInfo());
    }

    private void updateBalance(User user) {
        System.out.println("请输入新的用户余额：");
        float v = InputUtil.inputFloat();
        user.setBalance(v);
        System.out.println("修改成功！用户信息：" + user.getInfo());
    }

    private void showRecharge() throws UserUnloggedException {
        User onlineUser = OnlineUserManage.getOnlineUser();
        System.out.println("当前账户的余额为：" + onlineUser.getBalance());
        float money = -1;
        while (money <= 0) {
            System.out.println("请输入你想要充值的金额（单位：元）：");
            money = InputUtil.inputFloat();
            if (money <= 0) {
                System.out.println("充值的金额必须大于0！请重新输入");
            }
        }
        System.out.println("是否支付：" + money + "元？\t1. 是\t2. 否");
        boolean confirm = InputUtil.confirm();
        if (confirm) {
            Msg msg = userController.recharge(onlineUser.getId(), money);
            if (msg.getCode() == ResultCode.SUCCESS) {
                System.out.println("充值成功！");
            } else {
                System.out.println("充值失败！");
            }
        } else {
            System.out.println("取消充值！");
        }
    }

    /**
     * 最近的消费记录
     */
    private void showRecentRecharge() throws UserUnloggedException {
        User onlineUser = OnlineUserManage.getOnlineUser();
        Msg msg = userController.findRecentRecharge(onlineUser.getId());
        if (msg.getCode() == ResultCode.SUCCESS) {
            List<RechargeRecord> records = (List<RechargeRecord>) msg.getData("records");
            int i = 1;
            for (RechargeRecord record : records) {
                System.out.println("-------------------第" + (i++) + "条充值记录信息 -------------------");
                System.out.println(record.getInfo());
                System.out.println("-------------------------------------------------------");
            }
        } else if (msg.getCode() == ResultCode.NOT_FOUND) {
            System.out.println("最近没有充值记录！");
        } else {
            System.out.println("获取最近充值记录失败！");
        }
    }
}
