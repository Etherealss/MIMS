package service.impl;

import common.enums.ExceptionType;
import common.exception.BadRequestException;
import common.exception.NotFoundException;
import common.exception.ServerException;
import common.factory.DaoFactory;
import dao.MusicDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.po.Music;
import service.MusicService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wtk
 * @description
 * @date 2021-05-29
 */
public class MusicServiceImpl implements MusicService {

    private Logger logger = LoggerFactory.getLogger("root");

    private MusicDao musicDao = DaoFactory.getMusicDao();

    @Override
    public Music findMusicById(int musicId) throws NotFoundException {
        return getExistMusic(musicId);
    }

    @Override
    public Music deleteMusic(int musicId) throws ServerException {
        Music oldMusic = getExistMusic(musicId);
        boolean b = musicDao.deleteMusic(musicId);
        if (!b) {
            throw new ServerException("删除音乐异常");
        }
        return oldMusic;
    }

    @Override
    public Music updateMusic(Music music) throws NotFoundException {
        Music oldMusic = getExistMusic(music.getId());
        musicDao.updateMusic(music);
        return oldMusic;
    }

    @Override
    public void insertMusic(Music music) throws BadRequestException {
        Music existMusic = musicDao.findMusic(music.getId());
        if (existMusic != null) {
            throw new BadRequestException(ExceptionType.MUSIC_EXISTED, "音乐已存在");
        }
        musicDao.insertMusic(music);
    }

    @Override
    public List<Music> findMusicByName(String keyword) {
        List<Music> allMusic = findAllMusic();
        List<Music> list = new ArrayList<>();
        for (Music music : allMusic) {
            String musicName = music.getName();
            if (musicName.contains(keyword)) {
                list.add(music);
            }
        }
        return list;
    }

    @Override
    public List<Music> findMusicBySinger(String keyword) {
        List<Music> allMusic = findAllMusic();
        List<Music> list = new ArrayList<>();
        for (Music music : allMusic) {
            List<String> singers = music.getSingers();
            for (String singer : singers) {
                if (singer.equals(keyword)) {
                    list.add(music);
                }
            }
        }
        return list;
    }

    private Music getExistMusic(int musicId) throws NotFoundException {
        Music music = musicDao.findMusic(musicId);
        if (music == null) {
            throw new NotFoundException("音乐不存在");
        }
        return music;
    }

    private List<Music> findAllMusic() {
        int musicSize = musicDao.getMusicSize();
        List<Music> allMusic = musicDao.findAllMusic();
        return allMusic;
    }
}
