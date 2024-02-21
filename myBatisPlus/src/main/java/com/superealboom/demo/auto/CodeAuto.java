package com.superealboom.demo.auto;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.SQLException;
import java.util.Collections;

/**
 * @description: 代码生成
 * @author: tianci
 * @date: 2022/10/18 15:56
 */
public class CodeAuto {
    /**
     * 执行 run
     */
    public static void main(String[] args) throws SQLException {
        FastAutoGenerator.create("jdbc:mysql://192.168.99.107:3306/blog?autoReconnect=true&autoReconnectForPools=true&useSSL=false&rewriteBatchedStatements=true&allowMultiQueries=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai", "root", "root")
                .globalConfig(builder -> {
                    builder.author("tianci") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("/opt/mybatisPlusAuto"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("cn.afuo") // 设置父包名
                            .moduleName("blog") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/opt/mybatisPlusAuto")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("blog_menu,blog_info") // 设置需要生成的表名
                    ; // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}

