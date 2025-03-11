package cn.afuo.javabasic.serializable;


import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * 序列化：对象转化成字节，反序列化：字节转化成对象。序列化的核心点就是字节流中所保存的对象状态及描述信息
 * 只在本地JVM里运行下Java实例, 这个时候是不需要什么序列化和反序列化
 * 将内存中的对象持久化到磁盘/数据库中时, 与浏览器进行交互时, 实现RPC时, 就需要序列化和反序列化
 * @author: tianci
 * @date: 2024/7/1 17:47
 */
@Data
public class SerializableEntity implements Serializable {


    /**
     * 不显示指定serialVersionUID，一旦类被修改了, 那旧对象反序列化就会报错
     */
    private static final long serialVersionUID = 1L;

    private String name;

    private Integer age;

}
