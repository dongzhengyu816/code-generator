package xdpp.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
public class MessageConstruct {
    /***
     * 包路径
     */
    private String packagePath;
    /***
     * 类名，驼峰命名,首字母大写
     */
    private String entityName;
    /***
     * 是否是高字节先读
     */

    private boolean HighByteFirst;
    /***
     * 数据组的列表
     */
    private List<FieldGroup> list = new ArrayList<>();
}
