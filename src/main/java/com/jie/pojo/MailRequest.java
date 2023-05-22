package com.jie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author jie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailRequest implements Serializable {
    /**
     * 接收人
     */
    private String sendTo;

    /**
     *  邮件主题
     */
    private String subject;

    /**
     *  邮件内容
     */
    private String text;

    /**
     *  附件路径
     */
    private String filePath;

}


