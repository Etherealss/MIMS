package dao.impl;

import dao.MusicDao;
import database.MusicDataManagement;
import pojo.po.Music;

import java.util.List;

/**
 * @author wtk
 * @description
 * @date 2021-05-29
 */
public class MusicDaoImpl implements MusicDao {

    private MusicDataManagement management = MusicDataManagement.getManagement();

    @Override
    public int getMusicSize() {
        return management.size();
    }

    @Override
    public List<Music> findRangeMusic(int startId, int endId) {
        List<Music> rangeMusic = management.findRange(startId, endId);
        return rangeMusic;
    }

    @Override
    public List<Music> findAllMusic() {
        return management.findAll();
    }

    @Override
    public Music findMusic(int musicId) {
        return management.find(musicId);
    }

    @Override
    public Music updateMusic(Music music) {
        return management.update(music);
    }

    @Override
    public boolean deleteMusic(int musicId) {
        return management.delete(musicId) != null;
    }

    @Override
    public void insertMusic(Music music) {
        management.add(music);
    }
}
