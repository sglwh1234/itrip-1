package cn.itrip.auth.service;

import cn.itrip.beans.pojo.ItripUser;

public interface UserService {
    public ItripUser findUserByUserCode(String userCode) throws  Exception;

    int createItripUser(ItripUser itripUser) throws Exception;
}
