package cn.itrip.auth.controller;

import cn.itrip.auth.service.TokenService;
import cn.itrip.auth.service.UserService;
import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.beans.vo.ItripTokenVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.ErrorCode;
import cn.itrip.common.MD5;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@Controller
@RequestMapping("/api")
public class LoginController {
    @Resource
    private UserService userService;
    @Resource
    private TokenService tokenService;
    @RequestMapping(value = "/dologin",method = RequestMethod.POST,produces = "application/json")
    public @ResponseBody Dto doLogin(@RequestParam(required = true) String name,
                @RequestParam(required = true) String password,
                HttpServletRequest request){
        if(name!=null&&password!=null) {
            try {
                //登录验证
                ItripUser user = userService.login(name, MD5.getMd5(password, 32));
                if (user != null) {//用户名密码验证通过
                    //生成token
                    String agent = request.getHeader("user-agent");
                    String token = tokenService.generateToken(agent, user);
                    //保存token到redis
                    tokenService.saveToken(token,user);
                    //向客户端发送token
                    long genTime= Calendar.getInstance().getTimeInMillis();//token生成时间即当前时间
                    long expTime=genTime+tokenService.TOKEN_EXPIRE*1000;//token有效期，即当前时间+2个小时有效期
                    return  DtoUtil.returnDataSuccess(new ItripTokenVO(token,expTime,genTime));
                }

            } catch (Exception e) {
                return DtoUtil.returnFail(e.getMessage(), ErrorCode.AUTH_PARAMETER_ERROR);
           }
        }
        return  DtoUtil.returnFail("登录失败",ErrorCode.AUTH_UNKNOWN);

    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET,headers = "token")
    public @ResponseBody Dto doLogout(HttpServletRequest request){
        String agent = request.getHeader("user-agent");
        String token = request.getHeader("token");
        try {
            if (tokenService.validate(agent, token)) {
                tokenService.delToken(token);
                return DtoUtil.returnSuccess("退出成功");
            }else{
                return  DtoUtil.returnSuccess("未登录状态");
            }
        } catch (Exception e) {
           return  DtoUtil.returnFail("退出失败",ErrorCode.AUTH_UNKNOWN);
        }

    }
}
