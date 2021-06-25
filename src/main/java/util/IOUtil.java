package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wtk
 * @description IO工具
 * @date 2021-05-26
 */
public class IOUtil {

    /**
     * 读取txt文件
     * @param path 文件路径
     * @return
     * @throws IOException
     * @throws FileNotFoundException 文件路径错误
     */
    public static List<String> readTxt(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            // 不存在
            throw new FileNotFoundException("文件路径path错误");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> res = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                res.add(line);
            }
            return res;
        }
    }

    /**
     * 保存数据
     * @param data 数据
     * @param path 保存路径
     * @throws IOException
     */
    public static void write2Txt(List<String> data, String path) throws IOException {
        File file = new File(path);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            // 不存在父目录，则创建
            boolean mkdirs = parentFile.mkdirs();
        }
        if (!file.exists()) {
            // 不存在文件，则创建。该步骤要确保路径已存在
            boolean newFile = file.createNewFile();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String datum : data) {
                writer.write(datum);
                writer.newLine();
                writer.flush();
            }
        }
    }
}
