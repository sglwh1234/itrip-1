package cn.itrip.auth.service;

import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.mapper.itripUser.ItripUserMapper;
import common.MD5;
import common.RedisAPI;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private ItripUserMapper itripUserMapper;
    @Resource
    private SmsService smsService;
    @Resource
    private RedisAPI redisAPI;

    //根据手机号查询用户
    @Override
    public ItripUser findUserByUserCode(String userCode) throws Exception{
//        itripUserMapper.getItripUserById();
        return null;
    }
    //创建用户
    @Override
    public int createItripUser(ItripUser itripUser) throws Exception {
        //发送短信
       String validateCode= String.valueOf(MD5.getRandomCode());
       String expire="1";
       smsService.sendTemplateSMS(itripUser.getUserCode(),"1",new String[]{validateCode,expire});
        //缓存验证码
        redisAPI.set("activation:"+itripUser.getUserCode(),Integer.parseInt(expire)*60,validateCode);
        //写入数据库
        System.out.println(itripUser.getUserCode()+"-------------------------");
        return   itripUserMapper.insertItripUser(itripUser);
    }
}
