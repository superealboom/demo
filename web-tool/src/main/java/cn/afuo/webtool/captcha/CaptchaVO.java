package cn.afuo.webtool.captcha;

import lombok.Data;

@Data
public class CaptchaVO {

    private String captchaId;

    private String captchaCode;

    private String captchaImage;
}
