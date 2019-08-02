package cn.itrip.auth.service;

import cn.itrip.beans.pojo.ItripUser;

public interface UserService {
    //根据手机号查询用户
    ItripUser findUserByUserCode(String userCode) throws  Exception;
    //本地注册创建用户
    int itripCreateItripUser(ItripUser itripUser) throws Exception;

    boolean validatePhone(String user, String code) throws Exception;

    //用户名密码验证
    ItripUser login(String userCode,String password) throws  Exception;
    //微信登录创建用户
    void vendorsCreateItripUser(ItripUser user) throws Exception;
}
