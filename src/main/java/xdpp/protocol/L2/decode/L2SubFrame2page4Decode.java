package xdpp.protocol.L2.decode;
import xdpp.protocol.L2.entity.L2SubFrame2page4Entity;
import xdpp.protocol.L2.constant.L2SubFrame2page4Field;
import xdpp.protocol.common.error.MsgFieldRangeException;
import xdpp.protocol.common.error.ErrorCodeCommon;
import xdpp.protocol.common.util.BitUtil;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;
import java.math.BigDecimal;

public class L2SubFrame2page4Decode{
    public static L2SubFrame2page4Entity getEntity(byte[] msg){
        L2SubFrame2page4Entity entity = new L2SubFrame2page4Entity();
        int offset = 0;
        entity.setPageId((int)BitUtil.bytesDecodeBit(msg,offset,L2SubFrame2page4Field.PAGEID.getBitsNumber()));
        offset += L2SubFrame2page4Field.PAGEID.getBitsNumber();
        entity.setHs((int)BitUtil.bytesDecodeIntS(msg,offset,L2SubFrame2page4Field.HS.getBitsNumber()));
        offset += L2SubFrame2page4Field.HS.getBitsNumber();
        entity.setDif(BitUtil.bytesDecodeBit(msg,offset,L2SubFrame2page4Field.DIF.getBitsNumber()));
        offset += L2SubFrame2page4Field.DIF.getBitsNumber();
        return entity;
    }


    /***
    * 将获取到的实体对象转换为可以使用的实数map，map的key可以在枚举类中查看
    * 仅支持实体类没有内部类的情况
    * 对于解析成内存对象后还存在数组的情况，该方法暂不能使用
    */

    public static Map<String,Double> getRealNumberMap(L2SubFrame2page4Entity entity) throws IllegalAccessException,MsgFieldRangeException {
        Class cls = entity.getClass();
        Field[] fields = cls.getDeclaredFields();
        Map<String,Double> realNumberMap = new HashMap<>();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            BigDecimal radioDecimal = new BigDecimal("" + L2SubFrame2page4Field.dataFieldMap.get(field.getName()).getRadio());
            BigDecimal realNumberDecimal;
            if(field.get(entity) instanceof Long){
                realNumberDecimal = radioDecimal.multiply(new BigDecimal((long) field.get(entity)));
            }else {
                realNumberDecimal = radioDecimal.multiply(new BigDecimal((int) field.get(entity)));
            }
            if(L2SubFrame2page4Field.dataFieldMap.get(field.getName()).isExistRange()){
                if (L2SubFrame2page4Field.dataFieldMap.get(field.getName()).getRangeMinNumber()< realNumberDecimal.doubleValue() && L2SubFrame2page4Field.dataFieldMap.get(field.getName()).getRangeMaxNumber()> realNumberDecimal.doubleValue()){
                    realNumberMap.put(field.getName(),  realNumberDecimal.doubleValue());
                }else {
                    throw new MsgFieldRangeException(ErrorCodeCommon.E_FIELD_RANGE.getCode(),entity,ErrorCodeCommon.E_FIELD_RANGE.getDesc());
                }
            }else {
                realNumberMap.put(field.getName(), realNumberDecimal.doubleValue());
            }
        }
        return realNumberMap;
    }

}

