package database.base;

import pojo.po.ConsumingRecord;

import java.util.*;

/**
 * @author wtk
 * @description 将4个DataManagement抽象出一个父类，概括所有CRUD操作
 * 基于HashMap实现，已废弃，替换为自己写的MyArrayList
 * @date 2021-06-01
 */
@Deprecated
public class BaseDataManagement<K,V> {

    private Map<K,V> data = new HashMap<>();

    /**
     * 初始化数据
     * @param data
     */
    public void initData(Map<K,V> data) {
        this.data = data;
    }

    public List<V> getDataList() {
        List<V> list = new ArrayList<>(data.size());
        for (Map.Entry<K, V> entry : data.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    /**
     *
     * @param key
     * @param value
     * @return 返回先前的value，如果没有，则返回null
     */
    public V add(K key, V value) {
        return data.put(key, value);
    }

    public V delete(K key) {
        return data.remove(key);
    }

    /**
     * 旧数据
     * @param key
     * @param value
     * @return
     */
    public V update(K key, V value) {
        return data.replace(key, value);
    }

    public V find(int key) {
        return data.get(key);
    }

    /**
     * 查询[startKey, endKey)的数据
     * @param startKey
     * @param endKey
     * @return
     */
    public List<V> findRange(int startKey, int endKey) {
        List<V> list = new ArrayList<>(endKey - startKey);
        for (int i = 0; i < endKey - startKey; i++) {
            V value = data.get(startKey + i);
            if (value != null) {
                list.add(value);
            }
        }
        return list;
    }

    public List<V> findAll() {
        List<V> list = new ArrayList<>(data.size());
        Set<Map.Entry<K, V>> entries = data.entrySet();
        for (Map.Entry<K, V> entry : entries) {
            list.add(entry.getValue());
        }
        return list;
    }

    public int size() {
        return data.size();
    }
}
