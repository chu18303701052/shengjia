package ${cfg.packageParam};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;
import com.digov.api.util.common.CommonUtil;

/**
 * ${table.comment!} 多条记录参数
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@ApiModel(value = "${entity}IdsParam", description = "参数")
public class ${entity}IdsParam {
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#-- -----   存在字段填充设置   ----->
    <#if field.propertyName == "id">
    @ApiModelProperty(value = "${field.comment?replace("\n", "-")}集合", example = "[1]")
    private List<${field.propertyType}> ${field.propertyName}s;
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
        if (CommonUtil.isEmpty(${field.propertyName}s)) {
            return "${field.comment?replace("\n", "-")}集合 不能为空";
        }
    </#if>
</#list>
        return null;
    }
}
