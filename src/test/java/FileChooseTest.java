import org.junit.Test;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

/**
 * @author wtk
 * @description
 * @date 2021-06-03
 */
public class FileChooseTest {
    @Test
    public void test() throws Exception {
        //设置界面风格
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFileChooser chooser = new JFileChooser("d:/");
        //设置选择路径模式
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //过滤文件类型
        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt文件", "txt");
        // 设置过滤器
        chooser.setFileFilter(filter);
        //设置默认文件名
        chooser.setSelectedFile(new File("UserData.txt"));
        //设置对话框标题
        chooser.setDialogTitle("请选择文件路径");
        // 设置弹窗名
        chooser.setDialogTitle("保存文件");
        chooser.setMultiSelectionEnabled(false);
        if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(null)) {
            //用户点击了确定
            //取得路径选择
            String path = chooser.getSelectedFile().getAbsolutePath();
        }
    }


}
