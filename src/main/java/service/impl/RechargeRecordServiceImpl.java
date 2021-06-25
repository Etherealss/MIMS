package service.impl;

import common.factory.DaoFactory;
import common.structure.CycleQueue;
import common.structure.MyQueue;
import dao.RechargeRecordDao;
import pojo.po.RechargeRecord;
import service.RechargeRecordService;

import java.util.Date;
import java.util.List;
import java.util.Queue;

/**
 * @author wtk
 * @description
 * @date 2021-06-01
 */
public class RechargeRecordServiceImpl implements RechargeRecordService {

    private MyQueue<RechargeRecord> recentRecordQueue = new CycleQueue<>();
    private RechargeRecordDao rechargeRecordDao = DaoFactory.getRechargeRecordDao();

    @Override
    public boolean recharge(int userId, float money) {
        RechargeRecord rechargeRecord = new RechargeRecord();
        rechargeRecord.setUserId(userId);
        rechargeRecord.setPrice(money);
        rechargeRecord.setRechargeDate(new Date());

        rechargeRecordDao.insertRecord(rechargeRecord);
        recentRecordQueue.offer(rechargeRecord);
        return true;
    }

    @Override
    public List<RechargeRecord> findRecentRecharge(int userId) {
        return recentRecordQueue.findAll();
    }
}
