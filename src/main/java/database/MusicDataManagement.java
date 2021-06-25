package database;

import database.base.BaseDataManagementByList;
import pojo.po.Music;

/**
 * @author wtk
 * @description
 * @date 2021-05-29
 */
public class MusicDataManagement extends BaseDataManagementByList<Music> {
    private static MusicDataManagement management = new MusicDataManagement();
    private MusicDataManagement(){}
    public static MusicDataManagement getManagement() {
        return management;
    }
}
