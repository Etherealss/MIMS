package pojo.po;

import com.sun.corba.se.spi.ior.Identifiable;
import util.DateUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wtk
 * @description 音乐
 * @date 2021-05-24
 */
public class Music extends Identify {

    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy年MM月dd日");

    /** 音乐名 */
    private String name;
    /** 歌手 */
    private List<String> singers = new ArrayList<>(1);
    /** 媒体文件路径 */
    private String mediaFilePath;
    /** 歌曲发行时间 */
    private Date releaseDate;
    /** 是否免费听 */
    private boolean listenFree;
    /** 价格 */
    private float price;

    public Music() {
    }

    public void addSinger(String singer) {
        singers.add(singer);
    }

    public List<String> getSingers() {
        return singers;
    }

    public void setSingers(List<String> singers) {
        this.singers = singers;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", singer='" + singers + '\'' +
                ", mediaFilePath='" + mediaFilePath + '\'' +
                ", releaseDate=" + releaseDate +
                ", listenFree=" + listenFree +
                ", price=" + price +
                '}';
    }

    public String getInfo() {
        return "音乐信息：" +
                "音乐ID：\t" + getId() +
                "\n音乐名：\t'" + name + '\'' +
                "\n歌手：\t'" + singers + '\'' +
                "\n媒体文件路径：\t'" + mediaFilePath + '\'' +
                "\n发行时间：\t" + DateUtil.dateFormat(releaseDate) +
                "\n是否免费听：\t" + (listenFree ? "是" : "否") +
                "\n售价：\t" + price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMediaFilePath() {
        return mediaFilePath;
    }

    public void setMediaFilePath(String mediaFilePath) {
        this.mediaFilePath = mediaFilePath;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean getListenFree() {
        return listenFree;
    }

    public void setListenFree(boolean listenFree) {
        this.listenFree = listenFree;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
