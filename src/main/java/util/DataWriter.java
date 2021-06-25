package util;

import database.ConsumingRecordManagement;
import database.MusicDataManagement;
import database.RechargeRecordManagement;
import database.UserDataManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.po.ConsumingRecord;
import pojo.po.Music;
import pojo.po.RechargeRecord;
import pojo.po.User;

import java.io.IOException;
import java.util.*;

/**
 * @author wtk
 * @description
 * @date 2021-06-03
 */
public class DataWriter {

    private Logger logger = LoggerFactory.getLogger("root");

    public void exportUserData(String filepath) throws IOException {
        logger.trace("导出用户数据：filepath = " + filepath);
        List<User> dataList = UserDataManagement.getManagement().getDataList();
        List<String> strData = new ArrayList<>(dataList.size());
        for (User user : dataList) {
            // 直接用+连接符可能会进行数字加法，所以先把第一个元素转为String
            String builder = String.valueOf(user.getId()) + ',' +
                    user.getPassword() + ',' +
                    user.getUsername() + ',' +
                    user.getSex() + ',' +
                    user.getRegisterDate().getTime() + ',' +
                    user.getBalance() + ',' +
                    user.getAdminType();
            strData.add(builder);
        }
        IOUtil.write2Txt(strData, filepath);
    }

    public void exportMusicData(String filepath) throws IOException {
        logger.trace("导出音乐数据");

        List<Music> dataList = MusicDataManagement.getManagement().getDataList();
        List<String> strData = new ArrayList<>(dataList.size());
        for (Music music : dataList) {
            List<String> singerList = music.getSingers();
            StringBuilder signersName = new StringBuilder();
            for (String singerName : singerList) {
                signersName.append(singerName).append("|");
            }
            signersName.deleteCharAt(signersName.length() - 1);
            String data = String.valueOf(music.getId()) + ',' +
                    music.getName() + ',' +
                    signersName.toString() + ',' +
                    music.getMediaFilePath() + ',' +
                    music.getReleaseDate().getTime() + ',' +
                    music.getListenFree() + ',' +
                    music.getPrice();
            strData.add(data);
        }

        IOUtil.write2Txt(strData, filepath);
    }

    public void exportConsumptionData(String filepath) throws IOException {
        logger.trace("导出消费数据");

        List<ConsumingRecord> recordList = ConsumingRecordManagement.getManagement().getDataList();
        List<String> strData = new ArrayList<>(recordList.size());
        for (ConsumingRecord record : recordList) {
            String data = String.valueOf(record.getId()) + ',' +
                    record.getUserId() + ',' +
                    record.getTargetId() + ',' +
                    record.getType() + ',' +
                    record.getConsumeTime().getTime() + ',' +
                    record.getPrice();

            strData.add(data);
        }

        IOUtil.write2Txt(strData, filepath);
    }

    public void exportRechargeData(String filepath) throws IOException {
        logger.trace("导出充值数据");

        List<RechargeRecord> recordList = RechargeRecordManagement.getManagement().getDataList();
        List<String> strData = new ArrayList<>(recordList.size());
        for (RechargeRecord record : recordList) {
            String data = String.valueOf(record.getId()) + ',' +
                    record.getUserId() + ',' +
                    record.getRechargeDate().getTime() + ',' +
                    record.getPrice();

            strData.add(data);
        }

        IOUtil.write2Txt(strData, filepath);
    }

}
