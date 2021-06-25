package dao;

import pojo.po.ConsumingRecord;

import java.util.List;

/**
 * @author wtk
 * @description 消费
 * @date 2021-06-01
 */
public interface ConsumingRecordDao {

    void insertRecord(ConsumingRecord record);

    ConsumingRecord findByRecordId(int recordId);

    ConsumingRecord find(int userId, int targetId, String type);


    List<ConsumingRecord> findByUserId(int userId);

    void deleteRecord(int record);

    int getMaxRecordId();
}
