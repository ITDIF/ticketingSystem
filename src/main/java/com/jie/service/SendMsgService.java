package com.jie.service;

import java.util.Map;

/**
 * @author jie
 */
public interface SendMsgService {
    /**
     * 发送手机验证码
     * @param phoneNumbers 手机
     * @param param 发送 code
     * @return
     */
    public Boolean sendMsg(String  phoneNumbers, Map<String ,Integer> param);
}

