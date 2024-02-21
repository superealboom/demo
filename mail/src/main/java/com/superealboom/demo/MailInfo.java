package com.superealboom.demo;

import lombok.Data;

@Data
public class MailInfo {

    private String mailId;
    //收件人
    private String mailTo;
    //抄送人
    private String mailCs;
    //密送人
    private String mailMs;
    //主题
    private String subject;
    //内容
    private String content;
}
