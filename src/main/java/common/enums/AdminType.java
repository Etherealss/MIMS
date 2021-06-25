package common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wtk
 * @description
 * @date 2021-05-26
 */
public class AdminType {
    public static final String USER_ADMIN = "UserAdmin";
    public static final String MUSIC_ADMIN = "MusicAdmin";
    public static final String SYSTEM_ADMIN = "SystemAdmin";
    public static final String DATA_ADMIN = "DataAdmin";
    public static final String NOT_ADMIN = "NotAdmin";

    private static Map<String, String> adminTypeMap = new HashMap<>(3);

    static {
        adminTypeMap.put(USER_ADMIN, "用户管理员");
        adminTypeMap.put(MUSIC_ADMIN, "音乐管理员");
        adminTypeMap.put(SYSTEM_ADMIN, "系统管理员");
        adminTypeMap.put(DATA_ADMIN, "数据管理员");
        adminTypeMap.put(NOT_ADMIN, "非管理员");
    }

    /**
     * 返回管理员类型对应的中文含义
     * @param type 管理员类型
     * @return 管理员类型对应的中文含义，可能为null
     */
    public static String getTypeDescription(String type) {
        return adminTypeMap.get(type);
    }
}
