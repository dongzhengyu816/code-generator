package com.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: template-test
 * @description: 报文构造
 * @author: Zhengyu Dong
 * @create: 2022-12-16 19:45
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "message")
@PropertySource("classpath:/message.properties")
public class MessageConstruct {
    /***
     * 数据组的列表
     */
    private List<FieldGroup> list = new ArrayList<>();
}
