package xdpp.common.error;

import lombok.Data;

/**
 * @program: dataprocess
 * @description: 自定义异常类
 * @author: Zhengyu Dong
 * @create: 2022-11-21 14:59
 **/
@Data
public class XdppException extends RuntimeException{
    /***
     * 错误码
     */
    protected int errCode;
    /***
     * 错误信息
     */
    protected Object appDataInfo;


    public XdppException(int errCode, Object appDataInfo, String message) {
        super(message);
        this.errCode = errCode;
        this.appDataInfo = appDataInfo;
    }
}
