package cn.itrip.auth.controller;

import cn.itrip.auth.service.SmsService;
import cn.itrip.auth.service.UserService;
import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.beans.vo.userinfo.ItripUserVO;
import com.fasterxml.jackson.databind.util.BeanUtil;
import common.DtoUtil;
import common.EmptyUtils;
import common.ErrorCode;
import common.MD5;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/api")
public class UserController {
    @Resource(name = "smsService")
    private SmsService smsService;
    @Resource
    private UserService userService;
    @RequestMapping(value = "/registerbyphone",method = RequestMethod.POST)
    public@ResponseBody Dto registerbyphone(ItripUserVO itripUserVo){
        //验证手机号
        String userCode = itripUserVo.getUserCode();
        System.out.println("usercode="+userCode);
        if (!validPhone(userCode)) {
            return  DtoUtil.returnFail("请使用正确的手机号注册", ErrorCode.AUTH_ILLEGAL_USERCODE);
        }
        //把vo转换成pojo
        ItripUser itripUser = new ItripUser();
        itripUser.setUserCode(userCode);
        itripUser.setUserPassword(itripUserVo.getUserPassword());
        itripUser.setUserType(0);
       //验证用户是否注册过
        try {
            if(EmptyUtils.isEmpty(userService.findUserByUserCode(userCode))){
                //没有注册过
                itripUser.setUserPassword(MD5.getMd5(userCode,32));
                int i=userService.createItripUser(itripUser);
                if(i>0) {
                    return DtoUtil.returnSuccess();
                }else{
                    return DtoUtil.returnFail("注册异常",ErrorCode.AUTH_UNKNOWN);
                }
            }else{
                return DtoUtil.returnFail("此手机号已注册",ErrorCode.AUTH_USER_ALREADY_EXISTS);
            }
        }catch (Exception e){
            return DtoUtil.returnFail(e.getMessage(),ErrorCode.AUTH_UNKNOWN);
        }
    }
    /**
     * 验证是否合法的手机号
     * @param phone
     * @return
     */
    private boolean validPhone(String phone) {
        String regex="^1[3578]{1}\\d{9}$";
        return Pattern.compile(regex).matcher(phone).find();
    }
    //验证手机短信验证码
    @RequestMapping(value = "/validatephone",method = RequestMethod.PUT)
    public @ResponseBody Dto validCode(@RequestParam(required = true) String user, @RequestParam(required = true)  String code){

        return  null;
    }


}
