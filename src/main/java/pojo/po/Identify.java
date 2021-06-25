package pojo.po;


/**
 * @author wtk
 * @description 因为要实现排序算法，需要比较，而比较都是通过id比较。
 * 所以加一个抽象类，表示一个类一定具有id属性，并且通过id相互比较
 * @date 2021-06-23
 */
public abstract class Identify implements Comparable<Identify> {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Identify o) {
        return Integer.compare(id, o.getId());
    }

    public int compareTo(int antherId) {
        return Integer.compare(id, antherId);
    }
}
