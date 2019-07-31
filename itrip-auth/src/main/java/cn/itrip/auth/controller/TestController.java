package cn.itrip.auth.controller;

import cn.itrip.beans.dto.Dto;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.ErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api
@Controller
@RequestMapping("/api")
public class TestController {
    @ApiOperation(value = "登录验证",notes = "响应数据success：登录成功，fail:登录失败",
           httpMethod = "POST",protocols = "http"
    )
    @RequestMapping(value = "/testlogin",method = RequestMethod.POST)
    public @ResponseBody Dto testlogin(
            @ApiParam(name = "name",value = "用户名",defaultValue = "hello",required = true)
            @RequestParam(required = true) String name,
            @ApiParam(name = "password",value = "密*码",defaultValue = "777",required = true)
            @RequestParam(required = true)String password){
        if("tom".equals(name)&&"123".equals(password)){
            System.out.println("登录成功");
            return DtoUtil.returnSuccess("登录成功");
        }else{
            System.out.println("登录失败");
            return DtoUtil.returnFail("失败", ErrorCode.AUTH_ACTIVATE_FAILED);
        }
    }
}
