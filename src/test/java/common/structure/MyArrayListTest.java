package common.structure;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MyArrayListTest {

    private Logger logger = LoggerFactory.getLogger("testLogger");


    private List<Integer> list = new MyArrayList<>(2);

    @Before
    public void doBefore() throws Exception {
        list.add(1);
        list.add(2);
    }

    @Test
    public void testGet() throws Exception {
    }

    @Test
    public void testFind() throws Exception {
    }

    @Test
    public void testAdd() throws Exception {
    }

    @Test
    public void testSet() throws Exception {
    }

    @Test
    public void testTestAdd() throws Exception {
    }

    @Test
    public void testRemove() throws Exception {
    }

    @Test
    public void testIndexOf() throws Exception {
    }

    @Test
    public void testLastIndexOf() throws Exception {
    }

    @Test
    public void testClear() throws Exception {
    }

    @Test
    public void testReplace() throws Exception {
    }

    @Test
    public void testIterator() throws Exception {
    }

    @Test
    public void testIsEmpty() throws Exception {
    }

    @Test
    public void testTestRemove() throws Exception {
    }

    @Test
    public void testSize() throws Exception {
    }

    @Test
    public void testAddAll() throws Exception {
    }
}