package cn.itrip.auth.service;

import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.MD5;
import cn.itrip.common.RedisAPI;
import cn.itrip.mapper.itripUser.ItripUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ItripUser findUserByUserCode(String phone) throws Exception{
        Map<String, Object> param=new HashMap<>();
        param.put("userCode",phone);
        List<ItripUser> list = itripUserMapper.getItripUserListByMap(param);
        if(list.size()>0) {
            return list.get(0);
        }
        return  null;
    }
    //创建用户
    @Override
    public int itripCreateItripUser(ItripUser itripUser) throws Exception {
        //发送短信
       String validateCode= String.valueOf(MD5.getRandomCode());
       String expire="1";
       smsService.sendTemplateSMS(itripUser.getUserCode(),"1",new String[]{validateCode,expire});
        //缓存验证码
        redisAPI.set("activation:"+itripUser.getUserCode(),Integer.parseInt(expire)*60,validateCode);
        //写入数据库（判断是否已经存在）
//        System.out.println(itripUser.getUserCode()+"-------------------------");
        if (this.findUserByUserCode(itripUser.getUserCode()) == null) {
            itripUser.setUserType(0);
            itripUser.setUserPassword(MD5.getMd5(itripUser.getUserPassword(),32));
            itripUser.setCreationDate(new Date());
            return   itripUserMapper.insertItripUser(itripUser);
        }else{
            return 0;
        }
    }

    @Override
    public boolean validatePhone(String phone, String code)throws Exception {
        String key="activation:"+phone;
        ItripUser user = this.findUserByUserCode(phone);
        if(redisAPI.exist(key)){
            if(redisAPI.get(key).equals(code)){
                if (EmptyUtils.isNotEmpty(user)) {
                    user.setActivated(1);//激活用户
                    System.out.println(user.getId()+"--------------------userId---");
                    user.setFlatID(user.getId());//自注册用户平台id
//                    user.setUserType(0);
                    itripUserMapper.updateItripUser(user);
                    return true;
                }
            }else {
                itripUserMapper.deleteItripUserById(user.getId());
            }
        }
        return false;
    }

}
