package pojo.po;

import com.sun.corba.se.spi.ior.Identifiable;
import common.enums.AdminType;
import util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wtk
 * @description 用户
 * @date 2021-05-24
 */
public class User extends Identify {

    private String password;
    private boolean sex;
    /** 用户名 */
    private String username;
    /** 注册时间 */
    private Date registerDate;
    /** 余额 */
    private float balance;
    /** 是否为管理员 */
    private String adminType;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", username='" + username + '\'' +
                ", registerDate=" + registerDate +
                ", balance=" + balance +
                ", adminType=" + adminType +
                '}';
    }

    /**
     * 账号信息，不包含隐私数据
     * @return
     */
    public String getInfo() {
        return "--------------------- 账号信息 ---------------------\n账号ID：" + getId()
                + "\n用户名：\t" + username
                + "\n性别：\t" + (sex ? "男" : "女")
                + "\n注册时间：\t" + DateUtil.dateFormat(registerDate)
                + "\n账号余额：\t" + balance
                + "\n管理员类型：\t" + AdminType.getTypeDescription(adminType)
                + "\n---------------------------------------------------";
    }

    public String getAdminInfo() {
        return "--------------------- 账号信息 ---------------------" +
                "\n账号ID：" + getId() + "\t用户名：" +  username +
                "\n管理员类型：" + AdminType.getTypeDescription(adminType) +
                "\n---------------------------------------------------";
    }

    public String getAdminType() {
        return adminType;
    }

    public void setAdminType(String adminType) {
        this.adminType = adminType;
    }

    public User() {
    }

    public User(int id, String password) {
        this.password = password;
        setId(id);
    }
}
