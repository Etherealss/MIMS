package database;

import database.base.BaseDataManagementByList;
import pojo.po.ConsumingRecord;

/**
 * @author wtk
 * @description 消费记录
 * @date 2021-06-01
 */
public class ConsumingRecordManagement extends BaseDataManagementByList<ConsumingRecord> {
    private static ConsumingRecordManagement management = new ConsumingRecordManagement();

    private ConsumingRecordManagement() {}

    public static ConsumingRecordManagement getManagement() {
        return management;
    }
}
