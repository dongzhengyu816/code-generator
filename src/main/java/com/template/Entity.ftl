package ${packagePath};
import ${packagePath}.DFType;
import lombok.Data;
@Data
public class ${entityName}{
<#list fieldGroupList as fieldGroup><#if fieldGroup.innerClassName == "">
<#list fieldGroup.fieldList as DataField>
    /***
     * ${DataField.notes}
     */
    <#if fieldGroup.length != "1">
        <#if DataField.bits < 32>
    private int[] ${DataField.fieldName} = new int[${fieldGroup.length}];
        <#elseif DataField.bits < 64>
    private long[] ${DataField.fieldName} = new long[${fieldGroup.length}];
        <#else>
    private byte[] ${DataField.fieldName} = new byte[${(DataField.bits/8)?ceiling}*${fieldGroup.length}];
        </#if>
    <#else >
        <#if DataField.bits < 32>
    private int ${DataField.fieldName};
        <#elseif DataField.bits < 64>
    private long ${DataField.fieldName};
        <#else>
    private byte[] ${DataField.fieldName} = new byte[${(DataField.bits/8)?ceiling}];
        </#if>
    </#if>

</#list>
<#else >
    @Data
    class ${fieldGroup.innerClassName}{
    <#list fieldGroup.fieldList as DataField>
    /***
    * ${DataField.notes}
    */
    <#if DataField.bits < 32>
        private int ${DataField.fieldName};
    <#elseif DataField.bits < 64>
        private long ${DataField.fieldName};
    <#else >
        private byte[] ${DataField.fieldName} = new byte[${(DataField.bits/8)?ceiling}];
    </#if>

</#list>
    }
<#if fieldGroup.length != "1">
    private ${fieldGroup.innerClassName}[] ${fieldGroup.innerClassName?uncap_first} = new ${fieldGroup.innerClassName}[${fieldGroup.length}];
    public void add${fieldGroup.innerClassName}(int index,${fieldGroup.innerClassName} ${fieldGroup.innerClassName?uncap_first}Temp){
        ${fieldGroup.innerClassName?uncap_first}[index] = ${fieldGroup.innerClassName?uncap_first}Temp;
    }
<#else >
    private ${fieldGroup.innerClassName} ${fieldGroup.innerClassName?uncap_first};
            </#if>
    </#if>
</#list>
}