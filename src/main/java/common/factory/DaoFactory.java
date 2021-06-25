package common.factory;

import dao.ConsumingRecordDao;
import dao.MusicDao;
import dao.RechargeRecordDao;
import dao.UserDao;
import dao.impl.ConsumingRecordDaoImpl;
import dao.impl.MusicDaoImpl;
import dao.impl.RechargeRecordDaoImpl;
import dao.impl.UserDaoImpl;

/**
 * @author wtk
 * @description
 * @date 2021-05-24
 */
public class DaoFactory {

    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public static MusicDao getMusicDao() {
        return new MusicDaoImpl();
    }

    public static ConsumingRecordDao getConsumingRecordDao() {
        return new ConsumingRecordDaoImpl();
    }

    public static RechargeRecordDao getRechargeRecordDao() {
        return new RechargeRecordDaoImpl();
    }
}
