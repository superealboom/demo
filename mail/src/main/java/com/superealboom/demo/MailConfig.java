package com.superealboom.demo;

import lombok.Data;

/**
 * @description: TODO
 * @author: tianci
 * @date: 2022/8/4 11:04
 */
@Data
public class MailConfig {

    private String smtpHost;
    private String smtpPort;
    private String smtpAddr;
    private String userName;
    private String password;
    private String smtpAuth;

}
