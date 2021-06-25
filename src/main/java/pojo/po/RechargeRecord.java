package pojo.po;

import com.sun.corba.se.spi.ior.Identifiable;
import common.enums.AdminType;
import util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wtk
 * @description 充值记录
 * @date 2021-05-24
 */
public class RechargeRecord extends Identify {
    /** 充值的用户id */
    private int userId;
    /** 充值日期 */
    private Date rechargeDate;
    /** 充值金额 */
    private float price;

    @Override
    public String toString() {
        return "RechargeRecord{" +
                "id=" + getId() +
                ", userId=" + userId +
                ", rechargeDate=" + rechargeDate +
                ", price=" + price +
                '}';
    }

    /**
     * 充值记录信息
     * @return
     */
    public String getInfo() {
        return "ID：" + getId()
                + "\n用户ID：\t" + userId
                + "\n充值金额：\t" + price + "元"
                + "\n充值时间：\t" + DateUtil.dateFormat(rechargeDate);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(Date rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public RechargeRecord() {
    }

}
