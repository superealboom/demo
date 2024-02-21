package com.superealboom.demo.entity;

import lombok.Data;

/**
 * @description: 文件类
 * @author: tianci
 * @date: 2022/7/22 14:32
 */
@Data
public class File {

    private String responsePath;

    private String localPath;

    private String reportRecord;

    private String protocol;

    private String ip;

    private String port;

    private String username;

    private String password;

    private String comcode;

    private String reportListId;

    private String nowTime;

}
