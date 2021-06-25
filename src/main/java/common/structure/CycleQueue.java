package common.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author wtk
 * @description
 * @date 2021-04-25
 */
public class CycleQueue<T> implements MyQueue<T>, Iterable<T> {

    /** 数据 */
    private T[] data;
    /**
     * 头指针
     * 指向队列第一个元素的位置
     * 初始值为0
     */
    private int front;
    /**
     * 尾指针
     * 指向队列最后一个元素的下一个位置
     * 初始值为0
     */
    private int rear;
    private final int MAX_SIZE = 5;

    @SuppressWarnings("unchecked")
    public CycleQueue() {
        data = (T[]) new Object[MAX_SIZE];
        front = 0;
        rear = 0;
    }

    /**
     * 当前队列有效数据的个数
     * @return
     */
    @Override
    public int size() {
        // 假设rear = 2，front = 1，MAX_SIZE = 3，则结果size=1
        // 假设rear = 2，front = 4，MAX_SIZE = 5，则结果size=3
        // 加模取模操作用于消除rear - front结果为负数的影响
        return (rear - front + MAX_SIZE) % MAX_SIZE;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 判断队列是否已满
     * @return
     */
    public boolean isFull() {
        return ((rear + 1) % MAX_SIZE) == front;
    }

    /**
     * 入队
     * @param n
     */
    @Override
    public void offer(T n) {
        if (isFull()) {
            // 已满则先出队再入队
            poll();
        }
        data[rear] = n;
        //将 rear 后移, 这里取模是为了在指针越界的时候使其归零，回到数组头部
        rear = (rear + 1) % MAX_SIZE;
    }


    /**
     * 出队
     * @return
     */
    @Override
    public T poll() {
        // 判断队列是否空
        if (isEmpty()) {
            return null;
        }
        // front是指向队列的第一个元素
        T poll = data[front];
        data[front] = null;
        // 将 front 后移, 这里取模是为了在指针越界的时候使其归零，回到数组头部
        front = (front + 1) % MAX_SIZE;
        return poll;
    }

    @Override
    public List<T> findAll() {
        List<T> datalist = new ArrayList<>(size());
        // 复制
        for (int index = front; index != rear; index = (index + 1) % MAX_SIZE) {
            datalist.add(data[index]);
        }
        return datalist;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = front;

            @Override
            public boolean hasNext() {
                return index != rear;
            }

            @Override
            public T next() {
                T next = data[index];
                index = (index + 1) % MAX_SIZE;
                return next;
            }
        };
    }
}
