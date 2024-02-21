package com.example.loadbalance.controller;

import com.example.loadbalance.common.Constant;
import com.example.loadbalance.common.R;
import com.example.loadbalance.entity.Person;
import com.example.loadbalance.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description: 使用登录接口
 * @author: tianci
 * @date: 2022/8/17 15:09
 */
@Controller
public class LoginController {

    private final Person person;

    public LoginController(Person person) {
        this.person = person;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * @description: 登陆成功 - 创建cookie
     * @param: [user]
     * @return: com.example.loadbalance.common.Result
     * @author: tianci
     * @date: 2022/8/17 15:24
     */
    @PostMapping(value = "/cookie/login")
    @ResponseBody
    public R cookieLogin(HttpServletRequest request, HttpServletResponse response, User user) {
        Map<String, User> userMap =  person.getUserList().stream().collect(Collectors.toMap(User::getUsername, Function.identity()));
        if (userMap.containsKey(user.getUsername())) {
            if (StringUtils.equals(userMap.get(user.getUsername()).getPassword(), user.getPassword())) {
                // 验证成功
                // 创建cookie
                Cookie userCookie = new Cookie("user_cookie",user.getUsername());
                /*
                 * 中间这步可以设置cookie的存活时间
                 * userCookie.setMaxAge()
                 * 正数，表示在指定的秒数后过期
                 * 负数，表示浏览器一关，Cookie 就会被删除（默认值是-1） ,不写就是默认值
                 *零，表示马上删除 Cookie
                 */
                response.addCookie(userCookie);
            } else {
                // 密码不正确
                return R.error().message("密码不正确");
            }
        } else {
            // 用户不存在
            return R.error().message("用户不存在");
        }
        return R.ok().data("user", userMap.get(user.getUsername()));
    }


    /**
     * @description:  登录成功 - 创建session
     * @param: [request, response, user]
     * @return: com.example.loadbalance.common.R
     * @author: tianci
     * @date: 2022/8/24 10:59
     */
    @PostMapping(value = "/session/login")
    @ResponseBody
    public R sessionLogin(HttpServletRequest request, HttpServletResponse response, User user) {
        Map<String, User> userMap =  person.getUserList().stream().collect(Collectors.toMap(User::getUsername, Function.identity()));
        if (userMap.containsKey(user.getUsername())) {
            if (StringUtils.equals(userMap.get(user.getUsername()).getPassword(), user.getPassword())) {
                // 验证成功
                request.getSession().setAttribute(Constant.USER_SESSION, user);
            } else {
                // 密码不正确
                return R.error().message("密码不正确");
            }
        } else {
            // 用户不存在
            return R.error().message("用户不存在");
        }
        return R.ok().data("user", userMap.get(user.getUsername()));
    }

}
