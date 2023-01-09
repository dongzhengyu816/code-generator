package com.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @program: template-test
 * @description: 生成代码的包的类路径
 * @author: Zhengyu Dong
 * @create: 2022-12-16 21:06
 **/
@Data
@Configuration
@PropertySource("classpath:/config.properties")
@ConfigurationProperties
public class Config {
    private String packagePath;
    /***
     * 驼峰命名,首字母大写
     */
    private String entityName;

    private int HighByteFirst;
}
