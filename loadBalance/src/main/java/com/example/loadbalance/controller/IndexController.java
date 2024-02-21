package com.example.loadbalance.controller;

import com.example.loadbalance.common.Constant;
import com.example.loadbalance.common.R;
import com.example.loadbalance.entity.Person;
import com.example.loadbalance.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description: 查看首页数据
 * @author: tianci
 * @date: 2022/8/17 15:25
 */
@RestController
public class IndexController {


    private final Person person;

    public IndexController(Person person) {
        this.person = person;
    }

    /**
     * @description: 登陆成功 - 从cookie中拿出用户名
     * @param: [user]
     * @return: com.example.loadbalance.common.Result
     * @author: tianci
     * @date: 2022/8/17 15:24
     */
    @GetMapping(value = "/cookie/index")
    public R cookieLogin(HttpServletRequest request, HttpServletResponse response) {
        String username = "";
        Map<String, User> userMap =  person.getUserList().stream().collect(Collectors.toMap(User::getUsername, Function.identity()));
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if (StringUtils.equals(cookie.getName(), Constant.USER_COOKIE)) {
                username = cookie.getValue();
            }
        }
        User user = (User) request.getSession().getAttribute(Constant.USER_SESSION);
        return R.ok().data("cookie标题", "欢迎 " + username + " 进入系统").data("用户信息", userMap.get(username));
    }

    /**
     * @description:  登录成功 - 从session中拿出用户信息
     * @param: [request, response]
     * @return: com.example.loadbalance.common.R
     * @author: tianci
     * @date: 2022/8/24 11:00
     */
    @GetMapping(value = "/session/index")
    public R sessionLogin(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(Constant.USER_SESSION);
        return R.ok().data("session标题", "欢迎 " + user.getUsername() + " 进入系统").data("用户信息", user);
    }
}
