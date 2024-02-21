package com.superealboom.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.superealboom.demo.entity.File;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: redis操作
 * @author: tianci
 * @date: 2022/7/22 14:32
 */
@RestController
public class FileController {

    @Autowired
    private RedisTemplate redisTemplate;


    @PostMapping("/setRedis")
    public String setRedis(@RequestBody Map<String, String> map) {
        for (String key : map.keySet()) {
            redisTemplate.opsForValue().set(key, map.get(key));
        }
        return "成功";
    }

    @GetMapping("/getRedis")
    public Map<String,String> getRedis(@RequestParam("keys") List<String> keys) {
        Map<String, String> result = new HashMap<>();
        for (String key :keys) {
            String value = (String)redisTemplate.opsForValue().get(key);
            result.put(key, value);
        }
        return result;
    }


    @PostMapping("/pushList")
    public List<Object> pushList(@RequestBody File file) {
        String responsePath = file.getResponsePath();
        String localPath = file.getLocalPath();
        String reportRecord = file.getReportRecord();
        String protocol = file.getProtocol();
        String ip = file.getIp();
        String port = file.getPort();
        String username = file.getUsername();
        String password = file.getPassword();
        String comcode = file.getComcode();
        String reportListId = file.getReportListId();
        String nowTime = file.getNowTime();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("RESPONSEPATH", responsePath);
        jsonObject.put("LOCALPATH", localPath);
        jsonObject.put("REPORTRECORD", reportRecord);
        jsonObject.put("PROTOCOL", protocol);
        jsonObject.put("IP", ip);
        jsonObject.put("PORT", port);
        jsonObject.put("USERNAME", username);
        jsonObject.put("PASSWORD", password);
        jsonObject.put("COMCODE", comcode);
        jsonObject.put("REPORTLISTID",reportListId);
        jsonObject.put("DATE", nowTime);
        System.out.println("redisJson:" + jsonObject);

        redisTemplate.opsForList().rightPush("responseFile", jsonObject.toJSONString());
        List<Object> result = redisTemplate.opsForList().range("responseFile",0,-1);
        return result;
    }


    @DeleteMapping("/removeListById")
    public List<Object> removeListById(@RequestParam("id") String id) {
        List<Object> responseFileList = redisTemplate.opsForList().range("responseFile",0,-1);
        if (responseFileList != null && responseFileList.size()>0){
            for (Object responseFileObject : responseFileList) {
                JSONObject jsonObject = JSONObject.parseObject(responseFileObject.toString());
                String reportListId = jsonObject.getString("REPORTLISTID");
                if (StringUtils.equals(id, reportListId)) {
                    redisTemplate.opsForList().remove("responseFile", 1, responseFileObject);
                    break;
                }
            }
        }
        return redisTemplate.opsForList().range("responseFile",0,-1);
    }


    @GetMapping("/getList")
    public List<Object> getList(@RequestParam("key") String key) {
        return redisTemplate.opsForList().range(key,0,-1);
    }

}
