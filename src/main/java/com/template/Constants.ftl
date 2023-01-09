package ${packagePath};
import ${packagePath}.DFType;
public enum ${enumName}{
<#list fieldGroupList as fieldGroup>
    <#list fieldGroup.fieldList as DataField>
    ${DataField.fieldName?upper_case}(${DataField.bits},DFType.${DataField.fieldType?upper_case})<#if DataField_has_next ||fieldGroup_has_next>,<#else >;</#if>
    </#list>
</#list>
    private int bitsNumber;
    private DFType dfType;
    ${enumName}(int bitsNumber, DFType dfType) {
        this.bitsNumber = bitsNumber;
        this.dfType = dfType;
    }

    public DFType getDfType() {
        return dfType;
    }

    public int getBitsNumber() {
        return bitsNumber;
    }
}