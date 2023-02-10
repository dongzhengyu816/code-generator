package xdpp.entity;

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
    /***
     * 比例因子的底数
     */
    private double baseNumber;

    /***
     * 比例因子的指数
     */
    private double indexNumber;

    /***
     * 是否存在范围
     */
    private boolean existRange;

    /**
     * 范围最小值
     */
    private double rangeMinNumber;

    /***
     * 范围最大值
     */
    private double rangeMaxNumber;
}
