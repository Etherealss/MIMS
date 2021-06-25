package database;

import database.base.BaseDataManagementByList;
import pojo.po.RechargeRecord;

/**
 * @author wtk
 * @description 充值记录
 * @date 2021-06-01
 */
public class RechargeRecordManagement extends BaseDataManagementByList<RechargeRecord> {

    private static RechargeRecordManagement management = new RechargeRecordManagement();

    private RechargeRecordManagement() {}

    public static RechargeRecordManagement getManagement() {
        return management;
    }

}
