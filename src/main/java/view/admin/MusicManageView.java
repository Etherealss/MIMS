package view.admin;

import common.enums.AdminType;
import common.enums.ResultCode;
import common.exception.UserUnloggedException;
import common.manage.OnlineUserManage;
import controller.MusicController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.po.Music;
import pojo.po.User;
import pojo.vo.Msg;
import util.InputUtil;
import util.ViewUtil;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author wtk
 * @description
 * @date 2021-05-26
 */
public class MusicManageView {

    private Logger logger = LoggerFactory.getLogger("root");

    private MusicController musicController = new MusicController();

    public void enter() throws UserUnloggedException {
        User user = OnlineUserManage.getOnlineUser();
        if (!AdminType.MUSIC_ADMIN.equals(user.getAdminType())) {
            System.out.println("您不是音乐管理员！");
            return;
        }

        adminAction();
    }

    private void adminAction() {
        String[] options = {"查询音乐", "添加音乐", "修改音乐信息", "删除音乐", "返回"};
        while (true) {
            int op = ViewUtil.displayOptionsWithInput(options);
            switch (op) {
                case 1:
                    showFindMusic();
                    break;
                case 2:
                    showAddMusic();
                    break;
                case 3:
                    showUpdateMusic();
                    break;
                case 4:
                    showDeleteMusic();
                    break;
                case 5:
                    return;
                default:
            }
        }
    }

    private void showFindMusic() {
        ViewUtil.findAndShowExistMusic(musicController, "请输入你需要查找的音乐：");
    }

    private void showAddMusic() {
        System.out.println("请输入的想要添加的音乐ID（仅包含数字，输入-1自动设置）：");

        Music music = new Music();
        int id = inputMusicId();
        music.setId(id);

        updateMusicName(music);

        updateSingers(music);

        updateMudiaFile(music);

        updateReleaseDate(music);

        updateListenFree(music);

        updatePrice(music);

        Msg msg = musicController.addMusic(music);
        if (msg.getCode() == ResultCode.SUCCESS) {
            System.out.println("添加成功！");
        } else if (msg.getCode() == ResultCode.PRECONDITION_FAILED) {
            System.out.println("音乐已存在！");
        } else {
            System.out.println("出现异常！");
        }
    }

    private void showUpdateMusic() {
        Music music = ViewUtil.findAndShowExistMusic(musicController, "请输入你要修改的音乐ID：");
        if (music == null) {
            System.out.println("音乐获取失败！");
            return;
        }

        System.out.println("请选择你要修改的信息：");
        String[] options = {"音乐名", "歌手", "媒体文件", "发行时间", "免费听", "价格", "完成并保存", "取消修改"};
        while (true) {
            int op = ViewUtil.displayOptionsWithInput(options);

            switch (op) {
                case 1:
                    updateMusicName(music);
                    break;
                case 2:
                    updateSingers(music);
                    break;
                case 3:
                    updateMudiaFile(music);
                    break;
                case 4:
                    updateReleaseDate(music);
                    break;
                case 5:
                    updateListenFree(music);
                    break;
                case 6:
                    updatePrice(music);
                    break;
                case 7:
                    boolean saveSuccess = updateMusic(music);
                    if (saveSuccess) {
                        return;
                    } else {
                        continue;
                    }
                default:
                    System.out.println("取消修改！");
                    return;
            }

            System.out.println("音乐信息：\n" + music.getInfo());
        }
    }

    private void showDeleteMusic() {
        Music music = ViewUtil.findAndShowExistMusic(musicController, "请输入的你要删除的音乐：");
        if (music == null) {
            System.out.println("音乐获取失败！");
            return;
        }
        System.out.println("是否删除该音乐？1. 是\t2. 否");
        int confirm = InputUtil.inputInt();
        if (confirm == 1) {
            Msg msg = musicController.deleteMusic(music.getId());
            if (msg.getCode() == ResultCode.SUCCESS) {
                System.out.println("删除成功");
            } else if (msg.getCode() == ResultCode.NOT_FOUND) {
                System.out.println("音乐不存在！");
            } else {
                System.out.println("删除失败！");
            }
        }
    }

    private int inputMusicId() {
        while (true) {
            int id = InputUtil.inputInt();

            if (id == -1) {
                return 0;
            }

            // 检查要注册的账号id是否存在
            Msg msg = musicController.findMusic(id);
            if (msg.getCode() == 200) {
                // 账号已存在
                System.out.println("音乐编号已存在，请重新输入");
            } else if (msg.getCode() == 404) {
                return id;
            } else {
                System.out.println("异常！请重新输入");
            }
        }
    }

    private void updateMusicName(Music music) {
        System.out.println("请输入音乐名：");
        String name = InputUtil.inputString();
        music.setName(name);
    }

    private void updateSingers(Music music) {
        // 重置
        music.setSingers(new ArrayList<>());

        System.out.println("请输入至少一位歌手");
        String singer = "";
        int count = 1;
        while (true) {
            System.out.print("第" + count + "位歌手（输入-1停止输入）：\t");
            singer = InputUtil.inputString();
            if ("-1".equals(singer)) {
                // 确保输入的歌手至少一位
                if (music.getSingers().size() > 0) {
                    break;
                } else {
                    System.out.println("输入的歌手少于1位！请输入至少一位歌手");
                    continue;
                }
            } else if (singer.contains(",") || singer.contains("|")) {
                System.out.println("请不要输入诸如“,”、“|”的特殊字符");
                continue;
            }
            music.addSinger(singer);
            count++;
        }
    }

    private void updateMudiaFile(Music music) {
        System.out.println("请指定媒体文件路径");
        String filePath = InputUtil.inputString();
        music.setMediaFilePath(filePath);
    }

    private void updateReleaseDate(Music music) {
        System.out.println("请输入发行日期：");
        Date date = InputUtil.inputDate();
        music.setReleaseDate(date);
    }

    private void updateListenFree(Music music) {
        while (true) {
            System.out.println("请输入是否免费听：\n1. 是\t2. 否");
            int isFree = InputUtil.inputInt();
            if (isFree == 1) {
                music.setListenFree(true);
                break;
            } else if (isFree == 2) {
                music.setListenFree(false);
                break;
            } else {
                System.out.println("输入错误！");
            }
        }
    }

    private void updatePrice(Music music) {
        if (music.getListenFree()) {
            music.setPrice(0);
        } else {
            System.out.println("请输入价格：");
            float price = InputUtil.inputFloat();
            music.setPrice(price);
        }
    }

    /**
     * @param music
     * @return 保存成功返回true
     */
    private boolean updateMusic(Music music) {
        Msg msg = musicController.updateMusic(music);
        if (msg.getCode() == ResultCode.SUCCESS) {
            System.out.println("保存修改成功！");
            return true;
        } else {
            System.out.println("修改失败！");
            return false;
        }
    }

}
