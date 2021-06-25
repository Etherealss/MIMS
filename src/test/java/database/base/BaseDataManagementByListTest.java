package database.base;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.po.Music;

import java.util.List;

public class BaseDataManagementByListTest {

    private Logger logger = LoggerFactory.getLogger("testLogger");

    BaseDataManagementByList<Music> management = new BaseDataManagementByList<>();

    @Before
    public void dodefore() throws Exception {
        Music music1 = new Music();
        music1.setId(1);
        Music music2 = new Music();
        music2.setId(2);
        Music music3 = new Music();
        music3.setId(3);
        Music music4 = new Music();
        music4.setId(4);
        Music music5 = new Music();
        music5.setId(1);
        Music music6 = new Music();
        music6.setId(2);
        management.add(music2);
        management.add(music3);
        management.add(music1);
        management.add(music4);
        management.add(music5);
        management.add(music6);

    }

    @Test
    public void testInitData() throws Exception {
        logger.debug("{}", management.size());
    }

    @Test
    public void testGetDataList() throws Exception {
        List<Music> dataList = management.getDataList();
        for (Music music : dataList) {
            logger.debug("{}", music.getId());
        }
    }

    @Test
    public void testAdd() throws Exception {
    }

    @Test
    public void testDelete() throws Exception {
        Music delete = management.delete(4);
        logger.debug("{}", delete.getId());
    }

    @Test
    public void testUpdate() throws Exception {
        Music music = new Music();
        music.setId(3);
        music.setName("23");
        Music update = management.update(music);
        logger.debug("{}", update);
        Music music1 = management.find(3);
        logger.debug("{}", music1);

    }

    @Test
    public void testFindRange() throws Exception {
        List<Music> all = management.findRange(1,3);
        for (Music music : all) {
            logger.debug("{}", music);
        }
    }

    @Test
    public void testFindAll() throws Exception {
        List<Music> all = management.findAll();
        for (Music music : all) {
            logger.debug("{}", music);
        }
    }

    @Test
    public void testSize() throws Exception {
        logger.debug("{}", management.size());
    }
}