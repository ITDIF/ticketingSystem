package com.jie.service.Impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MailService {

    // JavaMailSender 在Mail 自动配置类 MailSenderAutoConfiguration 中已经导入，这里直接注入使用即可
    @Resource
    JavaMailSender javaMailSender;

    //方法5个参数分别表示：邮件发送者、收件人、抄送人、邮件主题以及邮件内容
    public void sendSimpleMail(String from, String to, String cc, String subject, String content) {
        // 简单邮件直接构建一个 SimpleMailMessage 对象进行配置并发送即可
        SimpleMailMessage simpMsg = new SimpleMailMessage();
        simpMsg.setFrom(from);
        simpMsg.setTo(to);
        simpMsg.setCc(cc);
        simpMsg.setSubject(subject);
        simpMsg.setText(content);
        javaMailSender.send(simpMsg);
    }
    public void sendTextEmail(String text, String to, String subject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        simpleMailMessage.setFrom("2084220263@QQ.com");
        simpleMailMessage.setTo(to);

        javaMailSender.send(simpleMailMessage);
    }
    public void ticketSuccessInform(String toPeo, String start, String end, String time) {
        if(toPeo == null || toPeo.indexOf("@qq.com")==-1) {
            return;
        }
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("出票成功");
        simpleMailMessage.setText("您已成功购买"+time+"从"+start+"到"+end+"的车票");
        simpleMailMessage.setFrom("2084220263@QQ.com");
        simpleMailMessage.setTo(toPeo);

        javaMailSender.send(simpleMailMessage);
    }
    public void candidateSuccessInform(String toPeo, String start, String end, String time) {
        if(toPeo == null || toPeo.indexOf("@qq.com")==-1) {
            return;
        }
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("支付成功");
        simpleMailMessage.setText("您已成功下单"+time+"从"+start+"到"+end+"的候补车票，" +
                "系统有票时将优先为您分配车票，请耐心等待。若在截止兑换时间还未兑现成功，将自动返还票款至付款账号。");
        simpleMailMessage.setFrom("2084220263@QQ.com");
        simpleMailMessage.setTo(toPeo);

        javaMailSender.send(simpleMailMessage);
    }
    public void waitingSuccess(String toPeo, String start, String end, String time) {
        if(toPeo == null || toPeo.indexOf("@qq.com")==-1) {
            return;
        }
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("订单兑现成功");
        simpleMailMessage.setText("您已成功候补"+time+"从"+start+"到"+end+"的车票，" +
                "请注意发车时间。");
        simpleMailMessage.setFrom("2084220263@QQ.com");
        simpleMailMessage.setTo(toPeo);
        javaMailSender.send(simpleMailMessage);
    }
    public int code(String toPeo, int code) {
        if(toPeo == null || toPeo.indexOf("@qq.com")==-1) {
            return 0;
        }
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(String.valueOf(code));
        simpleMailMessage.setText("您的验证码是："+code);
        simpleMailMessage.setFrom("2084220263@QQ.com");
        simpleMailMessage.setTo(toPeo);
        try {
            javaMailSender.send(simpleMailMessage);
        }catch (Exception e){
            return -1;
        }
        return 1;
    }

}
