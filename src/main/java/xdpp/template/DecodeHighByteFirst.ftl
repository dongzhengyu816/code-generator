package xdpp.protocol.${packagePath}.decode;
import xdpp.protocol.${packagePath}.entity.${entityName};
import xdpp.protocol.${packagePath}.constant.${enumName};
import xdpp.protocol.common.error.MsgFieldRangeException;
import xdpp.protocol.common.error.ErrorCodeCommon;
import xdpp.protocol.common.util.BitUtil;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;
import java.math.BigDecimal;

public class ${decodeName}{
    public static ${entityName} getEntity(byte[] msg){
        ${entityName} entity = new ${entityName}();
        int offset = 0;
<#list fieldGroupList as fieldGroup>
    <#if fieldGroup.innerClassName == "">
    <#list fieldGroup.fieldList as DataField>
        <#if fieldGroup.length == "1">
            <#if DataField.bits < 32>
        entity.set${DataField.fieldName?cap_first}((int)BitUtil.bytesDecode${DataField.fieldType}(msg,offset,${enumName}.${DataField.fieldName?upper_case}.getBitsNumber()));
        offset += ${enumName}.${DataField.fieldName?upper_case}.getBitsNumber();
            <#elseif DataField.bits < 64>
        entity.set${DataField.fieldName?cap_first}(BitUtil.bytesDecode${DataField.fieldType}(msg,offset,${enumName}.${DataField.fieldName?upper_case}.getBitsNumber()));
        offset += ${enumName}.${DataField.fieldName?upper_case}.getBitsNumber();
            <#else>
        byte[] temp${DataField.fieldName?cap_first} = new byte[${(DataField.bits/8)?ceiling}];
        for(int i=0;i<${(DataField.bits/8)?ceiling};i++){
            if(i!=${(DataField.bits/8)?ceiling-1}){
                temp${DataField.fieldName?cap_first}[i] = (byte)BitUtil.bytesDecodeUInt(msg,offset,8);
                offset += 8;
            }else{
                temp${DataField.fieldName?cap_first}[i] = (byte)BitUtil.bytesDecodeUInt(msg,offset,<#if (DataField.bits%8) == 0>8<#else ><#if (DataField.bits%8) == 0>8<#else >${(DataField.bits%8)}</#if></#if>);
                offset += <#if (DataField.bits%8) == 0>8<#else >${(DataField.bits%8)}</#if>;
            }
        }
        entity.set${DataField.fieldName?cap_first}(temp${DataField.fieldName?cap_first});
            </#if>
        <#else >
            <#if DataField.bits < 32>
        int[] temp${DataField.fieldName?cap_first} = new int[${fieldGroup.length}];
        for(int i=0;i< ${fieldGroup.length};i++){
            temp${DataField.fieldName?cap_first}[i] = (int)BitUtil.bytesDecode${DataField.fieldType}(msg,offset,${enumName}.${DataField.fieldName?upper_case}.getBitsNumber());
            offset += ${enumName}.${DataField.fieldName?upper_case}.getBitsNumber();
        }
        entity.set${DataField.fieldName?cap_first}(temp${DataField.fieldName?cap_first});
            <#elseif DataField.bits < 64>
        long[] temp${DataField.fieldName?cap_first} = new long[${fieldGroup.length}];
        for(int i=0;i< ${fieldGroup.length};i++){
            temp${DataField.fieldName?cap_first}[i] = BitUtil.bytesDecode${DataField.fieldType}(msg,offset,${enumName}.${DataField.fieldName?upper_case}.getBitsNumber());
            offset += ${enumName}.${DataField.fieldName?upper_case}.getBitsNumber();
        }
        entity.set${DataField.fieldName?cap_first}(temp${DataField.fieldName?cap_first});
            <#else>
        byte[] temp${DataField.fieldName?cap_first} = new byte[${(DataField.bits/8)?ceiling}*${fieldGroup.length}];
        for(int i=0;i<${(DataField.bits/8)?ceiling}*${fieldGroup.length};i++){
            if(i!=${(DataField.bits/8)?ceiling-1}){
                temp${DataField.fieldName?cap_first}[i] = (byte)BitUtil.bytesDecodeUInt(msg,offset,8);
                offset += 8;
            }else{
                temp${DataField.fieldName?cap_first}[i] = (byte)BitUtil.bytesDecodeUInt(msg,offset,<#if (DataField.bits%8) == 0>8<#else >${(DataField.bits%8)}</#if>);
                offset += <#if (DataField.bits%8) == 0>8<#else >${(DataField.bits%8)}</#if>;
            }
        }
        entity.set${DataField.fieldName?cap_first}(temp${DataField.fieldName?cap_first});
            </#if>
        </#if>
    </#list>
<#else >
    <#if fieldGroup.length != "1">
        for(int i=0;i<${fieldGroup.length};i++){
            ${entityName}.${fieldGroup.innerClassName} ${fieldGroup.innerClassName?uncap_first} = entity.new ${fieldGroup.innerClassName}();
        <#list fieldGroup.fieldList as DataField>
            <#if DataField.bits < 32>
            ${fieldGroup.innerClassName?uncap_first}.set${DataField.fieldName?cap_first}((int)BitUtil.bytesDecode${DataField.fieldType}(msg,offset,${enumName}.${DataField.fieldName?upper_case}.getBitsNumber()));
            offset += ${enumName}.${DataField.fieldName?upper_case}.getBitsNumber();
            <#elseif DataField.bits < 64>
            ${fieldGroup.innerClassName?uncap_first}.set${DataField.fieldName?cap_first}(BitUtil.bytesDecode${DataField.fieldType}(msg,offset,${enumName}.${DataField.fieldName?upper_case}.getBitsNumber()));
            offset += ${enumName}.${DataField.fieldName?upper_case}.getBitsNumber();
            <#else>
            byte[] temp${DataField.fieldName?cap_first} = new byte[${(DataField.bits/8)?ceiling}];
            for(int i=0;i<${(DataField.bits/8)?ceiling};i++){
                if(i!=${(DataField.bits/8)?ceiling-1}){
                    temp${DataField.fieldName?cap_first}[i] = (byte)BitUtil.bytesDecodeUInt(msg,offset,8);
                    offset += 8;
                }else{
                    temp${DataField.fieldName?cap_first}[i] = (byte)BitUtil.bytesDecodeUInt(msg,offset,<#if (DataField.bits%8) == 0>8<#else >${(DataField.bits%8)}</#if>);
                    offset += <#if (DataField.bits%8) == 0>8<#else >${(DataField.bits%8)}</#if>;
                }
            }
            ${fieldGroup.innerClassName?uncap_first}.set${DataField.fieldName?cap_first}(temp${DataField.fieldName?cap_first});
            </#if>

        </#list>
            entity.add${fieldGroup.innerClassName}(i,${fieldGroup.innerClassName?uncap_first});
        }
    <#else >
        ${entityName}.${fieldGroup.innerClassName} ${fieldGroup.innerClassName?uncap_first} = entity.new ${fieldGroup.innerClassName}();
        <#list fieldGroup.fieldList as DataField>
            <#if DataField.bits < 32>
        ${fieldGroup.innerClassName?uncap_first}.set${DataField.fieldName?cap_first}((int)BitUtil.bytesDecode${DataField.fieldType}(msg,offset,${enumName}.${DataField.fieldName?upper_case}.getBitsNumber()));
        offset += ${enumName}.${DataField.fieldName?upper_case}.getBitsNumber();
            <#elseif DataField.bits < 64>
        ${fieldGroup.innerClassName?uncap_first}.set${DataField.fieldName?cap_first}(BitUtil.bytesDecode${DataField.fieldType}(msg,offset,${enumName}.${DataField.fieldName?upper_case}.getBitsNumber()));
        offset += ${enumName}.${DataField.fieldName?upper_case}.getBitsNumber();
            <#else>
        byte[] temp${DataField.fieldName?cap_first} = new byte[${(DataField.bits/8)?ceiling}];
        for(int i=0;i<${(DataField.bits/8)?ceiling};i++){
            if(i!=${(DataField.bits/8)?ceiling-1}){
                temp${DataField.fieldName?cap_first}[i] = (byte)BitUtil.bytesDecodeUInt(msg,offset,8);
                offset += 8;
            }else{
                temp${DataField.fieldName?cap_first}[i] = (byte)BitUtil.bytesDecodeUInt(msg,offset,<#if (DataField.bits%8) == 0>8<#else >${(DataField.bits%8)}</#if>);
                offset += <#if (DataField.bits%8) == 0>8<#else >${(DataField.bits%8)}</#if>;
            }
        }
        ${fieldGroup.innerClassName?uncap_first}.set${DataField.fieldName?cap_first}(temp${DataField.fieldName?cap_first});
            </#if>

        </#list>
        entity.set${fieldGroup.innerClassName}(${fieldGroup.innerClassName?uncap_first});
    </#if>
</#if>
</#list>
        return entity;
    }


    /***
    * 将获取到的实体对象转换为可以使用的实数map，map的key可以在枚举类中查看
    * 仅支持实体类没有内部类的情况
    * 对于解析成内存对象后还存在数组的情况，该方法暂不能使用
    */

    public static Map<String,Double> getRealNumberMap(${entityName} entity) throws IllegalAccessException,MsgFieldRangeException {
        Class cls = entity.getClass();
        Field[] fields = cls.getDeclaredFields();
        Map<String,Double> realNumberMap = new HashMap<>();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            BigDecimal radioDecimal = new BigDecimal("" + ${enumName}.dataFieldMap.get(field.getName()).getRadio());
            BigDecimal realNumberDecimal;
            if(field.get(entity) instanceof Long){
                realNumberDecimal = radioDecimal.multiply(new BigDecimal((long) field.get(entity)));
            }else {
                realNumberDecimal = radioDecimal.multiply(new BigDecimal((int) field.get(entity)));
            }
            if(${enumName}.dataFieldMap.get(field.getName()).isExistRange()){
                if (${enumName}.dataFieldMap.get(field.getName()).getRangeMinNumber()< realNumberDecimal.doubleValue() && ${enumName}.dataFieldMap.get(field.getName()).getRangeMaxNumber()> realNumberDecimal.doubleValue()){
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

