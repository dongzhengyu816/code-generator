package xdpp.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: template-test
 * @description: 属性组,报文中可能存在内部类,并且每个属性的长度可能根据前一个数据段得出
 * @author: Zhengyu Dong
 * @create: 2022-12-17 10:16
 **/
@Data
public class FieldGroup {
    /***
     * list长度为1时说明该段为单字段，大于1时说明有内部类
     */
    List<DataField> fieldList = new ArrayList<>();
    /***
     * 该group的长度,改长度可能根据前面某一个字段的信息来决定
     */
    String length;

    String innerClassName;
}
