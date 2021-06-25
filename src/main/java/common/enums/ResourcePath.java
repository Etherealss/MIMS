package common.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author wtk
 * @description
 * @date 2021-06-03
 */
public class ResourcePath {

    private static Logger logger = LoggerFactory.getLogger("root");

    /** 用户资源路径 */
    public static final String USER_DATA_PATH;
    /** 音乐资源路径 */
    public static final String MUSIC_DATA_PATH;
    /** 消费记录资源路径 */
    public static final String CONSUMING_RECORD_PATH;
    /** 充值记录资源路径 */
    public static final String RECHARGE_RECORD_PATH;

    static {
        //获取配置文件
        InputStream is = ResourcePath.class.getClassLoader().getResourceAsStream("ResourcePath.properties");
        Properties prop = new Properties();
        try {
            //读取
            prop.load(is);
        } catch (Exception e) {
            logger.error("初始化参数失败", e);
        }

        USER_DATA_PATH = prop.getProperty("UserData");
        MUSIC_DATA_PATH = prop.getProperty("MusicData");
        CONSUMING_RECORD_PATH = prop.getProperty("ConsumingRecord");
        RECHARGE_RECORD_PATH = prop.getProperty("RechargeRecord");

        // 检查配置参数指向的文件或目录是否存在
        checkPathExists();
    }

    /**
     * 检查配置参数指向的文件或目录是否存在
     */
    private static void checkPathExists() {
        checkDirExists(USER_DATA_PATH);
        checkDirExists(MUSIC_DATA_PATH);
        checkDirExists(CONSUMING_RECORD_PATH);
        checkDirExists(RECHARGE_RECORD_PATH);
    }

    /**
     * 检查文件或目录是否存在
     * @param path 路径
     */
    private static void checkDirExists(String path) {
        File file = new File(path);
        if (!file.exists()) {
            logger.error("目录或文件不存在：" + file.getName());
        }
    }
}
