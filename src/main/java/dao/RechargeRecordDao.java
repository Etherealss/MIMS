package dao;

import pojo.po.RechargeRecord;

import java.util.List;

/**
 * @author wtk
 * @description 充值
 * @date 2021-06-01
 */
public interface RechargeRecordDao {

    RechargeRecord insertRecord(RechargeRecord record);

    RechargeRecord findByRecordId(int recordId);

    List<RechargeRecord> findByUserId(int userId);

    void deleteRecord(int recordId);

}
