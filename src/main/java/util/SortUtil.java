package util;

import common.structure.MyArrayList;
import pojo.po.Identify;

/**
 * @author wtk
 * @description
 * @date 2021-06-23
 */
public class SortUtil {
    /**
     * 插入排序
     * @param arr
     */
    public static <T extends Identify> void insertSort(MyArrayList<T> arr) {
        // 默认第一个数已经排序好，从下标1开始
        int size = arr.size();
        for (int i = 1; i < size; i++) {
            // 将要插入的第i条记录暂存在temp中
            T insertValue = arr.get(i);
            // 将有序序列中比temp大的记录后移
            int j = i - 1;
            while ((j >= 0) && (insertValue.compareTo(arr.get(j)) < 0)) {
                arr.replace(j + 1, arr.get(j));
                j--;
            }
            // 因为最后还有j--才退出循环，所以要j++
            arr.replace(j + 1, insertValue);
        }
    }
}
