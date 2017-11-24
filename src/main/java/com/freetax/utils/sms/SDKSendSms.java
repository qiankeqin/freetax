package com.freetax.utils.sms;

import com.aliyuncs.exceptions.ClientException;
import com.freetax.utils.propertiesLoader.PropertiesLoader;

/**
 * 发送短信接口
 * @Author shuxf
 * @Date 2017/11/10 17:39
 */
public class SDKSendSms {

    public static Boolean sendSMS(String mobile, String verifyCode, String templateCode) throws ClientException {

        String gateWay = PropertiesLoader.getValue("sms_gateway");

        if("alidayu".equals(gateWay)){
            return SDKSendTaoBaoSMS.sendSMS(mobile,verifyCode,templateCode);//暂时不确定是否集成阿里大鱼--------shuxf 临时屏蔽
        } else if("yuntongxun".equals(gateWay)){
//            return SDKSendTemplateSMS.sendSMS(mobile,params,templateCode);
        }

        return false;
    }
}
