package controller;

import common.enums.ResultCode;
import common.exception.*;
import common.factory.ServiceFactory;
import common.manage.OnlineUserManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.po.ConsumingRecord;
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
 * @date 2021-06-01
 */
public class ConsumptionController {

    private Logger logger = LoggerFactory.getLogger("root");

    private UserService userService = ServiceFactory.getUserService();
    private MusicService musicService = ServiceFactory.getMusicService();
    private ConsumingRecordService consumingRecordService = ServiceFactory.getConsumingRecordService();

    public Msg bugMusic(int musicId) {
        try {
            /*
            在Controller调用多个Service，执行多个子功能
            在某一个Service中，完成子功能的具体实现
            此处，获取在线用户和音乐是两个子功能，购买音乐也是子功能
            由于musicService.findMusicById(musicId);中包含了对异常状况的处理，
            所以放在Controller也是最方便的
            否则，在ConsumingRecordService中再次实现查找音乐的功能，就重复了
             */
            User user = OnlineUserManage.getOnlineUser();
            Music music = musicService.findMusicById(musicId);

            consumingRecordService.buyMusic(user, music);
            // 更新余额
            userService.updateUser(user);
            return Msg.success("balance", user.getBalance());

        } catch (NotFoundException e) {
            return Msg.musicNotFound();

        } catch (UserUnloggedException e) {
            return Msg.userUnlogged();

        } catch (InsufficientBalanceException e) {
            return new Msg(ResultCode.INSUFFICIENT_BALANCE, "用户余额不足");

        } catch (Exception e) {
            logger.error("异常", e);
            return Msg.exception(e);
        }
    }

    public Msg getMusicPurchased(int musicId) {
        try {
            User user = OnlineUserManage.getOnlineUser();
            ConsumingRecord record = consumingRecordService.getMusicConsumingRecord(user.getId(), musicId);
            return Msg.success("record", record);

        } catch (UserUnloggedException e) {
            return Msg.userUnlogged();
        } catch (NoSuchRecordException e) {
            return Msg.notFound("没有购买记录");
        } catch (Exception e) {
            logger.error("异常", e);
            return Msg.exception(e);
        }
    }

    public Msg getBoughtMusics() {
        try {
            User user = OnlineUserManage.getOnlineUser();
            List<Music> boughtMusics = consumingRecordService.getBoughtMusics(user.getId());
            if (boughtMusics.size() > 0) {
                return Msg.success("musics", boughtMusics);
            } else {
                return Msg.notFound("没有任何购买记录！");
            }
        } catch (UserUnloggedException e) {
            return Msg.userUnlogged();
        } catch (Exception e) {
            logger.error("异常", e);
            return Msg.exception(e);
        }
    }
}
