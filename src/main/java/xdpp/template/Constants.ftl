package xdpp.protocol.${packagePath}.constant;
import java.util.HashMap;

public enum ${enumName}{
<#list fieldGroupList as fieldGroup>
    <#list fieldGroup.fieldList as DataField>
    ${DataField.fieldName?upper_case}(<#if DataField.existRange == true>true<#else >false</#if>,${DataField.rangeMinNumber?c},${DataField.rangeMaxNumber?c},"${DataField.fieldName}",<#if DataField.indexNumber == 1>${DataField.baseNumber?c}<#else >Math.pow(${DataField.baseNumber?c},${DataField.indexNumber?c})</#if>,${DataField.bits},DFType.${DataField.fieldType?upper_case})<#if DataField_has_next ||fieldGroup_has_next>,<#else >;</#if>
    </#list>
</#list>
    private boolean isExistRange;
    private double rangeMinNumber;
    private double rangeMaxNumber;
    private String fieldKey;
    private double radio;
    private int bitsNumber;
    private DFType dfType;

    ${enumName}(boolean isExistRange,double rangeMinNumber, double rangeMaxNumber,String fieldKey,double radio,int bitsNumber, DFType dfType) {
        this.isExistRange = isExistRange;
        this.rangeMinNumber = rangeMinNumber;
        this.rangeMaxNumber = rangeMaxNumber;
        this.fieldKey = fieldKey;
        this.radio = radio;
        this.bitsNumber = bitsNumber;
        this.dfType = dfType;
    }

    public static HashMap<String,${enumName}>  dataFieldMap = new HashMap<>();
    static {
        for (${enumName} dataField : ${enumName}.values()){
            dataFieldMap.put(dataField.getFieldKey(),dataField);
        }
    }

    public double getRangeMinNumber() {
        return rangeMinNumber;
    }

    public double getRangeMaxNumber() {
        return rangeMaxNumber;
    }

    public boolean isExistRange() {
        return isExistRange;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public double getRadio() {
        return radio;
    }

    public DFType getDfType() {
        return dfType;
    }

    public int getBitsNumber() {
        return bitsNumber;
    }
}