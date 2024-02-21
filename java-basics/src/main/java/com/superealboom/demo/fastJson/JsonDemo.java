package com.superealboom.demo.fastJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;

/**
 * @description: 测试fastJson
 * @author: tianci
 * @date: 2022/5/10 11:08
 */
public class JsonDemo {


    /**
     * @description: 测试空字符串和空对象
     * @param: []
     * @return: void
     * @author: tianci
     * @date: 2022/5/10 14:44
     */
    @Test
    public void function() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1",1);
        jsonObject.put("2","");
        jsonObject.put("3","null");
        jsonObject.put("4",null);
        System.out.println(JSON.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue));

        JSONObject jsonObj = JSONObject.parseObject(JSON.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue));
        System.out.println(jsonObj.getString("1"));
        System.out.println(jsonObj.getString("2"));
        if (jsonObj.getString("3") == null)
            System.out.println("null对象:" + jsonObj.getString("3"));
        else if (jsonObj.getString("3").equals("null"))
            System.out.println("null字符串:" + jsonObj.getString("3"));
        if (jsonObj.getString("4") == null)
            System.out.println("null对象:" + jsonObj.getString("4"));
    }


    /**
     * @description: 对象包含对象
     * @param: []
     * @return: void
     * @author: tianci
     * @date: 2022/5/11 15:00
     */
    @Test
    public void function2() {
        String oggJsonD="{\"current_ts\":\"2021-12-20T14:32:31.061000\",\"primary_keys\":\"DEPTNO\",\"pos\":\"1134567890100\",\"before\":{\"ADRESS\":\"36.5\",\"DEPTNO\":\"1\",\"DNAME\":\"会计ogg1\"},\"deptcode\":\"tail_code\",\"op_type\":\"D\",\"after\":{},\"op_ts\":\"2018-08-26 10:11:19.355760\",\"table\":\"SCOTT.DEPT\"}";
        String oggJsonI="{\"current_ts\":\"2021-12-20T14:32:31.061000\",\"primary_keys\":\"DEPTNO\",\"pos\":\"1110089652101\",\"before\":{},\"deptcode\":\"tail_code\",\"op_type\":\"I\",\"after\":{\"ADRESS\":\"36.5\",\"DEPTNO\":\"2\",\"DNAME\":\"人事ogg1\"},\"op_ts\":\"2020-08-26 10:11:19.355760\",\"table\":\"SCOTT.DEPT\"}";
        String oggJsonU="{\"current_ts\":\"2021-12-20T14:32:31.061000\",\"primary_keys\":\"DEPTNO\",\"pos\":\"1110089652104\",\"before\":{\"ADRESS\":\"36.5\",\"DEPTNO\":\"2\",\"DNAME\":\"人事ogg1\"},\"deptcode\":\"tail_code\",\"op_type\":\"U\",\"after\":{\"ADRESS\":\"39.2\",\"DEPTNO\":\"2\",\"DNAME\":\"人事ogg1\"},\"op_ts\":\"2020-08-26 10:11:19.355760\",\"table\":\"SCOTT.DEPT\"}";

        JSONObject jsonObj = JSONObject.parseObject(oggJsonD);
        JSONObject before = jsonObj.getJSONObject("before");
        JSONObject after = jsonObj.getJSONObject("after");
        System.out.println(before.size());
        System.out.println(after.size());
    }


    @Test
    public void function3() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1","1");
        revert(jsonObject);
        System.out.println(jsonObject.getString("1"));
    }


    public void revert(JSONObject jsonObject) {
        jsonObject.put("1", "123");
    }

}
