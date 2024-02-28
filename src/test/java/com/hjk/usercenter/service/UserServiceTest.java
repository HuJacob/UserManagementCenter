package com.hjk.usercenter.service;

import com.hjk.usercenter.Model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * MybatiesX测试
 * @author hjk
 */
@SpringBootTest
class UserServiceTest {
    @Resource
    private UserService userService;
    @Test
    public void testAddUser(){
        User user = new User();
        user.setUserName("testName");
        user.setUserAccount("https://");
        user.setAvatarUrl("");
        user.setGender((byte) 0);
        user.setUserPassword("gdfgdfg");
        user.setEmail("gfdgdg");
        user.setPhone("gdfgdfg");

        boolean result = userService.save(user);
        Assert.assertTrue(result);
    }

    @Test
    void userRegister() {
        String userAccount = "hjk";
        String userPassword = "12345678";
        String checkPassword = "12345678";
        long result = userService.userRegister(userAccount, userPassword,
                checkPassword);
        Assertions.assertNotEquals(-1, result);
        System.out.println(result);
        Assertions.assertTrue(result > 0);
    }
}