package dao;

import pojo.po.Music;

import java.util.List;

/**
 * @author wtk
 * @description
 * @date 2021-05-29
 */
public interface MusicDao {

    int getMusicSize();

    List<Music> findRangeMusic(int startId, int endId);

    List<Music> findAllMusic();

    Music findMusic(int musicId);

    Music updateMusic(Music music);

    boolean deleteMusic(int musicId);

    void insertMusic(Music music);
}
