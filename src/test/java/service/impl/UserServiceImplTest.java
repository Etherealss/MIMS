package service.impl;

import common.factory.ServiceFactory;
import common.listener.ApplicationListener;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.po.User;
import service.UserService;

import java.io.IOException;
import java.util.List;

public class UserServiceImplTest {

    private Logger logger = LoggerFactory.getLogger("testLogger");

    UserService userService = ServiceFactory.getUserService();

    @Before
    public void doBefore() throws IOException {
        ApplicationListener listener = new ApplicationListener();
        listener.init();
    }

    @Test
    public void testLogin() throws Exception {
        User user =new User();
        user.setId(3);
        user.setPassword("abc003");
        User login = userService.login(user);
        logger.debug(login.toString());
    }

    @Test
    public void testFindUser() throws Exception {
        User user = userService.findUser(1);
        logger.debug(user.toString());
    }

    @Test
    public void testRegister() throws Exception {
//        User user = new User();
//        user.setId(9);
//        user.setPassword("abc009");
//        user.setUsername("阿九");
//        user.setSex(true);
//        userService.register(user);
    }


    @Test
    public void testUpdateUser() throws Exception {
        User user = userService.findUser(3);
        user.setUsername("阿九九");
        userService.updateUser(user);
    }

    @Test
    public void testUpdateUserPassword() throws Exception {
        User user = userService.findUser(9);
        userService.updateUserPassword(user.getId(), user.getPassword(), "abc0099");
    }

    @Test
    public void testFindAllAdminUser() throws Exception {
        List<User> allAdminUser = userService.findAllAdminUser();
        StringBuilder test = new StringBuilder();
        for (User user : allAdminUser) {
            test.append(user.getAdminInfo()).append("\n");
        }
        logger.debug(test.toString());
    }

    @Test
    public void testDeleteUser() throws Exception {
        userService.deleteUser(9);
    }
}