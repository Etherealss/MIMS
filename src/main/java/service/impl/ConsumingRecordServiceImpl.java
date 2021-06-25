package service.impl;

import common.enums.ConsumptionType;
import common.enums.ExceptionType;
import common.exception.BadRequestException;
import common.exception.InsufficientBalanceException;
import common.exception.NoSuchRecordException;
import common.exception.ServerException;
import common.factory.DaoFactory;
import dao.ConsumingRecordDao;
import dao.MusicDao;
import dao.UserDao;
import database.UserDataManagement;
import pojo.po.ConsumingRecord;
import pojo.po.Music;
import pojo.po.User;
import service.ConsumingRecordService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wtk
 * @description
 * @date 2021-06-01
 */
public class ConsumingRecordServiceImpl implements ConsumingRecordService {

    private ConsumingRecordDao recordDao = DaoFactory.getConsumingRecordDao();
    private MusicDao musicDao = DaoFactory.getMusicDao();

    @Override
    public void buyMusic(User user, Music music) throws ServerException {
        float balance = user.getBalance() - music.getPrice();
        if (balance < 0) {
            // 余额不足，无法购买
            throw new InsufficientBalanceException();
        }

        user.setBalance(balance);

        ConsumingRecord record = new ConsumingRecord();
        record.setUserId(user.getId());
        record.setTargetId(music.getId());
        record.setType(ConsumptionType.MUSIC);
        record.setPrice(music.getPrice());
        record.setId(recordDao.getMaxRecordId() + 1);
        record.setConsumeTime(new Date());

        recordDao.insertRecord(record);
    }

    @Override
    public ConsumingRecord getMusicConsumingRecord(int userId, int musicId) throws ServerException {
        ConsumingRecord record = recordDao.find(userId, musicId, ConsumptionType.MUSIC);
        if (record == null) {
            throw new NoSuchRecordException();
        }
        return record;
    }

    @Override
    public List<Music> getBoughtMusics(int userId) throws ServerException {
        // 获取用户所有的消费记录
        List<ConsumingRecord> records = recordDao.findByUserId(userId);
        List<Music> musicList = new ArrayList<>();
        // 筛选出音乐的消费记录
        for (ConsumingRecord record : records) {
            if (record.getType().equals(ConsumptionType.MUSIC)) {
                int musicId = record.getTargetId();
                // 获取音乐数据
                Music music = musicDao.findMusic(musicId);
                musicList.add(music);
            }
        }
        return musicList;
    }
}
