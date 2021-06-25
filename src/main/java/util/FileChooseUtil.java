package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

/**
 * @author wtk
 * @description
 * @date 2021-06-03
 */
public class FileChooseUtil {

    private static Logger logger = LoggerFactory.getLogger("root");

    /**
     * 选择文件的保存路径
     * @param defaultName
     * @return
     * @throws ClassNotFoundException
     * @throws UnsupportedLookAndFeelException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static String chooseSavePath(String defaultName) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        logger.trace("选择文件");
        //设置界面风格
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // 获取桌面
        File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
        // 设置默认路径为桌面
        JFileChooser chooser = new JFileChooser(homeDirectory);
        //设置选择路径模式
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //过滤文件类型
        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt文件", "txt");
        // 设置过滤器
        chooser.setFileFilter(filter);
        //设置默认文件名
        chooser.setSelectedFile(new File(defaultName));
        //设置对话框标题
        chooser.setDialogTitle("请选择文件路径");
        // 设置弹窗名
        chooser.setDialogTitle("保存文件");
        chooser.setMultiSelectionEnabled(false);
        if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(null)) {
            //用户点击了确定
            //取得路径选择
            String path = chooser.getSelectedFile().getAbsolutePath();
            return path;
        }
        return null;
    }
}
