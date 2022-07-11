package ${cfg.packageParam};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
import com.digov.api.util.common.CommonUtil;

/**
 * ${table.comment!} 单条记录参数
 *
 * @author ${author}
 * @since ${date}
 */
@Setter
@Getter
@ApiModel(value = "${entity}IdParam", description = "参数")
public class ${entity}IdParam {
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#-- -----   存在字段填充设置   ----->
    <#if field.propertyName == "id">
    @ApiModelProperty(value = "${field.comment?replace("\n", "-")}", example = "1")
    private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
    /**
    * 参数校验
    * @return
    */
    public String checkParam(){
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
<#-- -----   存在字段填充设置   ----->
    <#if field.propertyName == "id">
        if (CommonUtil.isEmpty(${field.propertyName})) {
            return "${field.comment?replace("\n", "-")} 不能为空";
        }
    </#if>
</#list>
        return null;
    }
}
