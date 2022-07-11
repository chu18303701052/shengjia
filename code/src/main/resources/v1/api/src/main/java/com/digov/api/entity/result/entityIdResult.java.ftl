package ${cfg.packageResult};

<#list table.importPackages as pkg>
<#if pkg?contains("com.baomidou.mybatisplus.annotation") || pkg?contains("Serializable")>
<#else>
import ${pkg};
</#if>
</#list>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Setter;
import lombok.Getter;
import ${package.Entity}.${entity};
import com.digov.api.util.common.CommonUtil;
<#-- 定义不需要生成的字段、隐藏的问题-------->
<#assign noCreateList=["createUserId", "createTime", "modifyTime", "flag"]>
/**
 * ${table.comment!} 单条记录响应结果
 *
 * @author ${author}
 * @since ${date}
 */
@Setter
@Getter
@ApiModel(value = "${entity}IdResult", description = "响应结果")
public class ${entity}IdResult {
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#-- -----   存在字段填充设置   ----->
<#if !noCreateList?seq_contains(field.propertyName)>
    @ApiModelProperty(value = "${field.comment?replace("\n", "-")}", example = "${field.comment?replace("\n", "-")}")
    private ${field.propertyType} ${field.propertyName};
</#if>
</#list>

}
