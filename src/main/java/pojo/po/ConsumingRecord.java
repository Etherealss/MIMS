package pojo.po;

import com.sun.corba.se.spi.ior.Identifiable;

import java.util.Date;

/**
 * @author wtk
 * @description 消费记录
 * @date 2021-05-24
 */
public class ConsumingRecord extends Identify {

    /** 操作用户 */
    private int userId;
    /** 消费目标 */
    private int targetId;
    /** 消费类型 */
    private String type;
    /** 消费时间 */
    private Date consumeTime;
    /** 消费金额 */
    private float price;

    @Override
    public String toString() {
        return "ConsumingRecord{" +
                "id=" + getId() +
                ", userId=" + userId +
                ", targetId=" + targetId +
                ", type='" + type + '\'' +
                ", consumeDate=" + consumeTime +
                ", price=" + price +
                '}';
    }

    public ConsumingRecord() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Date consumeTime) {
        this.consumeTime = consumeTime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
