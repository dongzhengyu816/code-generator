package xdpp.protocol.common.error;

import xdpp.common.error.XdppException;

/**
 * @program: protocol-decode
 * @description: 报文属性字段转换成实数后范围错误
 * @author: Zhengyu Dong
 * @create: 2023-02-10 11:51
 **/
public class MsgFieldRangeException extends XdppException {
    public MsgFieldRangeException(int errCode, Object appDataInfo, String message) {
        super(errCode, appDataInfo, message);
    }
}
