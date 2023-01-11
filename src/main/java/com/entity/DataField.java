package com.entity;

import lombok.Data;

/**
 * @program: template-test
 * @description: 数据属性
 * @author: Zhengyu Dong
 * @create: 2022-12-16 19:36
 **/
@Data
public class DataField {
    /***
     * 注释
     */
    private String notes;
    /***
     * 字段名字
     */
    private String fieldName;
    /***
     * 字段长度
     */
    private int bits;
    /***
     * 字段类型，Bit，UInt，Int，IntS
     */
    private String fieldType;
}
