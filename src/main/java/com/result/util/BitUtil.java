package com.result.util;

import lombok.extern.slf4j.Slf4j;

/**
 * bit数据处理公用类
 *
 * @author
 * @ClassName: BitUtil
 * @Description:
 * @date 2022-11-29 下午2:11:36
 */
@Slf4j
public class BitUtil {
    /***
     * 截取低几位
     */
    public static final int[] low = new int[]{0, 1, 3, 7, 15, 31, 63, 127, 255};

    /***
     * 解析有符号 int
     * @param data
     * @param startOffset
     * @param lengthIn
     * @return
     */
    public static long bytesDecodeInt(byte[] data, int startOffset,  int lengthIn) {
        long result = getBinaryNumber(data, startOffset, lengthIn);
        int startByte = startOffset / 8;
        int startBit = startOffset % 8;
        //符号位为1
        if ((result & (1 << (lengthIn - 1))) == 0){
            //返回正数
            return result;
        }else {
            //补码 - 1
            result = result - 1;
            //反码取反
            result = result ^ ((1<<lengthIn)-1);
            //返回绝对值的负数
            return result * -1;
        }

    }

    /***
     * intS类型的转换
     * @param data
     * @param startOffset
     * @param lengthIn
     * @return
     */
    public static int bytesDecodeIntS(byte[] data, int startOffset, int lengthIn) {
        int result = (int) getBinaryNumber(data, startOffset, lengthIn);
        int startByte = startOffset / 8;
        int startBit = startOffset % 8;
        //符号位为1
        if ((result & (1 << (lengthIn - 1 ))) == 0){
            //返回正数
            return result;
        }else {
            //消除符号位
            result = result ^ (1<<(lengthIn - 1));
            //返回绝对值的负数
            return result * -1;
        }
    }

    ;

    /***
     * 解析UInt类型
     * @param data message 的 byte数组
     * @param startOffset 偏移量
     * @param lengthIn 长度
     * @return 转换后的无符号int
     */
    public static long bytesDecodeUInt(byte[] data, int startOffset, int lengthIn) {
        return getBinaryNumber(data, startOffset, lengthIn);
    }

    /***
     *
     * @param data
     * @param startOffset
     * @param lengthIn
     * @return 每一个bit的值,可以为00011，和11不同
     */
    public static long bytesDecodeBit(byte[] data, int startOffset, int lengthIn) {
        long result = getBinaryNumber(data, startOffset, lengthIn);
        return result;
    };

    /***
     * 获取截取长度的无符号整数，转换成二进制则为对应的bit
     * @param data
     * @param startOffset
     * @param lengthIn
     * @return
     */
    public static long getBinaryNumber(byte[] data, int startOffset, int lengthIn){
        long result = 0;

        /***
         * 开始的字节数组下标
         */
        int startByte = startOffset / 8;
        /***
         * 开始的 bit 位置
         */
        int startBit = startOffset % 8;
        //还需截取的长度为0时结束循环
        while (lengthIn != 0) {
            //开始bit位置加上剩余length长度小于byte长度
            if (lengthIn + startBit - 1 < 8) {
                //左移进位
                result = result << lengthIn;
                //不可以先左移后右移消除前面的位数
                //for instance：startBit=2,length=3 byte = 10011000 -> 01100000 ->00011000 -> 00000110 = 0110
                result += Byte.toUnsignedInt((byte) ((data[startByte] & low[8-startBit]) >> (7 - (lengthIn + startBit - 1))));
                lengthIn = 0;
                //log.trace("leftLength:{}:  {}",lengthIn,Long.toBinaryString(result));
            } else {
                result = result << (8-startBit);
                result += Byte.toUnsignedInt((byte) ((data[startByte] & low[8-startBit])));
                lengthIn = (lengthIn + startBit) - 8;
                startByte++;
                startBit = 0;
                //log.trace("leftLength:{}:  {}",lengthIn,Long.toBinaryString(result));
            }
        }
        return result;
    }
}
