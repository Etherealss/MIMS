package util;

import common.structure.MyArrayList;
import pojo.po.Identify;

import java.util.List;

/**
 * @author wtk
 * @description
 * @date 2021-06-23
 */
public class SearchUtil {

    /**
     * 二分
     * @param arr
     * @param target
     * @return 目标所在的数组下标
     */
    public static <T extends Comparable<? super T>> int binarySearch(T[] arr, T target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (target.compareTo(arr[mid]) > 0) {
                left = mid + 1;
            } else if (target.compareTo(arr[mid]) < 0) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分 链表
     * @param arr
     * @param target 具有id属性的对象
     * @return 目标所在的数组下标
     */
    public static <T extends Identify> int binarySearch(MyArrayList<T> arr, T target) {
        int left = 0, right = arr.size() - 1;

        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (target.compareTo(arr.get(mid)) > 0) {
                left = mid + 1;
            } else if (target.compareTo(arr.get(mid)) < 0) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分 通过id
     * @param arr
     * @param targetId id
     * @return 目标所在的数组下标
     */
    public static <T extends Identify> int binarySearch(MyArrayList<T> arr, int targetId) {
        int left = 0, right = arr.size() - 1;

        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            int compareTo = arr.get(mid).compareTo(targetId);
            if (compareTo < 0) {
                left = mid + 1;
            } else if (compareTo > 0) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
