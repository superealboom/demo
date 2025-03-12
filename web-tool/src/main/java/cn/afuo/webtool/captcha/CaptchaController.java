package cn.afuo.webtool.captcha;


import cn.afuo.webtool.constant.RedisConstants;
import cn.afuo.webtool.domain.Result;
import cn.afuo.webtool.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 验证码
 */
@Slf4j
@RestController
@RequestMapping("captcha")
public class CaptchaController {

    private final CaptchaService captchaService;

    private final RedisUtil redisUtil;

    public CaptchaController(RedisUtil redisUtil, CaptchaService captchaService) {
        this.redisUtil = redisUtil;
        this.captchaService = captchaService;
    }

    /**
     * 验证码页面
     */
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("modules/captcha/index");
        CaptchaVO captchaVO = captchaService.getCaptcha();
        modelAndView.addObject("captchaVO", captchaVO);
        return modelAndView;
    }

    /**
     * 获取验证码
     */
    @GetMapping("/getCaptcha")
    public Result<?> getCaptcha() {
        CaptchaVO captchaVO = captchaService.getCaptcha();
        return Result.success(captchaVO);
    }

    /**
     * 校验验证码
     */
    @PostMapping("/checkCaptcha")
    public Result<?> checkCaptcha(CaptchaVO captchaVO) {
        String captchaCode = (String) redisUtil.get(RedisConstants.CAPTCHA_PREFIX + captchaVO.getCaptchaId());
        if (captchaVO.getCaptchaCode().equals(captchaCode)) {
            return Result.success().message("验证成功");
        }
        return Result.fail().message("验证失败");
    }
}
