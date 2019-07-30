package cn.itrip.auth.service;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import common.SystemConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Set;
@Service("smsService")
public class SmsServiceImpl implements SmsService {
    @Resource
    private SystemConfig systemConfig;
    @Override
    public HashMap<String, Object> sendTemplateSMS(String phone, String templateId, String[] datas) throws  Exception {
        HashMap<String, Object> result = null;

        //初始化SDK
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();

        restAPI.init(systemConfig.getSmsServerIP(), systemConfig.getSmsServerPort());

        restAPI.setAccount(systemConfig.getSmsAccountSid(), systemConfig.getSmsAuthToken());

        restAPI.setAppId(systemConfig.getSmsAppID());

        result = restAPI.sendTemplateSMS(phone,templateId ,datas);

        System.out.println("SDKTestGetSubAccounts result=" + result);
        if("000000".equals(result.get("statusCode"))){
            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
        }

        return result;
    }
}
