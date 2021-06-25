package common.factory;

import pojo.po.User;
import service.*;
import service.impl.*;

/**
 * @author wtk
 * @description
 * @date 2021-05-24
 */
public class ServiceFactory {

    public static UserService getUserService() {
        return new UserServiceImpl();
    }

    public static MusicService getMusicService() {
        return new MusicServiceImpl();
    }

    public static ConsumingRecordService getConsumingRecordService() {
        return new ConsumingRecordServiceImpl();
    }

    public static RechargeRecordService getRechargeRecordService() {
        return new RechargeRecordServiceImpl();
    }

    public static DataService getDataService() {
        return new DataServiceImpl();
    }
}
