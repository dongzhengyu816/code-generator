package xdpp.protocol.L2.entity;
import lombok.Data;

@Data
public class L2SubFrame2page4Entity{
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