package service;

import common.exception.ServerException;
import pojo.po.ConsumingRecord;
import pojo.po.Music;
import pojo.po.User;

import java.util.List;

/**
 * @author wtk
 * @description
 * @date 2021-06-01
 */
public interface ConsumingRecordService {

    void buyMusic(User user, Music music) throws ServerException;

    ConsumingRecord getMusicConsumingRecord(int userId, int musicId) throws ServerException;

    List<Music> getBoughtMusics(int userId) throws ServerException;
}
