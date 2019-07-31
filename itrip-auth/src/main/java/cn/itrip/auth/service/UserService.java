package cn.itrip.auth.service;

import cn.itrip.beans.pojo.ItripUser;

public interface UserService {
    //根据手机号查询用户
    ItripUser findUserByUserCode(String userCode) throws  Exception;
    //
    int itripCreateItripUser(ItripUser itripUser) throws Exception;

    boolean validatePhone(String user, String code) throws Exception;

}
