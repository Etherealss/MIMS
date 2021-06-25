package database.base;

import common.structure.MyArrayList;
import pojo.po.Identify;
import util.SearchUtil;
import util.SortUtil;

import java.util.*;

/**
 * @author wtk
 * @description 将4个DataManagement抽象出一个父类，概括所有CRUD操作
 * @date 2021-06-01
 */
public class BaseDataManagementByList<T extends Identify> {

    private MyArrayList<T> data = new MyArrayList<>();

    /**
     * 初始化数据
     * @param data
     */
    public void initData(MyArrayList<T> data) {
        this.data = data;
    }

    public List<T> getDataList() {
        List<T> list = new ArrayList<>(data.size());
        // 深复制
        list.addAll(data);
        return list;
    }

    /**
     * @param value
     * @return 返回先前的value，如果没有，则返回null
     */
    public T add(T value) {
        // 检查id
        if (value.getId() <= 0) {
            // 赋最新id号
            value.setId(this.findLast().getId()+1);
        }
        int index = SearchUtil.binarySearch(data, value);
        if (index >= 0) {
            // 已有相同元素
            return data.replace(index, value);
        }
        // 没有相同元素
        data.add(value);
        SortUtil.insertSort(data);
        return null;
    }

    public T delete(int id) {
        int index = SearchUtil.binarySearch(data, id);
        if (index >= 0) {
            return data.remove(index);
        }
        return null;
    }

    /**
     * 旧数据
     * @param value
     * @return
     */
    public T update(T value) {
        int index = SearchUtil.binarySearch(data, value);
        if (index >= 0) {
            return data.replace(index, value);
        } else {
            return null;
        }
    }


    public T find(int id) {
        return data.find(SearchUtil.binarySearch(data, id));
    }

    /**
     * 查询[startKey, endKey)的数据，key即id
     * @param startKey
     * @param endKey
     * @return
     */
    public List<T> findRange(int startKey, int endKey) {
        List<T> list = new ArrayList<>(endKey - startKey);
        // 找到范围查询的第一个索引
        int startIndex = SearchUtil.binarySearch(data, startKey);
        // 将这个索引之后的endKey - startKey个元素添加到列表中返回
        for (int i = startIndex; i < endKey - startKey; i++) {
            T value = data.get(i);
            list.add(value);
        }
        return list;
    }

    public List<T> findAll() {
        List<T> list = new ArrayList<>(data.size());
        list.addAll(data);
        return list;
    }

    public int size() {
        return data.size();
    }

    /**
     * 获取最后一个元素，用于获取id
     * @return
     */
    public T findLast() {
        return data.get(size()-1);
    }
}
