package com.hjk.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjk.usercenter.Model.User;
import com.hjk.usercenter.common.ErrorCode;
import com.hjk.usercenter.exception.BusinessException;
import com.hjk.usercenter.service.UserService;
import com.hjk.usercenter.Mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.hjk.usercenter.constant.UserConstant.USER_LOGIN_STATE;

/**
 *
 * @author hjk
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Resource
    private UserMapper userMapper;
    /**
     * 盐值
     */
    private final String SALT = "hjk";
    /**
     * 用户注册
     * @param userAccount 账户
     * @param userPassword 密码
     * @param checkPassword 校验密码
     * @return id 用户id
     */
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword, String planetCode) {
        //校验不为空
        if(StringUtils.isAllBlank(userAccount, userPassword,checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if(userAccount.length() < 1){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户注册账户过短");
        }
        if(userPassword.length() < 8 || checkPassword.length() < 8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户注册密码过短");
        }
        if(planetCode.length() > 5){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "邀请码过短");
        }
        //不包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if(matcher.find()){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "含特殊字符");
        }
        if(!userPassword.equals(checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次密码不一致");
        }
        //账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if(count > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账户重复");
        }
        //邀请码不能重复
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("planet_code", userAccount);
        count = userMapper.selectCount(queryWrapper);
        if(count > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"邀请码重复");
        }

        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //插入
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setPlanetCode(planetCode);
        boolean saveResult = this.save(user);
        if(!saveResult) {
            throw new BusinessException(ErrorCode.NULL_ERROR ,"未插入成功");
        }
        return user.getId();
    }
    //todo
    /**
     * 用户登录
     * @param userAccount 账户名
     * @param userPassword 密码
     * @param request 请求
     * @return User 用户类
     */
    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        if(request == null){
            throw new BusinessException(ErrorCode.NULL_ERROR ,"请求为空");
        }
        if(StringUtils.isAllBlank(userAccount, userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        if(userAccount.length() < 1){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号长度不符合");
        }
        if(userPassword.length() < 8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码长度不符合");
        }
        //不包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if(matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码长度不符合");
        }
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //查询数据库
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        queryWrapper.eq("user_password", encryptPassword);
        User user =  userMapper.selectOne(queryWrapper);
        //用户不存在
        if(user == null){
            log.info("user login failed");
            throw new BusinessException(ErrorCode.NULL_ERROR ,"用户不存在");
        }
        User safeUser = getSafetyUser(user);
        request.getSession().setAttribute(USER_LOGIN_STATE, safeUser);
        return safeUser;
    }

    /**
     * 用户脱敏
     * @param user 未脱敏用户
     * @return User 脱敏用户
     */
    @Override
    public User getSafetyUser(User user){
        User safeUser = new User();
        safeUser.setId(user.getId());
        safeUser.setUserName(user.getUserName());
        safeUser.setUserAccount(user.getUserAccount());
        safeUser.setAvatarUrl(user.getAvatarUrl());
        safeUser.setGender(user.getGender());
        safeUser.setEmail(user.getEmail());
        safeUser.setUserRole(user.getUserRole());
        safeUser.setUserStatus(user.getUserStatus());
        safeUser.setPhone(user.getPhone());
        safeUser.setGmtCreate(user.getGmtCreate());
        safeUser.setPlanetCode(user.getPlanetCode());
        return safeUser;
    }

    @Override
    public int userLogout(HttpServletRequest request) {
        //移除登陆状态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }
}




