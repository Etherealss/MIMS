package view;

import common.enums.ResultCode;
import common.exception.UserUnloggedException;
import common.factory.ControllerFactory;
import common.manage.OnlineUserManage;
import controller.ConsumptionController;
import controller.MusicController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.po.Music;
import pojo.po.User;
import pojo.vo.Msg;
import util.InputUtil;
import util.ViewUtil;

import java.util.List;

/**
 * @author wtk
 * @description
 * @date 2021-05-26
 */
public class MusicView {

    private Logger logger = LoggerFactory.getLogger("root");

    private MusicController musicController = ControllerFactory.getMusicController();
    private ConsumptionController consumptionController = ControllerFactory.getConsumptionController();

    public void enter() throws UserUnloggedException {
        action();
    }

    private void action() throws UserUnloggedException {
        String[] options = {"按ID查询音乐", "按歌名搜索音乐", "按歌手搜索音乐",
                "已购买的音乐", "返回"};
        while (true) {
            int op = ViewUtil.displayOptionsWithInput(options);
            Music aMusic = null;
            List<Music> musicList = null;
            switch (op) {
                case 1:
                    aMusic = showSearchById();
                    break;
                case 2:
                    musicList = showSearchByName();
                    break;
                case 3:
                    musicList = showSearchBySinger();
                    break;
                case 4:
                    musicList = showBoughtMusics();
                    break;
                case 5:
                    return;
                default:
            }

            if (op == 1) {
                if (aMusic != null) {
                    operateMusic(aMusic);
                }
            } else {
                if (musicList == null) {
                    // 相关的控制台提示已给出，这里不重复输出
                    continue;
                }
                int len = musicList.size();

                System.out.println("一共查询到" + len + "首音乐");
                int count = 1;
                for (Music music : musicList) {
                    System.out.println("================第" + (count++) + "首音乐================");
                    System.out.println(music.getInfo());
                }

                if (len == 0) {
                    System.out.println("查询音乐失败！");
                    continue;
                }

                System.out.println("请输入你想进行操作的音乐的序号：(输入-1返回)");
                int index = InputUtil.inputInt();
                if (index == -1) {
                    continue;
                }
                if (index > musicList.size()) {
                    System.out.println("序号错误！");
                    continue;
                }
                operateMusic(musicList.get(index - 1));
            }
        }
    }

    /**
     * 根据ID精准查询
     * @return
     */
    private Music showSearchById() {
        Music existMusic = ViewUtil.findAndShowExistMusic(musicController, "请输入的想要搜搜的音乐ID：");
        if (existMusic == null) {
            System.out.println("没有匹配的音乐！");
        }
        return existMusic;
    }

    /**
     * 根据音乐名模糊查询
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<Music> showSearchByName() {
        String musicName;
        while (true) {
            System.out.println("请输入的想要搜搜的音乐关键字：");
            musicName = InputUtil.inputString();
            if ("-1".equals(musicName)) {
                System.out.println("取消搜索！");
                return null;
            } else if ("".equals(musicName)) {
                System.out.println("输入不规范！请重新输入！");
                continue;
            }
            break;
        }
        Msg msg = musicController.searchMusic(musicName, "name");
        if (msg.getCode() != ResultCode.SUCCESS) {
            System.out.println("查询失败！");
            return null;
        }

        List<Music> musicList = (List<Music>) msg.getData("musics");
        return musicList;
    }

    /**
     * 根据歌手查询
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<Music> showSearchBySinger() {
        String signer;
        while (true) {
            System.out.println("请输入的想要搜搜的音乐的歌手：");
            signer = InputUtil.inputString();
            if ("-1".equals(signer)) {
                System.out.println("取消搜索！");
                return null;
            } else if ("".equals(signer)) {
                System.out.println("输入不规范！请重新输入！");
                continue;
            }
            break;
        }
        Msg msg = musicController.searchMusic(signer, "singer");
        if (msg.getCode() != ResultCode.SUCCESS) {
            System.out.println("查询失败！");
            return null;
        }

        List<Music> musicList = (List<Music>) msg.getData("musics");
        return musicList;
    }

    @SuppressWarnings("unchecked")
    private List<Music> showBoughtMusics() {
        Msg msg = consumptionController.getBoughtMusics();
        if (msg.getCode() == ResultCode.SUCCESS) {
            List<Music> musicList = (List<Music>) msg.getData("musics");
            return musicList;
        } else {
            System.out.println("没有购买记录！");
            return null;
        }
    }

    /**
     * 对音乐进行操作，如听音乐、购买音乐
     * @param music
     */
    private void operateMusic(Music music) throws UserUnloggedException {
        String[] options = {"听音乐", "购买音乐", "返回"};
        while (true) {
            int op = ViewUtil.displayOptionsWithInput(options);
            switch (op) {
                case 1:
                    listenMusic(music);
                    break;
                case 2:
                    buyMusic(music);
                    break;
                case 3:
                    return;
                default:
            }
        }
    }

    private void listenMusic(Music music) throws UserUnloggedException {
        if (canListen(music)) {
            doListenMusic(music);
        } else {
            // 无购买记录
            System.out.println("你尚未购买该歌曲！无法听歌");
        }
    }

    /**
     * 是否可以听歌
     * @param music
     * @return 有听歌权限返回true
     * @throws UserUnloggedException
     */
    private boolean canListen(Music music) throws UserUnloggedException {
        User user = OnlineUserManage.getOnlineUser();

        boolean listenFree = music.getListenFree();
        if (listenFree) {
            return true;
        }

        Msg msg = consumptionController.getMusicPurchased(music.getId());
        if (msg.getCode() == ResultCode.SUCCESS) {
            // 有购买记录
            return true;
        } else if (msg.getCode() == ResultCode.NOT_FOUND) {
            // 无购买记录
            return false;
        } else {
            return false;
        }
    }

    private void buyMusic(Music music) throws UserUnloggedException {
        if (canListen(music)) {
            // 已购买
            System.out.println("您已购买音乐");
            return;
        }
        User user = OnlineUserManage.getOnlineUser();
        System.out.println("当前音乐的价格为：" + music.getPrice());
        System.out.println("您的账户余额为：" + user.getBalance());

        float balance = user.getBalance() - music.getPrice();
        if (balance < 0) {
            System.out.println("余额不足，无法购买！");
            return;
        }

        System.out.println("是否购买？\t1. 是\t2. 否");
        boolean buy = InputUtil.confirm();
        if (buy) {
            Msg msg = consumptionController.bugMusic(music.getId());
            if (msg.getCode() == ResultCode.SUCCESS) {
                System.out.println("购买成功！账户余额为：" + msg.getData("balance"));
            } else {
                System.out.println("购买失败！");
            }
        }
    }

    private void doListenMusic(Music music) {
        System.out.println("用户成功听歌！\n" + music.getInfo());
    }

}
