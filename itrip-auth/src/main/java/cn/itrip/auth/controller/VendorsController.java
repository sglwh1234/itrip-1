package cn.itrip.auth.controller;

import cn.itrip.auth.service.TokenService;
import cn.itrip.auth.service.UserService;
import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.beans.vo.ItripWechatTokenVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.UrlUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/vendors")
public class VendorsController {
    String appId="wxbdc5610cc59c1631";
    @Resource
    private UserService userService;
    @Resource
    private TokenService tokenService;
    @RequestMapping("/wechat/login")
    public void wechatLogin(HttpServletResponse response) throws IOException {

        String redirectUri="https://localhost:8082/vendors/wechat/callback";
        String url="https://open.weixin.qq.com/connect/qrconnect?" +
                "appid=" +appId+
                "&redirect_uri=" + URLEncoder.encode(redirectUri,"UTF-8")+
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=STATE#wechat_redirect";
        response.sendRedirect(url);
    }
    //https://localhost:8082/vendors/wechat/callback?
    // code=CODE&
    // state=3d6be0a4035d839573b04816624a415e
    @RequestMapping(value = "/wechat/callback")
    public @ResponseBody Dto wechatCallback(String code, String state, HttpServletRequest request){
        //code的有效期10分钟
        //携带code码向微信平台发送请求获取access_token数据，（同时生成本站token）最后把两个token返回客户端
        //携带code发送请求，返回一个json数据
        String secret="";
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" +appId+
                "&secret=" +secret+
                "&code=" +code+
                "&grant_type=authorization_code";
        //发送请求获取token数据
        String jsonStr = UrlUtils.loadURL(url);
        Map<String,String> map = JSON.parseObject(jsonStr, Map.class);
        //判断是否授权
        if(!map.containsKey("access_token")){//用户未授权
            return DtoUtil.returnSuccess("用户取消了授权");
        }

        //判断是否第一次使用微信登录
        String token=null;
        try {
            ItripUser user=userService.findUserByUserCode(map.get("openid"));
            if(user==null){
               //第一次登录，在本站数据库创建用户
               user=new ItripUser();
               user.setActivated(1);
               user.setCreationDate(new Date());
               user.setUserCode(map.get("openid"));
               user.setUserType(1);
               userService.vendorsCreateItripUser(user);
           }
            //创建本站token
            token = tokenService.generateToken(request.getHeader("user-agent"), user);
            //缓存token
            tokenService.saveToken(token,user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取微信token即access_token
        String wechatToken = map.get("access_token");
        //把两个token封装vo返回客户端
        long getTime=System.currentTimeMillis();
        long expTime=getTime+tokenService.TOKEN_EXPIRE*1000;
        ItripWechatTokenVO wechatTokenVO = new ItripWechatTokenVO(token,expTime,getTime);
        wechatTokenVO.setAccessToken(wechatToken);
        wechatTokenVO.setOpenid(map.get("openid"));
        wechatTokenVO.setExpiresIn(map.get("expires_in"));
        wechatTokenVO.setRefreshToken(map.get("refresh_token"));

        return  DtoUtil.returnDataSuccess(wechatToken);

    }
    @RequestMapping(value = "/wechat/user/info")
    public @ResponseBody Dto wechatUserInfo(String accessToken,String openId){
        String url="https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=" +accessToken+
                "&openid="+openId;
        String userJson = UrlUtils.loadURL(url);
        Map<String,String> userInfo = JSON.parseObject(userJson, Map.class);

        return DtoUtil.returnDataSuccess(userInfo);
    }

    public Dto wechatReToken(HttpServletRequest request){
        String token=request.getHeader("token");
        String reToken=request.getHeader("refreshtoken");
        String url="https://api.weixin.qq.com/sns/oauth2/refresh_token?" +
                "appid="+appId+
                "&grant_type=refresh_token" +
                "&refresh_token="+reToken;
        String newToken=null;
        try {//置换本站token
            newToken = tokenService.reloadToken(request.getHeader("user-agent"), token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //刷新微信token
        String tokenJson = UrlUtils.loadURL(url);
        Map <String,String> map = JSON.parseObject(tokenJson, Map.class);
        //把两个token封装vo返回客户端
        long getTime=System.currentTimeMillis();
        long expTime=getTime+tokenService.TOKEN_EXPIRE*1000;
        ItripWechatTokenVO wechatTokenVO = new ItripWechatTokenVO(newToken,expTime,getTime);
        wechatTokenVO.setAccessToken(map.get("access_token"));
        wechatTokenVO.setOpenid(map.get("openid"));
        wechatTokenVO.setExpiresIn(map.get("expires_in"));
        wechatTokenVO.setRefreshToken(map.get("refresh_token"));

        return DtoUtil.returnDataSuccess(wechatTokenVO);
    }
}
