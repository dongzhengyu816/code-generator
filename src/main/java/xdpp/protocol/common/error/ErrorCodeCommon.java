package xdpp.protocol.common.error;

/**
 * @program: protocol-decode
 * @description: 公共错误码
 * @author: Zhengyu Dong
 * @create: 2023-01-10 20:41
 **/
public enum ErrorCodeCommon {
    E_LENGTH(44100,"长度不够"),
    E_FORMAT(44101,"格式错误"),
    E_DECODE(44102,"译码异常"),
    E_CRC(44103,"crc校验错误"),
    E_FIELD_RANGE(44104,"属性字段不在范围内");

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    ErrorCodeCommon(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int  code;  //错误代码
    private String  desc; // 简短描述
}
