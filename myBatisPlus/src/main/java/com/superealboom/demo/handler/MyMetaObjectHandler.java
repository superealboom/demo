package com.superealboom.demo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: 两种方式
 * @author: tianci
 * @date: 2022/4/20 16:03
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // this.setFieldValByName("createTime",new Date(),metaObject);
        // this.setFieldValByName("updateTime",new Date(),metaObject);
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "email", String.class, "auto@111.com");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // this.setFieldValByName("updateTime",new Date(),metaObject);
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }
}
