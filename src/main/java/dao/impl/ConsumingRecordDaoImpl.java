package dao.impl;

import dao.ConsumingRecordDao;
import database.ConsumingRecordManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.po.ConsumingRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wtk
 * @description
 * @date 2021-06-01
 */
public class ConsumingRecordDaoImpl implements ConsumingRecordDao {

    private Logger logger = LoggerFactory.getLogger("root");

    private ConsumingRecordManagement management = ConsumingRecordManagement.getManagement();

    @Override
    public void insertRecord(ConsumingRecord record) {
        management.add(record);
    }

    @Override
    public ConsumingRecord findByRecordId(int recordId) {
        return management.find(recordId);
    }

    @Override
    public ConsumingRecord find(int userId, int targetId, String type) {
        List<ConsumingRecord> records = management.findAll();
        for (ConsumingRecord record : records) {
            if (record.getUserId() == userId) {
                if (record.getTargetId() == targetId) {
                    if (record.getType().equals(type)) {
                        return record;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<ConsumingRecord> findByUserId(int userId) {
        List<ConsumingRecord> records = management.findAll();
        List<ConsumingRecord> list = new ArrayList<>();
        for (ConsumingRecord record : records) {
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

    @Override
    public int getMaxRecordId() {
        return management.find(management.size()).getId();
    }
}
