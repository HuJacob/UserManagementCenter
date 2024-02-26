package com.hjk.usercenter.service;
import java.util.Date;

import com.hjk.usercenter.Model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

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
        user.setUser_name("testName");
        user.setUser_account("https://");
        user.setAvatar_url("");
        user.setGender((byte) 0);
        user.setUser_password("gdfgdfg");
        user.setEmail("gfdgdg");
        user.setPhone("gdfgdfg");

        boolean result = userService.save(user);
        Assert.assertTrue(result);
    }

    @Test
    void userRegister() {
        String userAccount = "yupi";
        String userPassword = "";
        String checkPassword = "123456";
        long result = userService.userRegister(userAccount, userPassword,
                checkPassword);
        Assertions.assertEquals(-1, result);
        userAccount = "yu";
        result = userService.userRegister(userAccount, userPassword,
                checkPassword);
        Assertions.assertEquals(-1, result);
        userAccount = "yupi";
        userPassword = "123456";
        result = userService.userRegister(userAccount, userPassword,
                checkPassword);
        Assertions.assertEquals(-1, result);
        userAccount = "yu pi";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword,
                checkPassword);
        Assertions.assertEquals(-1, result);
        checkPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword,
                checkPassword);
        Assertions.assertEquals(-1, result);
        userAccount = "dogyupi";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword,
                checkPassword);
        Assertions.assertEquals(-1, result);
        userAccount = "yupi";
        userPassword = "12345678";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword,
                checkPassword);
        System.out.println(result);
        Assertions.assertTrue(result > 0);
    }
}