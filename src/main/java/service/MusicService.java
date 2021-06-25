package service;

import common.exception.ServerException;
import pojo.po.Music;

import java.util.List;

/**
 * @author wtk
 * @description
 * @date 2021-05-29
 */
public interface MusicService {

    Music findMusicById(int musicId) throws ServerException;

    Music deleteMusic(int musicId) throws ServerException;

    Music updateMusic(Music music) throws ServerException;

    void insertMusic(Music music) throws ServerException;

    List<Music> findMusicByName(String keyword) throws ServerException;

    List<Music> findMusicBySinger(String keyword) throws ServerException;

}
