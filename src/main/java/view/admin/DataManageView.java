package view.admin;

import common.enums.AdminType;
import common.enums.ResultCode;
import common.exception.UserUnloggedException;
import common.manage.OnlineUserManage;
import controller.DataController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.po.User;
import pojo.vo.Msg;
import util.FileChooseUtil;
import util.ViewUtil;

import javax.swing.*;

/**
 * @author wtk
 * @description
 * @date 2021-06-03
 */
public class DataManageView {
    private Logger logger = LoggerFactory.getLogger("root");

    private DataController dataController = new DataController();

    public void enter() throws UserUnloggedException {
        User user = OnlineUserManage.getOnlineUser();
        if (!AdminType.DATA_ADMIN.equals(user.getAdminType())) {
            System.out.println("您不是数据管理员！没有访问权限");
            return;
        }

        action();
    }

    private void action() {
        String[] options = {"保存数据", "导出数据文件", "取消"};
        int op = ViewUtil.displayOptionsWithInput(options);

        if (op == 1) {
            showSaveData();
        } else if (op == 2) {
            try {
                showExportData();
            } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException e) {
                logger.warn("导出文件异常", e);
            }
        }
    }

    /**
     * 展示保存数据的操作选项
     */
    private void showSaveData() {
        String[] options = {"保存用户数据", "保存音乐数据", "保存消费记录", "保存充值记录", "取消"};
        int op = ViewUtil.displayOptionsWithInput(options);
        Msg msg = null;
        switch (op) {
            case 1:
                msg = dataController.saveData("user");
                break;
            case 2:
                msg = dataController.saveData("music");
                break;
            case 3:
                msg = dataController.saveData("consumption");
                break;
            case 4:
                msg = dataController.saveData("recharge");
                break;
            case 5:
                return;
            default:
        }
        if (msg != null && msg.getCode() == ResultCode.SUCCESS) {
            System.out.println("保存成功！");
        } else {
            System.out.println("保存失败！");
        }
    }

    /**
     * 展示导出数据的操作选项
     */
    private void showExportData() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        String[] options = {"导出用户数据", "导出音乐数据", "导出消费记录", "导出充值记录", "取消"};
        int op = ViewUtil.displayOptionsWithInput(options);
        Msg msg = null;
        String savePath = null;
        switch (op) {
            case 1:
                savePath = FileChooseUtil.chooseSavePath("UserData.txt");
                if (savePath != null) {
                    msg = dataController.exportData("user", savePath);
                }
                break;
            case 2:
                savePath = FileChooseUtil.chooseSavePath("MusicData.txt");
                if (savePath != null) {
                    msg = dataController.exportData("music", savePath);
                }
                break;
            case 3:
                savePath = FileChooseUtil.chooseSavePath("ConsumingRecord.txt");
                if (savePath != null) {
                    msg = dataController.exportData("consumption", savePath);
                }
                break;
            case 4:
                savePath = FileChooseUtil.chooseSavePath("RechargeRecord.txt");
                if (savePath != null) {
                    msg = dataController.exportData("recharge", savePath);
                }
                break;
            case 5:
                return;
            default:
        }
        if (msg == null) {
            System.out.println("取消保存");
        } else if (msg.getCode() == ResultCode.SUCCESS) {
            System.out.println("保存成功");
        } else {
            logger.debug(msg.toString());
            System.out.println("保存失败");
        }
    }
}
