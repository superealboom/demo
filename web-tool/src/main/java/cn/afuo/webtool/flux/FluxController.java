package cn.afuo.webtool.flux;


import cn.afuo.webtool.util.DateUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Controller
@RequestMapping("flux")
public class FluxController {

    /**
     * flux页面
     */
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        // 设置跳转的视图
        modelAndView.setViewName("modules/flux/index");
        return modelAndView;
    }


    /**
     * flux实时每隔5秒获取时间
     */
    @ResponseBody
    @GetMapping(value = "/realtime", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> realtime() {
        Mono<String> immediateValue = Mono.just(DateUtil.now());
        Flux<String> intervalValues = Flux.interval(Duration.ofSeconds(1))
                .map(tick -> DateUtil.now());
        // 将立即发出的Mono与按间隔发出的Flux合并
        return immediateValue.concatWith(intervalValues);
    }

}
