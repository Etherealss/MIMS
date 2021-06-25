package common.structure;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CycleQueueTest {

    private Logger logger = LoggerFactory.getLogger(CycleQueueTest.class);

    private CycleQueue<String> myQueue = new CycleQueue<>();

    @Test
    public void test1() throws Exception {
        myQueue.offer("A");
        myQueue.offer("S");
        myQueue.offer("D");
        myQueue.offer("E");
        myQueue.offer("G");
        myQueue.offer("H");
        myQueue.offer("J");
        boolean empty = myQueue.isEmpty();
        logger.debug("是否为空：{}",empty);
        int size = myQueue.size();
        logger.debug("元素个数{}",size);
        myQueue.poll();
        myQueue.poll();
        myQueue.poll();
        myQueue.poll();
        logger.debug("去掉4个元素后：{}",myQueue.size());
        String poll = myQueue.poll();
        logger.debug(poll);
    }

    @Test
    public void testOffer() throws Exception {
        myQueue.offer("ABC");
    }

    @Test
    public void testPoll() throws Exception {
        myQueue.offer("ABC");
        String poll = myQueue.poll();
        logger.debug(poll);
    }

    @Test
    public void testIterator() throws Exception {
        myQueue.offer("A");
        myQueue.offer("S");
        myQueue.offer("D");
        myQueue.offer("E");
        myQueue.offer("G");
        myQueue.offer("H");
        myQueue.offer("J");
        System.out.println(myQueue.size());
        for (String s : myQueue) {
            System.out.println(s);
        }
    }
}