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
<#-- 定义不需要生成的字段、隐藏的问题-------->
<#assign noCreateList=["createUserId", "createTime", "modifyTime", "flag"]>
/**
 * ${table.comment!} 单条记录参数
 *
 * @author ${author}
 * @since ${date}
 */
@Setter
@Getter
@ApiModel(value = "${entity}DetailResult", description = "参数")
public class ${entity}DetailResult {
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
<#-- -----   存在字段填充设置   ----->
    <#if !noCreateList?seq_contains(field.propertyName)>
    @ApiModelProperty(value = "${field.comment?replace("\n", "-")}", example = "${field.comment?replace("\n", "-")}")
    private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
    /**
     * 返回参数处理
     */
    public void hanleResult(){

    }
}
