package com.superealboom.demo.controller;

import com.superealboom.demo.entity.UserInfo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * @description: cache
 * @author: tianci
 * @date: 2022/4/24 16:53
 */
@RestController
@RequestMapping("/ehcache")
public class UserController {

    private static final String CACHE_NAME = "userCache";


    @GetMapping("/userInfo/{userId}")
    @Cacheable(value = CACHE_NAME, key = "#userId")
    public UserInfo getUserById(@PathVariable long userId) {
        System.out.println("getUserById");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        return userInfo;
    }

    @PostMapping("/userInfo")
    @CachePut(value = CACHE_NAME, key = "#userInfo.userId")
    public UserInfo insertUser(@RequestBody UserInfo userInfo) {
        System.out.println("insertUser");
        return userInfo;
    }

    @PutMapping("/userInfo")
    @CachePut(value = CACHE_NAME, key = "#userInfo.userId")
    public UserInfo updateUser(@RequestBody UserInfo userInfo) {
        System.out.println("updateUser");
        return userInfo;
    }

    @DeleteMapping("/userInfo/{userId}")
    @CacheEvict(value = CACHE_NAME, key = "#userId")
    public long deleteUser(@PathVariable long userId) {
        System.out.println("deleteUser");
        return userId;
    }


    // spring-cache 中 cache转map
    // public static Map<String, Object> cacheToMap(Cache cache) {
    //     Object obj = cache.getNativeCache();
    //     Map<String, Object> map = new HashMap<>();
    //     Field[] fields = obj.getClass().getDeclaredFields();
    //     try {
    //         for (Field field : fields) {
    //             field.setAccessible(true);
    //             map.put(field.getName(), field.get(obj));
    //         }
    //     } catch (Exception e) {
    //         log.error("ObjToMap exp", e);
    //         return null;
    //     }
    //     // 获取Cache.map中的cache
    //     return (Map<String, Object>) map.get("cache");
    // }

}
