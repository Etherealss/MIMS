package common.structure;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author wtk
 * @description
 * @date 2021-05-19
 */
public class MyArrayList<T> extends AbstractList<T> implements Iterable<T>, Serializable {

    private T[] elements;

    /**
     * 指向第一个可存储的位置
     */
    private int size = 0;

    public MyArrayList() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int init) {
        elements = (T[]) new Object[init];
    }

    /**
     * @param index
     * @return 越界了就返回true
     */
    private boolean isIndexoutOfBounds(int index) {
        return index >= elements.length || index < 0;
    }

    @Override
    public T get(int index) {
        if (isIndexoutOfBounds(index)) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }

    public T find(int index) {
        if (isIndexoutOfBounds(index)) {
            return null;
        }
        return elements[index];
    }

    @Override
    public boolean add(T t) {
        ensureExplicitCapacity();
        subAdd(size, t);
        return true;
    }

    @Override
    public T set(int index, T element) {
        if (isIndexoutOfBounds(index)) {
            throw new IndexOutOfBoundsException();
        }
        T oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    /**
     * 将要添加的新元素添加到index对应的下标，原本位置上的元素整体往后移
     * @param index
     * @param element
     */
    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        ensureExplicitCapacity(size + 1);

        // 往后移
        if (size + 1 - index >= 0) {
            /*
            src ：原数组；
            srcPos ：原数组中的起始索引；
            dest ：目标数组；
            destPos ：目标数组中的起始索引；
            length ：要复制的数组长度。
             */
            // 因为这是数组增长1，所以length = size-index+1，保证length >= 0
            System.arraycopy(elements, index, elements, index + 1, size + 1 - index);
        }
        subAdd(index, element);
    }

    /**
     * 添加一个元素，没有任何检查操作
     * @param index
     * @param element
     */
    private void subAdd(int index, T element) {
        elements[index] = element;
        size++;
    }

    @Override
    public T remove(int index) {
        if (isIndexoutOfBounds(index)) {
            throw new IndexOutOfBoundsException();
        }
        T res = elements[index];
        if (size - 1 - index >= 0) {
            // 因为这是数组减少1，所以length = size-index-1，保证length >= 0
            System.arraycopy(elements, index + 1, elements, index, size - 1 - index);
        }
        size--;
        // 所有元素往前移，最后一个元素是多余的
        elements[size] = null;
        return res;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        elements = (T[]) new Object[size];
        size = 0;
    }

    public T replace(int index, T value) {
        if (isIndexoutOfBounds(index)) {
            throw new IndexOutOfBoundsException();
        }
        T origin = elements[index];
        elements[index] = value;
        return origin;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                T res = elements[index];
                index++;
                return res;
            }
        };
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(Object o) {
        T res = null;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                res = elements[i];
                index = i;
                break;
            }
        }
        if (res == null) {
            return false;
        }
        for (int i = index; i < size - 1; i++) {
            elements[index] = elements[index + 1];
        }
        size--;
        // 所有元素往前移，最后一个元素是多余的
        elements[size] = null;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 判断是否要扩展数组
     */
    private void ensureExplicitCapacity() {
        // 如果minCapacity等于数组长度，说明minCapacity对应的下标已经存不下数据了，要膨胀数组
        ensureExplicitCapacity(size);
    }

    /**
     * 判断是否要扩展数组
     * @param minCapacity
     */
    private void ensureExplicitCapacity(int minCapacity) {
        // 如果minCapacity等于数组长度，说明minCapacity对应的下标已经存不下数据了，要膨胀数组
        if (elements.length == 0) {
            elements = (T[]) new Object[3];
        }
        while (minCapacity >= elements.length) {
            growCapacity(elements.length * 2);
        }
    }

    /**
     * 膨胀数组，扩展容量
     * @param newCapacity
     */
    private void growCapacity(int newCapacity) {
        elements = Arrays.copyOf(elements, newCapacity);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() == 0) {
            return false;
        }
        ensureExplicitCapacity(size + c.size());
        for (T t : c) {
            subAdd(size, t);
        }
        return true;
    }
}
