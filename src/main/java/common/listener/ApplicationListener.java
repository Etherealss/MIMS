package common.listener;

import com.sun.org.apache.regexp.internal.RE;
import common.enums.ResourcePath;
import database.*;
import util.DataReader;
import util.DataWriter;

import java.io.IOException;

/**
 * @author wtk
 * @description 程序监听器
 * @date 2021-05-26
 */
public class ApplicationListener {


    private DataReader dataReader = new DataReader();
    private DataWriter writer = new DataWriter();

    public void init() throws IOException {
        UserDataManagement.getManagement().initData(dataReader.initUserData4List(ResourcePath.USER_DATA_PATH));
        MusicDataManagement.getManagement().initData(dataReader.initMusicData4List(ResourcePath.MUSIC_DATA_PATH));
        ConsumingRecordManagement.getManagement().initData(dataReader.initConsumingRecord4List(ResourcePath.CONSUMING_RECORD_PATH));
        RechargeRecordManagement.getManagement().initData(dataReader.initRechargeRecord4List(ResourcePath.RECHARGE_RECORD_PATH));
    }

    public void save() throws IOException {
        writer.exportUserData(ResourcePath.USER_DATA_PATH);
        writer.exportConsumptionData(ResourcePath.CONSUMING_RECORD_PATH);
        writer.exportMusicData(ResourcePath.MUSIC_DATA_PATH);
        writer.exportRechargeData(ResourcePath.RECHARGE_RECORD_PATH);
    }

}
