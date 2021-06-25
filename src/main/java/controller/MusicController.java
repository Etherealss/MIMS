package controller;

import common.enums.ResultCode;
import common.enums.ExceptionType;
import common.exception.BadRequestException;
import common.exception.InsufficientBalanceException;
import common.exception.NotFoundException;
import common.exception.UserUnloggedException;
import common.factory.ServiceFactory;
import common.manage.OnlineUserManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.po.Music;
import pojo.po.User;
import pojo.vo.Msg;
import service.ConsumingRecordService;
import service.MusicService;
import service.UserService;

import java.util.List;

/**
 * @author wtk
 * @description
 * @date 2021-05-28
 */
public class MusicController {

    private Logger logger = LoggerFactory.getLogger("root");

    private MusicService musicService = ServiceFactory.getMusicService();

    public Msg findMusic(int musicId) {
        try {
            Music music = musicService.findMusicById(musicId);
            return Msg.success("music", music);
        } catch (NotFoundException e) {
            return Msg.musicNotFound();
        } catch (Exception e) {
            return Msg.exception(e);
        }
    }

    public Msg searchMusic(String keyword, String type) {
        List<Music> musics = null;
        try {
            switch (type) {
                case "name":
                    musics = musicService.findMusicByName(keyword);
                    break;
                case "singer":
                    musics = musicService.findMusicBySinger(keyword);
                    break;
                case "like":
                    break;
                default:
            }
            Msg msg = new Msg(200, "查询成功");
            msg.addData("musics", musics);
            return msg;
        } catch (Exception e) {
            return Msg.exception(e);
        }
    }

    public Msg addMusic(Music music) {
        try {
            musicService.insertMusic(music);
            return Msg.success();
        } catch (BadRequestException e) {
            if (ExceptionType.MUSIC_EXISTED.equals(e.getType())) {
                return new Msg(ResultCode.PRECONDITION_FAILED, "音乐已存在");
            }
            return Msg.exception();
        } catch (Exception e) {
            logger.error("异常", e);
            return Msg.exception(e);
        }
    }

    public Msg updateMusic(Music music) {
        try {
            musicService.updateMusic(music);
            return Msg.success();
        } catch (NotFoundException e) {
            return Msg.musicNotFound();
        } catch (Exception e) {
            logger.error("异常", e);
            return Msg.exception(e);
        }
    }

    public Msg deleteMusic(int musicId) {
        try {
            musicService.deleteMusic(musicId);
            return Msg.success();
        } catch (NotFoundException e) {
            return Msg.musicNotFound();
        } catch (Exception e) {
            logger.error("异常", e);
            return Msg.exception(e);
        }
    }

    public Msg saveMusicData() {
        return Msg.success();
    }

}
