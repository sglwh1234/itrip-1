package cn.itrip.auth.service;

import cn.itrip.auth.exception.TokenFailedException;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.common.MD5;
import cn.itrip.common.RedisAPI;
import cn.itrip.common.UserAgentUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service("tokenService")
public class TokenServiceImpl implements TokenService {
    @Resource
    private RedisAPI redisAPI;

    @Override
    public String generateToken(String agent, ItripUser itripUser) throws Exception {
        //格式：token:户端标识-USERCODE-USERID-CREATIONDATE-RONDEM[6位]
        StringBuffer sb=new StringBuffer();
        sb.append(TOKEN_PREFIX);
        //判断是PC 还是Mobile
        if (UserAgentUtil.CheckAgent(agent)) {//mobile
            sb.append("MOBILE");
        }else{//PC
            sb.append("PC");
        }
        sb.append("-");
        sb.append(MD5.getMd5(itripUser.getUserCode(),32));
        sb.append("-");
        sb.append(itripUser.getId());
        sb.append("-");
        //20190801094912
        sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        sb.append("-");
        sb.append(MD5.getMd5(agent,6));
        return sb.toString();
    }

    @Override
    public void saveToken(String token, ItripUser itripUser) throws Exception {
        //判断PC 、MOBILE
        if(token.startsWith(TOKEN_PREFIX+"PC")){
            redisAPI.set(token,TOKEN_EXPIRE,JSON.toJSONString(itripUser));
        }else {
            redisAPI.set(token, JSON.toJSONString(itripUser));
        }

    }

    @Override
    public boolean validate(String agent, String token) throws Exception {
        String agentMD5 = token.split("-")[4];
        if(!agentMD5.equals(MD5.getMd5(agent,6))){//客户端不是登录时的客户端了，认证失败
            throw new TokenFailedException("token认证失败,不是登录的客户端");
        }
        if (!redisAPI.exist(token)) {
            throw  new TokenFailedException("认证失败，未登录或token过期");
        }
        return true;
    }

    @Override
    public void delToken(String token) throws Exception {
        redisAPI.delete(token);
    }

    @Override
    public String reloadToken(String agent, String token) throws Exception {
        //1.验证token有效性
        if (!this.validate(agent,token)) {
            throw new TokenFailedException("token无效");
        }
        //2.是否在保护期
        String createTimeStr = token.split("-")[3];
        long createTime = new SimpleDateFormat("yyyyMMddHHmmss").parse(createTimeStr).getTime();
        long currentTime= Calendar.getInstance().getTimeInMillis();
        //1个小时保护期内不允许置换token
        if (currentTime-createTime<TOKEN_PROTECT_TIME*1000) {
            throw new TokenFailedException("保护期内，不允许置换");
        }

        //3.进行置换

        //3.1生成新token
        String userJson = redisAPI.get(token);
        String   newToken = this.generateToken(agent, JSON.parseObject(userJson, ItripUser.class));

        //3.2.旧token设置过期（2分钟后）
        redisAPI.set(token,2*60,userJson);
        //3.3 缓存新token
        redisAPI.set(newToken,TOKEN_EXPIRE,userJson);
        //返回新token
        return newToken;

    }
}
