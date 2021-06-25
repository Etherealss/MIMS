package common.structure;

import java.util.List;

/**
 * @author wtk
 * @description 队列接口
 * @date 2021-04-25
 */
public interface MyQueue<T> {
    /**
     * 有效元素个数
     * @return
     */
    int size();

    /**
     * 为空
     * @return
     */
    boolean isEmpty();

    /**
     * 入队
     * @param n
     */
    void offer(T n);

    /**
     * 出队
     * @return
     */
    T poll();

    /**
     * 获取所有
     * @return
     */
    List<T> findAll();
}
