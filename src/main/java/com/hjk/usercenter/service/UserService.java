package com.hjk.usercenter.service;

import com.hjk.usercenter.Model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author hjk
 */
public interface UserService extends IService<User> {
    /**
     *用户注册方法
     * @param userAccount 账户
     * @param userPassword 密码
     * @param checkPassword 校验密码
     * @param planetCode 邀请码
     * @return 账户id
     */
    long userRegister(String userAccount, String userPassword,String checkPassword, String planetCode);

    /**
     * 用户登录方法
     * @param userAccount 账户
     * @param userPassword 密码
     * @param request 请求
     * @return User 用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param user 未脱敏用户
     * @return User 脱敏用户
     */
    User getSafetyUser(User user);

    /**
     * 用户注销
     * @param request 请求
     * @return 标识
     */
    int userLogout(HttpServletRequest request);
}
