package cn.itrip.auth.service;

import cn.itrip.beans.pojo.ItripUser;

public interface TokenService {
    String TOKEN_PREFIX="token:";
    int TOKEN_EXPIRE=2*60*60;//登录认证token有效期
    int TOKEN_PROTECT_TIME=60*60;//token置换保护期
    /**
     * 生成token，根据用户信息以及客户端信息
     * @param agent  客户端信息
     * @param itripUser
     * @return
     * @throws Exception
     */
    String generateToken(String agent, ItripUser itripUser) throws Exception;

    /**
     * 保存token到redis
     * @param token
     * @param itripUser
     * @throws Exception
     */
    void saveToken(String token,ItripUser itripUser)throws  Exception;

    boolean validate(String agent,String token) throws  Exception;

    void delToken(String token) throws Exception;

    String reloadToken(String agent,String token) throws Exception;
}
