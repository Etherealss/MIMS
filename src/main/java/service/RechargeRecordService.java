package service;

import pojo.po.RechargeRecord;

import java.util.List;

/**
 * @author wtk
 * @description
 * @date 2021-06-01
 */
public interface RechargeRecordService {

    /**
     * 保存充值数据
     * @param userId
     * @param money
     * @return
     */
    boolean recharge(int userId, float money);

    /**
     * 获取最近几条充值记录
     * @param userId
     * @return
     */
    List<RechargeRecord> findRecentRecharge(int userId);
}
