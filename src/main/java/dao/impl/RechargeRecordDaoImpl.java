package dao.impl;

import dao.RechargeRecordDao;
import database.RechargeRecordManagement;
import pojo.po.RechargeRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wtk
 * @description
 * @date 2021-06-01
 */
public class RechargeRecordDaoImpl implements RechargeRecordDao {
    private RechargeRecordManagement management = RechargeRecordManagement.getManagement();
    @Override
    public RechargeRecord insertRecord(RechargeRecord record) {
        return management.add(record);
    }

    @Override
    public RechargeRecord findByRecordId(int recordId) {
        return management.find(recordId);
    }

    @Override
    public List<RechargeRecord> findByUserId(int userId) {
        List<RechargeRecord> records = management.findAll();
        List<RechargeRecord> list = new ArrayList<>();
        for (RechargeRecord record : records) {
            if (record.getUserId() == userId) {
                list.add(record);
            }
        }
        return list;
    }

    @Override
    public void deleteRecord(int recordId) {
        management.delete(recordId);
    }
}
