package cn.itrip.auth.controller;

import cn.itrip.auth.service.TokenService;
import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.vo.ItripTokenVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.ErrorCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@Controller
@RequestMapping("/api")
public class TokenController {
    @Resource
    private TokenService tokenService;

    @RequestMapping(value = "/validatetoken",method = RequestMethod.GET,headers = "token")
    public @ResponseBody Dto validateToken(HttpServletRequest request){
        try {
            boolean result= tokenService.validate(request.getHeader("user-agent"),request.getHeader("token"));
            if (result) {
                return DtoUtil.returnSuccess("认证成功");
            }else {
                return  DtoUtil.returnSuccess("认证失败");
            }
        } catch (Exception e) {
            return  DtoUtil.returnFail(e.getMessage(), ErrorCode.AUTH_UNKNOWN);
        }
    }

    @RequestMapping(value = "/retoken",method = RequestMethod.POST,headers = "token")
    public@ResponseBody Dto reloadToken(HttpServletRequest request){
        String token = request.getHeader("token");
        String agent = request.getHeader("user-agent");
        try {
            String newToken=tokenService.reloadToken(agent,token);
            if (newToken != null) {
                long genTime= Calendar.getInstance().getTimeInMillis();
                long expTime=genTime+tokenService.TOKEN_EXPIRE*1000;
                return DtoUtil.returnDataSuccess(new ItripTokenVO(newToken,expTime,genTime));
            }else{
                return DtoUtil.returnSuccess("置换失败");
            }
        } catch (Exception e) {
            return  DtoUtil.returnFail(e.getMessage(),ErrorCode.AUTH_UNKNOWN);
        }
    }
}
