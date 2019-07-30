package cn.itrip.auth.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

public interface SmsService {
    public HashMap<String, Object> sendTemplateSMS(String phone, String templateId, String[] datas) throws Exception;

}