import common.listener.ApplicationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import view.IndexView;

import java.io.IOException;

/**
 * @author wtk
 * @description 主程序
 * @date 2021-05-24
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger("root");

    public static void main(String[] args) {
        ApplicationListener listener = new ApplicationListener();
        try {
            listener.init();
        } catch (IOException e) {
            logger.error("用户初始化错误", e);
            System.exit(0);
        }

        IndexView indexView = new IndexView();
        indexView.enter();

        try {
            // 保存数据
            listener.save();
        } catch (IOException e) {
            logger.error("导出数据异常", e);
        }
    }
}
