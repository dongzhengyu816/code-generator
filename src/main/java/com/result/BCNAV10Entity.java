package com.result;
import com.result.DFType;
import lombok.Data;
@Data
public class BCNAV10Entity{
    /***
     * 页面类型
     */
    private int pageId;

    /***
     * 卫星健康状态
     */
    private int hs;

    /***
     * 卫星完好性状态采用电文完好性标识
     */
    private long dif;

}