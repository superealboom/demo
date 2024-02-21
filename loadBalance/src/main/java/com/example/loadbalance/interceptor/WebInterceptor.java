package com.example.loadbalance.interceptor;

import com.example.loadbalance.common.Constant;
import com.example.loadbalance.entity.User;
import com.example.loadbalance.util.IPKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 拦截器处理请求，校验http中的cookie
 * @author: tianci
 * @date: 2022/8/17 15:19
 */
@Slf4j
@Component
public class WebInterceptor implements HandlerInterceptor {

    /**
     * @description: cookie模式过滤器
     * @param: [request, response, handler]
     * @return: boolean
     * @author: tianci
     * @date: 2022/8/24 10:37
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();

        log.info("UserAgent: {}", request.getHeader(Constant.USER_AGENT));
        log.info("用户访问地址: {}, 来路地址: {}, 方法: {}", uri, IPKit.getIpAddrByRequest(request), request.getMethod());

        // cookie方式
        String cookieUsername = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(Constant.USER_COOKIE, cookie.getName())) {
                    cookieUsername = cookie.getValue();
                    break;
                }
            }
        }

        // session方式
        String sessionUsername = null;
        User user = (User) request.getSession().getAttribute(Constant.USER_SESSION);
        if (user != null) {
            sessionUsername = user.getUsername();
        }

        if (!StringUtils.equals("/login", uri)) {
            int falseNumber = 0;
            if (!StringUtils.equals("/cookie/login", uri) && null == cookieUsername) {
                falseNumber++;
            }
            if (!StringUtils.equals("/session/login", uri) && null == sessionUsername) {
                falseNumber++;
            }
            if (falseNumber == 2) {
                response.sendRedirect(request.getContextPath() + "/login");
                return false;
            }
        }
        return true;
    }
}
