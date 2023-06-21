package com.jie.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.jie.config.AliyunSendMsgConfig;
import com.jie.service.SendMsgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author jie
 */
@Service
public class SendMsgServiceImpl implements SendMsgService {
    @Resource
    AliyunSendMsgConfig aliyunSendMsgConfig;
    /**
     * @param phoneNumbers 手机
     * @param param   发送 code
     * @return
     */
    @Override
    public Boolean sendMsg(String phoneNumbers, Map<String, Integer> param) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-qingdao", aliyunSendMsgConfig.getAccessKeyId(), aliyunSendMsgConfig.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        //接收短信的手机号码
        request.putQueryParameter("PhoneNumbers",phoneNumbers);
        //短信签名名称
        request.putQueryParameter("SignName",aliyunSendMsgConfig.getSignName());
        //短信模板ID
        request.putQueryParameter("TemplateCode",aliyunSendMsgConfig.getTemplateCode());
        //短信模板变量对应的实际值
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));
        try {
            CommonResponse response = client.getCommonResponse(request);
            // 是否发送成功
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}

