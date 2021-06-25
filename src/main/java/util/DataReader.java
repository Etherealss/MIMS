package util;

import common.structure.MyArrayList;
import database.UserDataManagement;
import pojo.po.ConsumingRecord;
import pojo.po.Music;
import pojo.po.RechargeRecord;
import pojo.po.User;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wtk
 * @description 读取数据
 * @date 2021-05-26
 */
public class DataReader {

    /**
     * 初始化UserDataManagement
     * @param filepath 用户文件路径
     * @throws IOException
     */
    @Deprecated
    public Map<Integer, User> initUserData(String filepath) throws IOException {
        // 读取用户数据
        List<String> strings = IOUtil.readTxt(filepath);
        // 封装user的Map
        Map<Integer, User> userMap = new HashMap<>(strings.size());
        for (String userStr : strings) {
            String[] split = userStr.split(",");
            User user = new User();
            user.setId(Integer.parseInt(split[0]));
            user.setPassword(split[1]);
            user.setUsername(split[2]);
            // 1为男
            user.setSex("true".equals(split[3]));
            user.setRegisterDate(new Date(Long.parseLong(split[4])));
            // 余额
            user.setBalance(Float.parseFloat(split[5]));
            // 管理员
            user.setAdminType(split[6]);
            userMap.put(user.getId(), user);
        }
        return userMap;
    }

    /**
     * 初始化MusicDataManagement
     * @param filepath 用户文件路径
     * @throws IOException
     */
    @Deprecated
    public Map<Integer, Music> initMusicData(String filepath) throws IOException {
        // 读取用户数据
        List<String> strings = IOUtil.readTxt(filepath);
        // 封装user的Map
        Map<Integer, Music> musicMap = new HashMap<>(strings.size());
        for (String musicStr : strings) {
            String[] split = musicStr.split(",");
            Music music = new Music();
            music.setId(Integer.parseInt(split[0]));
            music.setName(split[1]);

            String singers = split[2];
            String[] singersName = singers.split("\\|");
            for (String singerName : singersName) {
                music.addSinger(singerName);
            }

            music.setMediaFilePath(split[3]);
            music.setReleaseDate(new Date(Integer.parseInt(split[4])));
            music.setListenFree("true".equals(split[5]));
            music.setPrice(Float.parseFloat(split[6]));

            musicMap.put(music.getId(), music);
        }
        return musicMap;
    }

    /**
     * 初始化MusicDataManagement
     * @param filepath 用户文件路径
     * @throws IOException
     */
    @Deprecated
    public Map<Integer, ConsumingRecord> initConsumingRecord(String filepath) throws IOException {
        List<String> strings = IOUtil.readTxt(filepath);
        Map<Integer, ConsumingRecord> map = new HashMap<>(strings.size());
        for (String s : strings) {
            String[] split = s.split(",");

            ConsumingRecord record = new ConsumingRecord();
            record.setId(Integer.parseInt(split[0]));
            record.setUserId(Integer.parseInt(split[1]));
            record.setTargetId(Integer.parseInt(split[2]));
            record.setType(split[3]);
            record.setConsumeTime(new Date(Long.parseLong(split[4])));
            record.setPrice(Float.parseFloat(split[5]));

            map.put(record.getId(), record);
        }
        return map;
    }

    /**
     * 初始化MusicDataManagement
     * @param filepath 用户文件路径
     * @throws IOException
     */
    public Map<Integer, RechargeRecord> initRechargeRecord(String filepath) throws IOException {
        List<String> strings = IOUtil.readTxt(filepath);
        Map<Integer, RechargeRecord> musicMap = new HashMap<>(strings.size());
        for (String s : strings) {
            String[] split = s.split(",");

            RechargeRecord record = new RechargeRecord();
            record.setId(Integer.parseInt(split[0]));
            record.setUserId(Integer.parseInt(split[1]));
            record.setRechargeDate(new Date(Long.parseLong(split[2])));
            record.setPrice(Float.parseFloat(split[3]));

            musicMap.put(record.getId(), record);
        }
        return musicMap;
    }

    /**
     * 初始化UserDataManagement
     * @param filepath 用户文件路径
     * @throws IOException
     */
    public MyArrayList<User> initUserData4List(String filepath) throws IOException {
        // 读取用户数据
        List<String> strings = IOUtil.readTxt(filepath);
        MyArrayList<User> list = new MyArrayList<>();
        for (String userStr : strings) {
            String[] split = userStr.split(",");
            User user = new User();
            user.setId(Integer.parseInt(split[0]));
            user.setPassword(split[1]);
            user.setUsername(split[2]);
            // 1为男
            user.setSex("true".equals(split[3]));
            user.setRegisterDate(new Date(Long.parseLong(split[4])));
            // 余额
            user.setBalance(Float.parseFloat(split[5]));
            // 管理员
            user.setAdminType(split[6]);
            list.add(user);
        }
        return list;
    }

    /**
     * 初始化MusicDataManagement
     * @param filepath 用户文件路径
     * @throws IOException
     */
    public MyArrayList<Music> initMusicData4List(String filepath) throws IOException {
        // 读取用户数据
        List<String> strings = IOUtil.readTxt(filepath);
        MyArrayList<Music> list = new MyArrayList<>();
        for (String musicStr : strings) {
            String[] split = musicStr.split(",");
            Music music = new Music();
            music.setId(Integer.parseInt(split[0]));
            music.setName(split[1]);

            String singers = split[2];
            String[] singersName = singers.split("\\|");
            for (String singerName : singersName) {
                music.addSinger(singerName);
            }

            music.setMediaFilePath(split[3]);
            music.setReleaseDate(new Date(Integer.parseInt(split[4])));
            music.setListenFree("true".equals(split[5]));
            music.setPrice(Float.parseFloat(split[6]));

            list.add(music);
        }
        return list;
    }

    public MyArrayList<ConsumingRecord> initConsumingRecord4List(String filepath) throws IOException {
        List<String> strings = IOUtil.readTxt(filepath);
        MyArrayList<ConsumingRecord> list = new MyArrayList<>();
        for (String s : strings) {
            String[] split = s.split(",");

            ConsumingRecord record = new ConsumingRecord();
            record.setId(Integer.parseInt(split[0]));
            record.setUserId(Integer.parseInt(split[1]));
            record.setTargetId(Integer.parseInt(split[2]));
            record.setType(split[3]);
            record.setConsumeTime(new Date(Long.parseLong(split[4])));
            record.setPrice(Float.parseFloat(split[5]));

            list.add(record);
        }
        return list;
    }

    /**
     * 初始化MusicDataManagement
     * @param filepath 用户文件路径
     * @throws IOException
     */
    public MyArrayList<RechargeRecord> initRechargeRecord4List(String filepath) throws IOException {
        List<String> strings = IOUtil.readTxt(filepath);
        MyArrayList<RechargeRecord> list = new MyArrayList<>();
        for (String s : strings) {
            String[] split = s.split(",");

            RechargeRecord record = new RechargeRecord();
            record.setId(Integer.parseInt(split[0]));
            record.setUserId(Integer.parseInt(split[1]));
            record.setRechargeDate(new Date(Long.parseLong(split[2])));
            record.setPrice(Float.parseFloat(split[3]));

            list.add(record);
        }
        return list;
    }
}
