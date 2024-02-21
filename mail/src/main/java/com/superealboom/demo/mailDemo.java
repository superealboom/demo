package com.superealboom.demo;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

/**
 * @description: 邮件
 * @author: tianci
 * @date: 2022/8/4 10:44
 */
public class mailDemo {


    public static void main(String[] args) throws Exception {


        MailInfo mailInfo = new MailInfo();
        mailInfo.setMailTo("ci.tian@newtouch.com");
        mailInfo.setContent("见信如晤");
        mailInfo.setSubject("发送信");

        MailConfig mailConfig = new MailConfig();
        mailConfig.setSmtpHost("smtp.163.com");
        mailConfig.setSmtpPort("25");
        mailConfig.setSmtpAddr("superealboom@163.com");
        mailConfig.setUserName("superealboom");
        mailConfig.setPassword("GNUEYGKVIXULJXDT");
        mailConfig.setSmtpAuth("true");

        send(mailInfo, mailConfig);
    }


    public static void send(MailInfo mailInfo, MailConfig mailConfig) throws Exception {
        String to=mailInfo.getMailTo();
        String cs=mailInfo.getMailCs();
        String ms=mailInfo.getMailMs();
        String subject=mailInfo.getSubject();
        String content=mailInfo.getContent();
        String[] fileList=null;
        //1、建立会话，将环境信息放进Session中。
        Properties p = new Properties();
        p.put("mail.smtp.auth", true);//身份验证
        p.put("mail.transport.protocol", "smtp");//发送邮件协议名
        p.put("mail.smtp.host", mailConfig.getSmtpHost());//邮件服务器主机名
        p.put("mail.smtp.port", mailConfig.getSmtpPort());//邮件服务器端口
        Session session = Session.getInstance(p);

        //2、创建邮件对象
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(mailConfig.getSmtpAddr()));//new一个邮件地址，发件人
        Multipart multipart = new MimeMultipart();//邮件正文框(包括附件)
        BodyPart messageBodyPart = new MimeBodyPart();//邮件组成部分
        //发送
        if (to != null) {
            InternetAddress[] iaToList = new InternetAddress().parse(to);
            msg.setRecipients(Message.RecipientType.TO, iaToList); // 收件人
        }
        //抄送
        if (cs != null) {
            InternetAddress[] iaToListcs = new InternetAddress().parse(cs);
            msg.setRecipients(Message.RecipientType.CC, iaToListcs); // 抄送人
        }
        //密送
        if (ms != null) {
            InternetAddress[] iaToListms = new InternetAddress().parse(ms);
            msg.setRecipients(Message.RecipientType.BCC, iaToListms); // 密送人
        }
        msg.setSentDate(new Date()); // 发送日期
        msg.setSubject(subject); // 主题
        msg.setText(content); // 内容
        messageBodyPart.setContent(content, "text/html;charset=gbk");
        multipart.addBodyPart(messageBodyPart);//将body部分放在mult里
        //添加附件
        if (fileList != null) {
            for (int index = 0; index < fileList.length; index++) {
                BodyPart mailArchieve = new MimeBodyPart();
                FileDataSource fds = new FileDataSource(fileList[index]);
                mailArchieve.setDataHandler(new DataHandler(fds));
                mailArchieve.setFileName(MimeUtility.encodeText(fds.getName(), "GBK", "B"));
                multipart.addBodyPart(mailArchieve);
            }
        }
        msg.setContent(multipart);

        //3、邮件服务器进行验证并发送邮件
        Transport tran = session.getTransport("smtp");
        tran.connect(mailConfig.getSmtpHost(), mailConfig.getUserName(), mailConfig.getPassword());
        tran.sendMessage(msg, msg.getAllRecipients()); //发送
        System.out.println("邮件发送成功");
        tran.close();
    }

}
